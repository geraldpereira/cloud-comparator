package fr.byob.cloud.comparator.simple.client.manager.impl;

import java.util.List;

import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.sun.jersey.api.client.WebResource;

import fr.byob.cloud.comparator.rest.v1x.bean.CompareRequest;
import fr.byob.cloud.comparator.rest.v1x.bean.CompareResult;
import fr.byob.cloud.comparator.rest.v1x.bean.CompareResults;
import fr.byob.cloud.comparator.simple.client.guice.AuthorizationHeader;
import fr.byob.cloud.comparator.simple.client.manager.CompareClientResource;

public class CompareClientResourceImpl extends AbstractClientResource implements CompareClientResource {

	@Inject
	private WebResource webResource;

	@Inject
	public CompareClientResourceImpl(@AuthorizationHeader final String authorization) {
		super(authorization);
	}

	@Override
	public List<CompareResult> compare(final CompareRequest request) {
		final CompareResults results = webResource.path("compare/result").header("authorization", authorization).type(
				MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).post(CompareResults.class,
				request);

		return results.getResults();
	}

}
