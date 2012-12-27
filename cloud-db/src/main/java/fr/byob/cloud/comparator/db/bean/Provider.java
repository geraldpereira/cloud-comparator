package fr.byob.cloud.comparator.db.bean;

import java.math.BigDecimal;

public class Provider {

	private final String id;
	private final String location;
	private BigDecimal bandwidthInCost; // per GB
	private BigDecimal bandwidthOutCost; // per GB

	public Provider(final String id, final String location) {
		super();
		this.id = id;
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}

	public BigDecimal getBandwidthInCost() {
		return bandwidthInCost;
	}

	public void setBandwidthInCost(final BigDecimal bandwidthInCost) {
		this.bandwidthInCost = bandwidthInCost;
	}

	public BigDecimal getBandwidthOutCost() {
		return bandwidthOutCost;
	}

	public void setBandwidthOutCost(final BigDecimal bandwidthOutCost) {
		this.bandwidthOutCost = bandwidthOutCost;
	}

	@Override
	public String toString() {
		return "Provider [id=" + id + ", location=" + location + ", bandwidthInCost=" + bandwidthInCost
				+ ", bandwidthOutCost=" + bandwidthOutCost + "]";
	}


}
