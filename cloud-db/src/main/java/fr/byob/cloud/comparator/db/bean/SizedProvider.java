package fr.byob.cloud.comparator.db.bean;

import java.util.List;

public class SizedProvider extends Provider {
	private List<ProviderComputeSize> sizes;

	public SizedProvider(final String id, final String location) {
		super(id, location);
	}

	public void setSizes(final List<ProviderComputeSize> sizes) {
		this.sizes = sizes;
	}

	public List<ProviderComputeSize> getSizes() {
		return sizes;
	}

	@Override
	public String toString() {
		return "SizedProvider [getId()=" + getId() + ", getLocation()=" + getLocation() + ", getBandwidthInCost()="
				+ getBandwidthInCost() + ", getBandwidthOutCost()=" + getBandwidthOutCost() + "]";
	}

	
}
