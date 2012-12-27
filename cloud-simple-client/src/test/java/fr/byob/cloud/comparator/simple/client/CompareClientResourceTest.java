package fr.byob.cloud.comparator.simple.client;

import java.math.BigDecimal;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;

import fr.byob.cloud.comparator.commons.guice.LoggerModule;
import fr.byob.cloud.comparator.rest.exception.ExceptionEntity;
import fr.byob.cloud.comparator.rest.v1x.bean.CompareRequest;
import fr.byob.cloud.comparator.rest.v1x.bean.CompareResult;
import fr.byob.cloud.comparator.simple.client.guice.ClientManagerModule;
import fr.byob.cloud.comparator.simple.client.guice.HTTPClientModule;
import fr.byob.cloud.comparator.simple.client.manager.CompareClientResource;

/**
 * Warning : this test needs the rest application to be deployed on tomcat
 *
 *
 * @author Kojiro
 *
 */
public class CompareClientResourceTest {

	private CompareClientResource compareResource;
	private static Injector injector;

	@BeforeClass
	public static void beforeClass() {
		injector = Guice.createInjector(new LoggerModule(), new HTTPClientModule(), new ClientManagerModule());
	}

	@Before
	public void before() {
		compareResource = injector.getInstance(CompareClientResource.class);
		Assert.assertNotNull(compareResource);
	}

	@Test
	public void compareTest() {
		final CompareRequest request = new CompareRequest();
		request.setLocation("eu");
		request.setBandwidthIn(new BigDecimal(10));
		request.setBandwidthOut(new BigDecimal(10));
		request.setCpu(new BigDecimal(1));
		request.setRam(new BigDecimal(1));
		request.setInstancesCount(2);
		request.setHoursPerMonth(10);
		request.setStorage(new BigDecimal(100));

		final List<CompareResult> results = compareResource.compare(request);

		final CompareResult awsResult = results.get(0);
		Assert.assertEquals("aws_ec2", awsResult.getProviderName());
		Assert.assertEquals("eu_west_3", awsResult.getLocation());
		Assert.assertEquals(0d, awsResult.getBandwidthInCost().doubleValue());
		Assert.assertEquals(1.2d, awsResult.getBandwidthOutCost().doubleValue());
		Assert.assertEquals(1.2d, awsResult.getBandwidthCost().doubleValue());
		Assert.assertEquals(1d, awsResult.getCpu().doubleValue());
		Assert.assertEquals(1.7d, awsResult.getRam().doubleValue());
		Assert.assertEquals(1.9d, awsResult.getComputeCost().doubleValue());
		Assert.assertEquals(3.1d, awsResult.getTotalCost().doubleValue());

		final CompareResult cloudSigmaResult = results.get(1);
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
	public void compareFailTest() {
		final CompareRequest request = new CompareRequest();
		request.setLocation("eu");
		request.setBandwidthIn(new BigDecimal(10241));

		try {
			compareResource.compare(request);
		} catch (final Throwable t) {
			Assert.assertEquals(UniformInterfaceException.class, t.getClass());
			final UniformInterfaceException exception = (UniformInterfaceException) t;
			final ClientResponse response = exception.getResponse();
			final ExceptionEntity entity = response.getEntity(ExceptionEntity.class);
			Assert.assertEquals("BandwidthIn must be defined as an Integer between 0 and 10240", entity.getMessage());
		}
	}
}
