package fr.byob.cloud.comparator.db.mappers;

import java.math.BigDecimal;
import java.util.List;

import junit.framework.Assert;

import org.apache.ibatis.session.SqlSessionManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fr.byob.cloud.comparator.db.bean.Provider;
import fr.byob.cloud.comparator.db.bean.ProviderSearchCriteria;
import fr.byob.cloud.comparator.db.bean.SimpleProvider;
import fr.byob.cloud.comparator.db.bean.SizedProvider;
import fr.byob.cloud.comparator.db.guice.CloudMyBatisModule;

public class ProviderMapperTest {

	private ProviderMapper providerMapper;
	private SqlSessionManager ssm;
	private static Injector injector;

	@BeforeClass
	public static void beforeClass() {
		injector = Guice.createInjector(new CloudMyBatisModule());
	}

	@Before
	public void before() {
		ssm = injector.getInstance(SqlSessionManager.class);
		providerMapper = injector.getInstance(ProviderMapper.class);
		Assert.assertNotNull(providerMapper);
	}

	@Test
	public void selectAll() {
		ssm.startManagedSession();
		try {
			final List<Provider> result = providerMapper.selectAll();
			Assert.assertEquals(10, result.size());
		} finally {
			ssm.rollback();
			ssm.close();
		}
	}

	@Test
	public void select() {
		ssm.startManagedSession();
		try {
			final Provider searchCriteria = new Provider("aws_ec2", "us_east_1");
			final Provider result = providerMapper.select(searchCriteria);

			Assert.assertEquals(0.12d, result.getBandwidthOutCost().doubleValue());
		} finally {
			ssm.rollback();
			ssm.close();
		}
	}

	@Test
	public void searchSimples() {
		ssm.startManagedSession();
		try {
			final ProviderSearchCriteria criteria = new ProviderSearchCriteria();
			criteria.setLocation("eu%");
			criteria.setCpu(BigDecimal.valueOf(1));
			criteria.setRam(BigDecimal.valueOf(1));

			final List<SimpleProvider> simples = providerMapper.searchSimples(criteria);

			Assert.assertEquals(1, simples.size());
			Assert.assertEquals("cloudsigma", simples.get(0).getId());
			Assert.assertEquals("eu_west_1", simples.get(0).getLocation());
		} finally {
			ssm.rollback();
			ssm.close();
		}
	}

	@Test
	public void searchSized() {
		ssm.startManagedSession();
		try {
			final ProviderSearchCriteria criteria = new ProviderSearchCriteria();
			criteria.setLocation("us%");
			criteria.setCpu(BigDecimal.valueOf(2));
			criteria.setRam(BigDecimal.valueOf(0.5));
			criteria.setStorage(BigDecimal.valueOf(10));

			final List<SizedProvider> sized = providerMapper.searchSized(criteria);

			Assert.assertEquals(3, sized.size());
			Assert.assertEquals("aws_ec2", sized.get(0).getId());
			Assert.assertEquals("us_west_1", sized.get(1).getLocation());
			Assert.assertEquals("c1.medium", sized.get(0).getSizes().get(0).getSize());
			Assert.assertEquals("c1.medium", sized.get(1).getSizes().get(0).getSize());
		} finally {
			ssm.rollback();
			ssm.close();
		}
	}

	@Test
	public void searchSizedNoLocation() {
		ssm.startManagedSession();
		try {
			final ProviderSearchCriteria criteria = new ProviderSearchCriteria();
			criteria.setCpu(BigDecimal.valueOf(2));
			criteria.setRam(BigDecimal.valueOf(0.5));
			criteria.setStorage(BigDecimal.valueOf(10));

			final List<SizedProvider> sized = providerMapper.searchSized(criteria);

			Assert.assertEquals(6, sized.size());
		} finally {
			ssm.rollback();
			ssm.close();
		}
	}

	@Test
	public void searchSizedNullRamAndStorage() {
		ssm.startManagedSession();
		try {
			final ProviderSearchCriteria criteria = new ProviderSearchCriteria();
			criteria.setLocation("eu%");
			criteria.setRam(BigDecimal.valueOf(0.25));

			final List<SizedProvider> sized = providerMapper.searchSized(criteria);

			Assert.assertEquals(2, sized.size());
			Assert.assertEquals("aws_ec2", sized.get(0).getId());
			Assert.assertEquals("rackspace", sized.get(1).getId());
			Assert.assertEquals("eu_west_3", sized.get(0).getLocation());
			Assert.assertEquals("eu_west_2", sized.get(1).getLocation());
			Assert.assertEquals("t1.micro", sized.get(0).getSizes().get(0).getSize());
			Assert.assertEquals("1", sized.get(1).getSizes().get(0).getSize());
		} finally {
			ssm.rollback();
			ssm.close();
		}
	}
}
