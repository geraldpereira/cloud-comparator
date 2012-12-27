package fr.byob.cloud.comparator.simple.client.guice;

import com.google.inject.AbstractModule;

import fr.byob.cloud.comparator.simple.client.manager.CompareClientResource;
import fr.byob.cloud.comparator.simple.client.manager.ProviderClientResource;
import fr.byob.cloud.comparator.simple.client.manager.impl.CompareClientResourceImpl;
import fr.byob.cloud.comparator.simple.client.manager.impl.ProviderClientResourceImpl;

public class ClientManagerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(CompareClientResource.class).to(CompareClientResourceImpl.class);
		bind(ProviderClientResource.class).to(ProviderClientResourceImpl.class);
	}

}
