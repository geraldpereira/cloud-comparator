package fr.byob.cloud.comparator.db.guice;

import java.io.Reader;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.log4j.Logger;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.builtin.PooledDataSourceProvider;

import com.google.inject.name.Names;

import fr.byob.cloud.comparator.db.bean.Provider;
import fr.byob.cloud.comparator.db.bean.ProviderComputeSize;
import fr.byob.cloud.comparator.db.bean.ProviderSearchCriteria;
import fr.byob.cloud.comparator.db.bean.SimpleProvider;
import fr.byob.cloud.comparator.db.bean.SizedProvider;
import fr.byob.cloud.comparator.db.mappers.ProviderMapper;

public class CloudMyBatisModule extends MyBatisModule {

	private static final Logger log = Logger.getLogger(CloudMyBatisModule.class);

	@Override
	protected void initialize() {
		// install(JdbcHelper.PostgreSQL);
		log.debug("Initializing DbBatisModule ...");

		bindDataSourceProviderType(PooledDataSourceProvider.class);
		bindTransactionFactoryType(JdbcTransactionFactory.class);

		try {
			final Properties properties = Resources.getResourceAsProperties("cloud.properties");
			BeanUtils.copyProperties(this, properties);

			Names.bindProperties(binder(), properties);
			final String initScript = properties.getProperty("byob.initScript");

			if (initScript != null) {
				final String driver = properties.getProperty("JDBC.driver");
				final String url = properties.getProperty("JDBC.url");
				final String username = properties.getProperty("JDBC.username");
				final String password = properties.getProperty("JDBC.password");
				final UnpooledDataSource dataSource = new UnpooledDataSource(driver, url, username, password);
				dataSource.setAutoCommit(false);
				final ScriptRunner scriptRunner = new ScriptRunner(dataSource.getConnection());

				final Reader reader = Resources.getResourceAsReader(initScript);
				scriptRunner.runScript(reader);
			}

		} catch (final Exception e) {
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}

		addMapperClass(ProviderMapper.class);
		addSimpleAlias(Provider.class);
		addSimpleAlias(SimpleProvider.class);
		addSimpleAlias(SizedProvider.class);
		addSimpleAlias(ProviderComputeSize.class);
		addSimpleAlias(ProviderSearchCriteria.class);

		log.debug("Initialization of DbBatisModule done.");

		// handleType(CustomType.class).with(CustomLongTypeHandler.class);
		// handleType(Address.class).with(AddressTypeHandler.class);
		// addInterceptorClass(CountUpdateInterceptor.class);
		// addSimpleAlias(com.acme.Foo.class);
		// addAlias("MyBar", com.acme.Bar.class);
		// handleType(com.acme.Foo.class).with(com.acme.dao.FooHandler.class);
		// handleType(com.acme.Bar.class).with(com.acme.dao.BarHandler.class);
		// addMapperClass(com.acme.dao.FooMapper.class);
		// addMapperClass(com.acme.dao.BarMapper.class);
		// lazyLoadingEnabled(true);
		// bindObjectFactoryType(com.acme.MyObjectFactoryProvider.class);
	}

}
