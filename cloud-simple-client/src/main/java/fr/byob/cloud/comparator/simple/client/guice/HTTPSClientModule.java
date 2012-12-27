package fr.byob.cloud.comparator.simple.client.guice;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.google.inject.AbstractModule;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.client.urlconnection.HTTPSProperties;
import com.sun.jersey.core.util.Base64;

public class HTTPSClientModule extends AbstractModule {

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

		final ClientConfig config = new DefaultClientConfig();
		//		SSLContext ctx = SSLContext.getInstance("SSL");
		//		ctx.init(null, myTrustManager, null);

		final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkClientTrusted(final java.security.cert.X509Certificate[] certs, final String authType) {
			}

			@Override
			public void checkServerTrusted(final java.security.cert.X509Certificate[] certs, final String authType) {
			}
		} };

		SSLContext ctx = null;
		try {
			ctx = SSLContext.getInstance("SSL");
			ctx.init(null, trustAllCerts, new java.security.SecureRandom());
		} catch (final NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (final KeyManagementException e) {
			e.printStackTrace();
		}

		final HostnameVerifier hostnameVerifier = new HostnameVerifier() {

			@Override
			public boolean verify(final String hostname, final SSLSession session) {
				System.out.println(hostname);
				// Should only connect to our server !
				return true;
			}
		};

		config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES,
				new HTTPSProperties(hostnameVerifier, ctx));

		final Client client = Client.create(config);

		//		System.out.println("Basic "+Base64.encode("tomcat:tomcat"));
		//		System.out.println("Basic dG9tY2F0OnRvbWNhdA==");
		//		System.out.println(Base64.d);
		// SSL
		// http://blogs.oracle.com/enterprisetechtips/entry/consuming_restful_web_services_with

		// Pour cr�er un header d'authentification : http://www.avajava.com/tutorials/lessons/how-do-i-use-basic-authentication-with-tomcat.html?page=2

		client.addFilter(new ClientFilter() {
			@Override
			public ClientResponse handle(final ClientRequest cr) throws ClientHandlerException {
				final ClientResponse response = getNext().handle(cr);
				return response;
			}
		});
		final Properties properties = new Properties();
		try {
			properties.load(getClass().getResourceAsStream("client.properties"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
		final String serverUrl = properties.getProperty("server.https.url");
		// Init web resource
		final WebResource webResource = client.resource(serverUrl);

		bind(WebResource.class).toInstance(webResource);

	}

}
