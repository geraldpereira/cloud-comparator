package fr.byob.cloud.comparator.rest.v1x.bean;

import java.util.List;

public class CompareResults {
	private List<CompareResult> results;

	public CompareResults() {

	}

	public List<CompareResult> getResults() {
		return results;
	}

	public void setResults(final List<CompareResult> results) {
		this.results = results;
	}
}
