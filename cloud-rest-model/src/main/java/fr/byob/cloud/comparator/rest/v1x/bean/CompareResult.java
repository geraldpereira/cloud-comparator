package fr.byob.cloud.comparator.rest.v1x.bean;

import java.math.BigDecimal;

public class CompareResult {
	private String providerName;
	private String location;
	private BigDecimal cpu;
	private BigDecimal ram;
	private BigDecimal storage;
	private BigDecimal totalCost;
	private BigDecimal computeCost;
	private BigDecimal bandwidthCost;
	private BigDecimal bandwidthInCost;
	private BigDecimal bandwidthOutCost;

	/**
	 * Used by dozer and JAXB to initialize bean
	 */
	public CompareResult() {
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

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(final BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public BigDecimal getComputeCost() {
		return computeCost;
	}

	public void setComputeCost(final BigDecimal computeCost) {
		this.computeCost = computeCost;
	}

	public BigDecimal getStorage() {
		return storage;
	}

	public void setStorage(final BigDecimal storage) {
		this.storage = storage;
	}

	public BigDecimal getBandwidthCost() {
		return bandwidthCost;
	}

	public void setBandwidthCost(final BigDecimal bandwidthCost) {
		this.bandwidthCost = bandwidthCost;
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

	public BigDecimal getCpu() {
		return cpu;
	}

	public void setCpu(final BigDecimal cpu) {
		this.cpu = cpu;
	}

	public BigDecimal getRam() {
		return ram;
	}

	public void setRam(final BigDecimal ram) {
		this.ram = ram;
	}

}
