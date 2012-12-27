package fr.byob.cloud.comparator.simple.client;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fr.byob.cloud.comparator.commons.guice.LoggerModule;
import fr.byob.cloud.comparator.simple.client.guice.ClientManagerModule;
import fr.byob.cloud.comparator.simple.client.guice.HTTPClientModule;
import fr.byob.cloud.comparator.simple.client.manager.ProviderClientResource;

/**
 * Warning : this test needs the rest application to be deployed on tomcat
 *
 *
 * @author Kojiro
 *
 */
public class ProviderResourceTest {

	private ProviderClientResource compareResource;
	private static Injector injector;

	@BeforeClass
	public static void beforeClass() {
		injector = Guice.createInjector(new LoggerModule(), new HTTPClientModule(), new ClientManagerModule());
	}

	@Before
	public void before() {
		compareResource = injector.getInstance(ProviderClientResource.class);
		Assert.assertNotNull(compareResource);
	}

	@Test
	public void locationsTest() {

		final List<String> locations = compareResource.locations();

		final List<String> expected = Arrays.asList("ap", "eu", "us");
		Assert.assertEquals(expected, locations);
	}

}
