package fr.byob.cloud.comparator.db.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fr.byob.cloud.comparator.db.bean.ProviderSearchCriteria;
import fr.byob.cloud.comparator.db.bean.SimpleProvider;
import fr.byob.cloud.comparator.db.bean.SizedProvider;
import fr.byob.cloud.comparator.db.guice.CloudMyBatisModule;
import fr.byob.cloud.comparator.db.guice.DAOModule;

public class ProviderDAOTest {
	private ProviderDAO providerDAO;
	private static Injector injector;

	@BeforeClass
	public static void beforeClass() {
		injector = Guice.createInjector(new CloudMyBatisModule(), new DAOModule());
	}

	@Before
	public void before() {
		providerDAO = injector.getInstance(ProviderDAO.class);
		Assert.assertNotNull(providerDAO);
	}

	@Test
	public void searchSimples() {
		final ProviderSearchCriteria criteria = new ProviderSearchCriteria();
		criteria.setLocation("eu");
		criteria.setCpu(BigDecimal.valueOf(1));
		criteria.setRam(BigDecimal.valueOf(1));

		final List<SimpleProvider> simples = providerDAO.searchSimples(criteria);
		Assert.assertEquals(1, simples.size());
		Assert.assertEquals("cloudsigma", simples.get(0).getId());
		Assert.assertEquals("eu_west_1", simples.get(0).getLocation());
	}

	@Test
	public void searchSized() {
		final ProviderSearchCriteria criteria = new ProviderSearchCriteria();
		criteria.setLocation("us");
		criteria.setCpu(BigDecimal.valueOf(2));
		criteria.setRam(BigDecimal.valueOf(0.5));
		criteria.setStorage(BigDecimal.valueOf(10));

		final List<SizedProvider> sized = providerDAO.searchSized(criteria);
		Assert.assertEquals(3, sized.size());
		Assert.assertEquals("aws_ec2", sized.get(0).getId());
		Assert.assertEquals("us_west_1", sized.get(1).getLocation());
		Assert.assertEquals("c1.medium", sized.get(0).getSizes().get(0).getSize());
		Assert.assertEquals("c1.medium", sized.get(1).getSizes().get(0).getSize());
	}

	@Test
	public void searchSizedNullRamAndStorage() {
		final ProviderSearchCriteria criteria = new ProviderSearchCriteria();
		criteria.setLocation("eu");
		criteria.setRam(BigDecimal.valueOf(0.25));

		final List<SizedProvider> sized = providerDAO.searchSized(criteria);

		Assert.assertEquals(2, sized.size());
		Assert.assertEquals("aws_ec2", sized.get(0).getId());
		Assert.assertEquals("rackspace", sized.get(1).getId());
		Assert.assertEquals("eu_west_3", sized.get(0).getLocation());
		Assert.assertEquals("eu_west_2", sized.get(1).getLocation());
		Assert.assertEquals("t1.micro", sized.get(0).getSizes().get(0).getSize());
		Assert.assertEquals("1", sized.get(1).getSizes().get(0).getSize());
	}

	@Test
	public void searchNoCPU() {
		final ProviderSearchCriteria criteria = new ProviderSearchCriteria();
		criteria.setLocation("us_center");
		criteria.setRam(BigDecimal.valueOf(0.25));

		final List<SizedProvider> sized = providerDAO.searchSized(criteria);

		Assert.assertEquals(1, sized.size());
		Assert.assertEquals("rackspace", sized.get(0).getId());
		Assert.assertEquals("us_center_1", sized.get(0).getLocation());
		Assert.assertEquals("1", sized.get(0).getSizes().get(0).getSize());
	}

	@Test
	public void selectLocations() {
		final List<String> locations = providerDAO.selectLocations();
		Assert.assertEquals(Arrays.asList("ap_northeast_1", "ap_southeast_1", "eu_west_1", "eu_west_2", "eu_west_3",
				"us_center_1", "us_east_1", "us_west_1", "us_west_2", "us_west_3"), locations);
	}



}


