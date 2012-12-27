package fr.byob.cloud.comparator.simple.client.manager;

import java.util.List;

import fr.byob.cloud.comparator.rest.v1x.bean.CompareRequest;
import fr.byob.cloud.comparator.rest.v1x.bean.CompareResult;

public interface CompareClientResource {
	public List<CompareResult> compare(final CompareRequest request);
}
