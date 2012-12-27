package fr.byob.cloud.comparator.gwt.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;

import fr.byob.cloud.comparator.commons.guice.LoggerModule;
import fr.byob.cloud.comparator.commons.units.ByteUnit;
import fr.byob.cloud.comparator.commons.units.ByteValue;
import fr.byob.cloud.comparator.commons.units.CostValue;
import fr.byob.cloud.comparator.commons.units.HertzUnit;
import fr.byob.cloud.comparator.commons.units.HertzValue;
import fr.byob.cloud.comparator.gwt.client.RestService;
import fr.byob.cloud.comparator.gwt.client.widget.model.CompareException;
import fr.byob.cloud.comparator.gwt.client.widget.model.ResultRowModel;
import fr.byob.cloud.comparator.gwt.client.widget.model.SimpleProviderModel;
import fr.byob.cloud.comparator.rest.exception.ExceptionEntity;
import fr.byob.cloud.comparator.rest.v1x.bean.CompareRequest;
import fr.byob.cloud.comparator.rest.v1x.bean.CompareResult;
import fr.byob.cloud.comparator.simple.client.guice.ClientManagerModule;
import fr.byob.cloud.comparator.simple.client.guice.HTTPClientModule;
import fr.byob.cloud.comparator.simple.client.manager.CompareClientResource;
import fr.byob.cloud.comparator.simple.client.manager.ProviderClientResource;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class RestServiceImpl extends RemoteServiceServlet implements RestService {

	private final ProviderClientResource providerClient;
	private final CompareClientResource compareClient;
	private List<String> locations;
	private long lastLocationsGet = 0;

	public RestServiceImpl() {
		// TODO maybe we could find a better / nicer way to do this ?
		// Check here : http://stuffthathappens.com/blog/2009/09/14/guice-with-gwt/
		final Injector injector = Guice.createInjector(new LoggerModule(), new HTTPClientModule(),
				new ClientManagerModule());

		providerClient = injector.getInstance(ProviderClientResource.class);
		compareClient = injector.getInstance(CompareClientResource.class);
	}

	@Override
	public List<String> getLocations() {
		final long currentTime = System.currentTimeMillis();
		synchronized (this) {
			if (currentTime - lastLocationsGet > 600000) { // 10 minutes cache
				locations = null;
			}

			if (locations == null) {
				locations = providerClient.locations();
				lastLocationsGet = System.currentTimeMillis();
			}
		}
		return locations;
	}

	private CompareRequest newCompareRequest(final Integer nbHours, final Integer nbServer, final String cpu,
			final String ram, final String storage, final String inBW, final String outBW, final String location) {
		final CompareRequest request = new CompareRequest();

		Integer instancesCount = 1;
		if (nbServer != null) {
			instancesCount = nbServer;
		}

		request.setHoursPerMonth(nbHours);

		request.setInstancesCount(instancesCount);

		final HertzValue cpuValue = new HertzValue(cpu);
		request.setCpu(cpuValue.toGigaHertz());

		final ByteValue ramValue = new ByteValue(ram);
		request.setRam(ramValue.toGigaByte());

		final ByteValue storageValue = new ByteValue(storage);
		request.setStorage(storageValue.toGigaByte());

		final ByteValue bandwidthInValue = new ByteValue(inBW);
		request.setBandwidthIn(bandwidthInValue.toGigaByte());

		final ByteValue bandwidthOutValue = new ByteValue(outBW);
		request.setBandwidthOut(bandwidthOutValue.toGigaByte());

		request.setLocation(location);

		return request;
	}

	private ResultRowModel newResultRowModel(final CompareResult compareResult) {
		final ResultRowModel rowModel = new ResultRowModel();

		final HertzValue cpuValue = new HertzValue(compareResult.getCpu(), HertzUnit.GIGAHERTZ);
		final ByteValue ramValue = new ByteValue(compareResult.getRam(), ByteUnit.GIGABYTE);
		final ByteValue storageValue = new ByteValue(compareResult.getStorage(), ByteUnit.GIGABYTE);

		final CostValue bandwidthInCostValue = new CostValue(compareResult.getBandwidthInCost());
		final CostValue bandwidthOutCostValue = new CostValue(compareResult.getBandwidthOutCost());
		final CostValue bandwidthCostValue = new CostValue(compareResult.getBandwidthCost());
		final CostValue computeCostValue = new CostValue(compareResult.getComputeCost());
		final CostValue totalCostValue = new CostValue(compareResult.getTotalCost());

		rowModel.setCpu(cpuValue.toString());
		rowModel.setRam(ramValue.toString());
		rowModel.setStorage(storageValue.toString());

		rowModel.setBandwidthInCost(bandwidthInCostValue.toString());
		rowModel.setBandwidthOutCost(bandwidthOutCostValue.toString());
		rowModel.setBandwidthCost(bandwidthCostValue.toString());
		rowModel.setComputeCost(computeCostValue.toString());
		rowModel.setTotalCost(totalCostValue.toString());

		rowModel.setLocation(compareResult.getLocation());
		rowModel.setProviderName(compareResult.getProviderName());
		rowModel.setCost(compareResult.getTotalCost().doubleValue());

		return rowModel;
	}

	@Override
	public List<ResultRowModel> getResults(final Integer nbHours, final Integer nbServer, final String cpu,
			final String ram, final String storage, final String inBW, final String outBW, final String location)
			throws CompareException {
		try {
			final CompareRequest request = newCompareRequest(nbHours, nbServer, cpu, ram, storage, inBW, outBW,
					location);
			final List<CompareResult> results = compareClient.compare(request);

			final List<ResultRowModel> resultRow = new ArrayList<ResultRowModel>();
			for (final CompareResult result : results) {
				resultRow.add(newResultRowModel(result));
			}
			return resultRow;
		} catch (final UniformInterfaceException uie) {
			final ClientResponse response = uie.getResponse();
			final ExceptionEntity entity = response.getEntity(ExceptionEntity.class);
			final String message = entity.getMessage();
			throw new CompareException(message);
		}
	}

	@Override
	public List<SimpleProviderModel> getBestProviders(final Integer nbHours, final Integer nbServer, final String cpu,
			final String ram, final String storage, final String inBW, final String outBW, final String location) {
		final List<SimpleProviderModel> resultRow = new ArrayList<SimpleProviderModel>();//dataProvider.getList();
		{
			for (int i = 0 ; i < 4 ; i++) {
				final SimpleProviderModel model = new SimpleProviderModel();
				if (i == 0) {
					model.setProviderName("CloudSigma");
				} else if (i == 1) {
					model.setProviderName("Amazon");
				} else if (i == 2) {
					model.setProviderName("GoGrid");
				} else {
					model.setProviderName("RackSpace");
				}
				model.setTotalCost(56.3);
				resultRow.add(model);
			}
		}
		return resultRow;
	}
}
