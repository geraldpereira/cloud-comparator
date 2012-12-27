package fr.byob.cloud.comparator.rest.v1x.resource;

import java.util.Arrays;
import java.util.List;

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
import fr.byob.cloud.comparator.rest.v1x.bean.Locations;

public class ProviderResourceTest {

	private ProviderResource providerResource;
	private static Injector injector;

	@BeforeClass
	public static void beforeClass() {
		injector = Guice.createInjector(new LoggerModule(), new DAOModule(), new CloudMyBatisModule(),
				new CloudJerseyServletModule(),
				new DozerModule());

	}

	@Before
	public void before() {
		providerResource = injector.getInstance(ProviderResource.class);
		Assert.assertNotNull(providerResource);
	}

	@Test
	public void locations() {
		final Locations locations = providerResource.locations();

		final List<String> expected = Arrays.asList("ap", "eu", "us");
		Assert.assertEquals(expected, locations.getLocations());
	}

}
