package fr.byob.cloud.comparator.db.guice;

import com.google.inject.AbstractModule;

import fr.byob.cloud.comparator.db.dao.ProviderDAO;
import fr.byob.cloud.comparator.db.dao.impl.ProviderDAOImpl;

public class DAOModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ProviderDAO.class).to(ProviderDAOImpl.class);
	}

}
