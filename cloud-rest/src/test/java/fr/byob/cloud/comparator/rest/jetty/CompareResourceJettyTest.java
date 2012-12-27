package fr.byob.cloud.comparator.rest.jetty;

import java.math.BigDecimal;

import javax.ws.rs.core.MediaType;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.Base64;

import fr.byob.cloud.comparator.rest.exception.ExceptionEntity;
import fr.byob.cloud.comparator.rest.v1x.bean.CompareRequest;
import fr.byob.cloud.comparator.rest.v1x.bean.CompareResult;
import fr.byob.cloud.comparator.rest.v1x.bean.CompareResults;

public class CompareResourceJettyTest {

	private static String authorization;
	private static WebResource webResource;
	private static Launcher launcher;

	@BeforeClass
	public static void beforeClass() throws Exception {
		launcher = new Launcher();

		// Init http auth header
		final byte[] pwd = Base64.encode("cloud-rest-user:niancat");
		final String value = new String(pwd);
		authorization = "Basic " + value;

		// Init client
		final ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		final Client client = Client.create(clientConfig);

		client.addFilter(new ClientFilter() {
			@Override
			public ClientResponse handle(final ClientRequest cr) throws ClientHandlerException {
				final ClientResponse response = getNext().handle(cr);
				return response;
			}
		});
		//		cloud-rest/api/
		// Init web resource
		webResource = client.resource("http://localhost:10080/v1.0");
	}

	@AfterClass
	public static void afterClass() throws Exception {
		launcher.stop();
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

		final CompareResults results = webResource.path("compare/result").header("authorization", authorization).type(
				MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).post(CompareResults.class,
						request);

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
	public void compareFailTest() {
		final CompareRequest request = new CompareRequest();
		request.setLocation("eu");
		request.setBandwidthIn(new BigDecimal(10241));

		try {
			webResource.path("compare/result").header("authorization", authorization).type(
					MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).post(CompareResults.class,
							request);
		} catch (final Throwable t) {
			Assert.assertEquals(UniformInterfaceException.class, t.getClass());
			final UniformInterfaceException exception = (UniformInterfaceException) t;
			final ClientResponse response = exception.getResponse();
			final ExceptionEntity entity = response.getEntity(ExceptionEntity.class);
			Assert.assertEquals("BandwidthIn must be defined as an Integer between 0 and 10240", entity.getMessage());
			Assert.assertEquals(
					"POST http://localhost:10080/v1.0/compare/result returned a response status of 400 Bad Request",
					t.getMessage());
		}
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

		final CompareResults results = webResource.path("compare/result").header("authorization", authorization).type(
				MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).post(CompareResults.class,
						request);

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

		final CompareResults results = webResource.path("compare/result").header("authorization", authorization).type(
				MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).post(CompareResults.class,
						request);

		Assert.assertEquals(0, results.getResults().size());
	}

}
