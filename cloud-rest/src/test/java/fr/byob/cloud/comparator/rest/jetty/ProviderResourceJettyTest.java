package fr.byob.cloud.comparator.rest.jetty;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.Base64;

import fr.byob.cloud.comparator.rest.v1x.bean.Locations;

public class ProviderResourceJettyTest {

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
	public void locationsTest() {

		final Locations locations = webResource.path("provider/locations").header("authorization", authorization).type(
				MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get(Locations.class);

		final List<String> expected = Arrays.asList("ap", "eu", "us");
		Assert.assertEquals(expected, locations.getLocations());
	}

}
