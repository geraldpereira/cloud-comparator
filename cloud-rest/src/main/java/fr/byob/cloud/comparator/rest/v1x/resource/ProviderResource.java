package fr.byob.cloud.comparator.rest.v1x.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.google.inject.Inject;

import fr.byob.cloud.comparator.commons.guice.InjectLogger;
import fr.byob.cloud.comparator.rest.v1x.V1XConstants;
import fr.byob.cloud.comparator.rest.v1x.bean.Locations;
import fr.byob.cloud.comparator.rest.v1x.manager.ProviderManager;

@Path(V1XConstants.PATH + "/provider/")
public class ProviderResource {

	@InjectLogger
	private Logger log;

	@Inject
	private ProviderManager providerManager;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/locations")
	public Locations locations() {
		final Locations locations = new Locations();
		locations.setLocations(providerManager.locations());
		return locations;
	}
}
