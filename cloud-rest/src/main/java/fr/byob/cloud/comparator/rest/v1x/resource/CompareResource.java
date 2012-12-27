package fr.byob.cloud.comparator.rest.v1x.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.google.inject.Inject;

import fr.byob.cloud.comparator.commons.guice.InjectLogger;
import fr.byob.cloud.comparator.rest.v1x.V1XConstants;
import fr.byob.cloud.comparator.rest.v1x.bean.CompareRequest;
import fr.byob.cloud.comparator.rest.v1x.bean.CompareResult;
import fr.byob.cloud.comparator.rest.v1x.bean.CompareResults;
import fr.byob.cloud.comparator.rest.v1x.manager.CompareManager;

@Path(V1XConstants.PATH + "/compare/")
public class CompareResource {

	@InjectLogger
	private Logger log;

	@Inject
	private CompareManager compareManager;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/result")
	public CompareResults compare(final CompareRequest request) {
		log.info(request.toString());
		final CompareResults results = new CompareResults();
		final List<CompareResult> resultsList = compareManager.compare(request);
		results.setResults(resultsList);
		return results;
	}
}
