package fr.byob.cloud.comparator.simple.client.guice;

import java.io.IOException;
import java.util.Properties;

import com.google.inject.AbstractModule;
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

public class HTTPClientModule extends AbstractModule {

	@Override
	protected void configure() {
		// If we want to add an auth header :
		// http://blogs.oracle.com/enterprisetechtips/entry/consuming_restful_web_services_with
		//		webResource.header("authorization", Base64.encode("tomcat:tomcat"));

		// Init http auth header
		final byte[] pwd = Base64.encode("cloud-rest-user:niancat");
		final String value = new String(pwd);
		final String authorization = "Basic " + value;

		bindConstant().annotatedWith(AuthorizationHeader.class).to(authorization);

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

		final Properties properties = new Properties();
		try {
			properties.load(getClass().getResourceAsStream("/client.properties"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
		final String serverUrl = properties.getProperty("server.http.url");
		System.out.println("HTTPClientModule.configure() " + serverUrl);
		// Init web resource
		final WebResource webResource = client.resource(serverUrl);

		bind(WebResource.class).toInstance(webResource);

	}

}
