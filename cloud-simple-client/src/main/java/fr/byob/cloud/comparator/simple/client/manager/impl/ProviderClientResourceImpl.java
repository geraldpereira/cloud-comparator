package fr.byob.cloud.comparator.simple.client.manager.impl;

import java.util.List;

import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.sun.jersey.api.client.WebResource;

import fr.byob.cloud.comparator.rest.v1x.bean.Locations;
import fr.byob.cloud.comparator.simple.client.guice.AuthorizationHeader;
import fr.byob.cloud.comparator.simple.client.manager.ProviderClientResource;

public class ProviderClientResourceImpl extends AbstractClientResource implements ProviderClientResource {

	@Inject
	private WebResource webResource;

	@Inject
	public ProviderClientResourceImpl(@AuthorizationHeader final String authorization) {
		super(authorization);
	}

	@Override
	public List<String> locations() {
		final Locations locations = webResource.path("provider/locations").header("authorization", authorization).type(
				MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get(Locations.class);
		return locations.getLocations();
	}

}
