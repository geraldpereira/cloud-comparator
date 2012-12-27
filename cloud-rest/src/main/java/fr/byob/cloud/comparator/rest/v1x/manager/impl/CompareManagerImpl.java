package fr.byob.cloud.comparator.rest.v1x.manager.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.dozer.Mapper;

import com.google.inject.Inject;

import fr.byob.cloud.comparator.db.bean.ProviderComputeSize;
import fr.byob.cloud.comparator.db.bean.ProviderSearchCriteria;
import fr.byob.cloud.comparator.db.bean.SimpleProvider;
import fr.byob.cloud.comparator.db.bean.SizedProvider;
import fr.byob.cloud.comparator.db.dao.ProviderDAO;
import fr.byob.cloud.comparator.rest.exception.IllegalRequestException;
import fr.byob.cloud.comparator.rest.v1x.bean.CompareRequest;
import fr.byob.cloud.comparator.rest.v1x.bean.CompareResult;
import fr.byob.cloud.comparator.rest.v1x.manager.CompareManager;

public class CompareManagerImpl implements CompareManager {
	@Inject
	private ProviderDAO providerDAO;

	@Inject
	private Mapper dozer;

	private final static Comparator<CompareResult> resultComparator = new Comparator<CompareResult>() {

		@Override
		public int compare(final CompareResult o1, final CompareResult o2) {
			return o1.getTotalCost().compareTo(o2.getTotalCost());
		}
	};

	private ProviderSearchCriteria toDB(final CompareRequest request) {
		final ProviderSearchCriteria criteria = dozer.map(request, ProviderSearchCriteria.class);
		return criteria;
	}

	private CompareResult toDTO(final CompareRequest request, final SimpleProvider simpleProvider) {
		final CompareResult result = new CompareResult();
		// commons
		result.setProviderName(simpleProvider.getId());
		result.setLocation(simpleProvider.getLocation());

		BigDecimal cpu = request.getCpu();
		if (cpu == null){
			cpu = BigDecimal.ZERO;
		}

		BigDecimal storage = request.getStorage();
		if (storage == null){
			storage = BigDecimal.ZERO;
		}

		BigDecimal ram = request.getRam();
		if (ram == null){
			ram = BigDecimal.ZERO;
		}

		// compute
		result.setCpu(cpu);
		result.setRam(ram);
		result.setStorage(storage);

		final BigDecimal cpuCost = simpleProvider.getCpuCost().multiply(
				new BigDecimal(request.getHoursPerMonth() * request.getInstancesCount()).multiply(cpu));


		final BigDecimal ramCost = simpleProvider.getRamCost().multiply(
				new BigDecimal(request.getHoursPerMonth() * request.getInstancesCount()).multiply(ram));

		final BigDecimal storageCost = simpleProvider.getStorageCost().multiply(new BigDecimal(request.getInstancesCount())).multiply(storage);

		result.setComputeCost(cpuCost.add(ramCost).add(storageCost));
		// bandwidth
		result.setBandwidthInCost(simpleProvider.getBandwidthInCost().multiply(request.getBandwidthIn()));
		result.setBandwidthOutCost(simpleProvider.getBandwidthOutCost().multiply(request.getBandwidthOut()));
		result.setBandwidthCost(result.getBandwidthInCost().add(result.getBandwidthOutCost()));
		// total
		result.setTotalCost(result.getComputeCost().add(result.getBandwidthCost()));
		return result;
	}

	public CompareResult toDTO(final CompareRequest request, final SizedProvider sizedProvider,
			final ProviderComputeSize size) {
		final CompareResult result = new CompareResult();
		// commons
		result.setProviderName(sizedProvider.getId());
		result.setLocation(sizedProvider.getLocation());
		// compute
		result.setCpu(size.getCpu());
		result.setRam(size.getRam());
		result.setStorage(size.getStorage());
		result.setComputeCost(size.getCost().multiply(
				new BigDecimal(request.getHoursPerMonth() * request.getInstancesCount())));

		// bandwidth
		result.setBandwidthInCost(sizedProvider.getBandwidthInCost().multiply(request.getBandwidthIn()));
		result.setBandwidthOutCost(sizedProvider.getBandwidthOutCost().multiply(request.getBandwidthOut()));
		result.setBandwidthCost(result.getBandwidthInCost().add(result.getBandwidthOutCost()));
		// total
		result.setTotalCost(result.getComputeCost().add(result.getBandwidthCost()));

		return result;
	}

	@Override
	public List<CompareResult> compare(final CompareRequest request) {
		validateRequest(request);

		final ProviderSearchCriteria criteria = toDB(request);

		final List<SizedProvider> sizedProviders = providerDAO.searchSized(criteria);
		final List<SimpleProvider> simpleProviders = providerDAO.searchSimples(criteria);

		final List<CompareResult> results = new ArrayList<CompareResult>();

		for (final SimpleProvider simpleProvider : simpleProviders) {
			results.add(toDTO(request, simpleProvider));
		}

		for (final SizedProvider sizedProvider : sizedProviders) {
			for (final ProviderComputeSize size : sizedProvider.getSizes()) {
				results.add(toDTO(request, sizedProvider, size));
			}
		}

		Collections.sort(results, resultComparator);

		return results;
	}

	private static void validateRequest (final CompareRequest request){
		validateBandwidth(request.getBandwidthIn(), "BandwidthIn");
		validateBandwidth(request.getBandwidthOut(), "BandwidthOut");

		validatePositiveBigDecimal(request.getCpu(), "CPU");
		validateHoursPerMonth(request.getHoursPerMonth());
		validatePositiveInteger(request.getInstancesCount(), "InstancesCount");
		validatePositiveNotNullBigDecimal(request.getRam(), "RAM");
		validatePositiveBigDecimal(request.getStorage(), "Storage");
	}

	private static void validateHoursPerMonth(final Integer hoursPerMonths) {
		if (hoursPerMonths == null || hoursPerMonths < 1 || hoursPerMonths > 730) {
			throw new IllegalRequestException("'Hours per month' must be defined as an Integer between 1 and 730");
		}
	}

	private static void validateBandwidth(final BigDecimal bandwidth, final String name) {
		if (bandwidth == null || bandwidth.compareTo(BigDecimal.ZERO) < 0
				|| bandwidth.compareTo(new BigDecimal(10240)) > 0) {
			throw new IllegalRequestException(name + " must be defined as an Integer between 0 and 10240");
		}
	}

	private static void validatePositiveInteger(final Integer integer, final String name) {
		if (integer == null || integer < 0) {
			throw new IllegalRequestException(name + "must be defined as a positive Integer");
		}
	}

	private static void validatePositiveNotNullBigDecimal(final BigDecimal bigDecimal, final String name) {
		if (bigDecimal == null || bigDecimal.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalRequestException(name + "must be defined as a positive Integer");
		}
	}

	private static void validatePositiveBigDecimal(final BigDecimal bigDecimal, final String name) {
		if (bigDecimal == null){
			return;
		}
		validatePositiveNotNullBigDecimal(bigDecimal, name);
	}

}
