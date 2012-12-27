package fr.byob.cloud.comparator.rest.v1x.bean;

import java.util.List;

public class Locations {
	private List<String> locations;

	public Locations() {

	}

	public void setLocations(final List<String> locations) {
		this.locations = locations;
	}

	public List<String> getLocations() {
		return locations;
	}
}
