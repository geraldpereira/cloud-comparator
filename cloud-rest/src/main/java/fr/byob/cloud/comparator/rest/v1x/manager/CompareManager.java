package fr.byob.cloud.comparator.rest.v1x.manager;

import java.util.List;

import fr.byob.cloud.comparator.rest.v1x.bean.CompareRequest;
import fr.byob.cloud.comparator.rest.v1x.bean.CompareResult;

public interface CompareManager {
	public List<CompareResult> compare(final CompareRequest request);
}
