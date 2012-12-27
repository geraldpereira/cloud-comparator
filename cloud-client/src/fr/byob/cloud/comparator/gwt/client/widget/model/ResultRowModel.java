package fr.byob.cloud.comparator.gwt.client.widget.model;

import java.io.Serializable;

public class ResultRowModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3087231493007659164L;

	private String providerName;
	private String location;
	private String cpu;
	private String ram;
	private String storage;
	private String totalCost;
	private String computeCost;
	private String bandwidthCost;
	private String bandwidthInCost;
	private String bandwidthOutCost;
	private double cost;

	public void setCost(final double cost) {
		this.cost = cost;
	}

	public double getCost() {
		return cost;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(final String providerName) {
		this.providerName = providerName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(final String location) {
		this.location = location;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(final String cpu) {
		this.cpu = cpu;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(final String ram) {
		this.ram = ram;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(final String storage) {
		this.storage = storage;
	}

	public String getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(final String totalCost) {
		this.totalCost = totalCost;
	}

	public String getComputeCost() {
		return computeCost;
	}

	public void setComputeCost(final String computeCost) {
		this.computeCost = computeCost;
	}


	public String getBandwidthCost() {
		return bandwidthCost;
	}

	public void setBandwidthCost(final String bandwidthCost) {
		this.bandwidthCost = bandwidthCost;
	}

	public String getBandwidthInCost() {
		return bandwidthInCost;
	}

	public void setBandwidthInCost(final String bandwidthInCost) {
		this.bandwidthInCost = bandwidthInCost;
	}

	public String getBandwidthOutCost() {
		return bandwidthOutCost;
	}

	public void setBandwidthOutCost(final String bandwidthOutCost) {
		this.bandwidthOutCost = bandwidthOutCost;
	}

}
