package fr.byob.cloud.comparator.rest.v1x.resource;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fr.byob.cloud.comparator.commons.guice.LoggerModule;
import fr.byob.cloud.comparator.db.guice.CloudMyBatisModule;
import fr.byob.cloud.comparator.db.guice.DAOModule;
import fr.byob.cloud.comparator.rest.DozerModule;
import fr.byob.cloud.comparator.rest.tomcat.CloudJerseyServletModule;
import fr.byob.cloud.comparator.rest.v1x.bean.CompareRequest;
import fr.byob.cloud.comparator.rest.v1x.bean.CompareResult;
import fr.byob.cloud.comparator.rest.v1x.bean.CompareResults;

public class CompareResourceTest {

	private CompareResource compareResource;
	private static Injector injector;

	@BeforeClass
	public static void beforeClass() {
		injector = Guice.createInjector(new LoggerModule(), new DAOModule(), new CloudMyBatisModule(),
				new CloudJerseyServletModule(),
				new DozerModule());

	}

	@Before
	public void before() {
		compareResource = injector.getInstance(CompareResource.class);
		Assert.assertNotNull(compareResource);
	}

	@Test
	public void compare() {
		final CompareRequest request = new CompareRequest();
		request.setLocation("eu");
		request.setBandwidthIn(new BigDecimal(10));
		request.setBandwidthOut(new BigDecimal(10));
		request.setCpu(new BigDecimal(1));
		request.setRam(new BigDecimal(1));
		request.setInstancesCount(2);
		request.setHoursPerMonth(10);
		request.setStorage(new BigDecimal(100));

		final CompareResults results = compareResource.compare(request);

		final CompareResult awsResult = results.getResults().get(0);
		Assert.assertEquals("aws_ec2", awsResult.getProviderName());
		Assert.assertEquals("eu_west_3", awsResult.getLocation());
		Assert.assertEquals(0d, awsResult.getBandwidthInCost().doubleValue());
		Assert.assertEquals(1.2d, awsResult.getBandwidthOutCost().doubleValue());
		Assert.assertEquals(1.2d, awsResult.getBandwidthCost().doubleValue());
		Assert.assertEquals(1d, awsResult.getCpu().doubleValue());
		Assert.assertEquals(1.7d, awsResult.getRam().doubleValue());
		Assert.assertEquals(1.9d, awsResult.getComputeCost().doubleValue());
		Assert.assertEquals(3.1d, awsResult.getTotalCost().doubleValue());

		final CompareResult cloudSigmaResult = results.getResults().get(1);
		Assert.assertEquals("cloudsigma", cloudSigmaResult.getProviderName());
		Assert.assertEquals("eu_west_1", cloudSigmaResult.getLocation());
		Assert.assertEquals(0d, cloudSigmaResult.getBandwidthInCost().doubleValue());
		Assert.assertEquals(0.585d, cloudSigmaResult.getBandwidthOutCost().doubleValue());
		Assert.assertEquals(0.585d, cloudSigmaResult.getBandwidthCost().doubleValue());
		Assert.assertEquals(1d, cloudSigmaResult.getCpu().doubleValue());
		Assert.assertEquals(1d, cloudSigmaResult.getRam().doubleValue());
		Assert.assertEquals(37.036d, cloudSigmaResult.getComputeCost().doubleValue());
		Assert.assertEquals(37.621d, cloudSigmaResult.getTotalCost().doubleValue());

	}

	@Test
	public void compareOneResult() {
		final CompareRequest request = new CompareRequest();
		request.setLocation("ap_southeast");
		request.setBandwidthIn(new BigDecimal(10));
		request.setBandwidthOut(new BigDecimal(10));
		request.setCpu(new BigDecimal(10));
		request.setRam(new BigDecimal(10));
		request.setInstancesCount(1);
		request.setHoursPerMonth(10);
		request.setStorage(new BigDecimal(100));

		final CompareResults results = compareResource.compare(request);

		Assert.assertEquals(1, results.getResults().size());
	}

	@Test
	public void compareNoResult() {
		final CompareRequest request = new CompareRequest();
		request.setLocation("niak");
		request.setBandwidthIn(new BigDecimal(10));
		request.setBandwidthOut(new BigDecimal(10));
		request.setCpu(new BigDecimal(10));
		request.setRam(new BigDecimal(10));
		request.setInstancesCount(1);
		request.setHoursPerMonth(10);
		request.setStorage(new BigDecimal(100));

		final CompareResults results = compareResource.compare(request);

		Assert.assertEquals(0, results.getResults().size());
	}


}
