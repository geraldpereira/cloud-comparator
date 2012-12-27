package fr.byob.cloud.comparator.db.bean;

import java.math.BigDecimal;

public class SimpleProvider extends Provider {
	private BigDecimal cpuCost;
	private BigDecimal ramCost;
	private BigDecimal storageCost;
	private BigDecimal maxCpu;
	private BigDecimal maxRam; 
	private BigDecimal maxStorage;

	public SimpleProvider(final String id, final String location) {
		super(id, location);
	}

	public BigDecimal getCpuCost() {
		return cpuCost;
	}

	public void setCpuCost(BigDecimal cpuCost) {
		this.cpuCost = cpuCost;
	}

	public BigDecimal getRamCost() {
		return ramCost;
	}

	public void setRamCost(BigDecimal ramCost) {
		this.ramCost = ramCost;
	}

	public BigDecimal getStorageCost() {
		return storageCost;
	}

	public void setStorageCost(BigDecimal storageCost) {
		this.storageCost = storageCost;
	}

	public BigDecimal getMaxCpu() {
		return maxCpu;
	}

	public void setMaxCpu(BigDecimal maxCpu) {
		this.maxCpu = maxCpu;
	}

	public BigDecimal getMaxRam() {
		return maxRam;
	}

	public void setMaxRam(BigDecimal maxRam) {
		this.maxRam = maxRam;
	}

	public BigDecimal getMaxStorage() {
		return maxStorage;
	}

	public void setMaxStorage(BigDecimal maxStorage) {
		this.maxStorage = maxStorage;
	}

	@Override
	public String toString() {
		return "SizedProvider [getId()=" + getId() + ", getLocation()=" + getLocation() + ", getBandwidthInCost()="
				+ getBandwidthInCost() + ", getBandwidthOutCost()=" + getBandwidthOutCost() + "]";
	}


}
