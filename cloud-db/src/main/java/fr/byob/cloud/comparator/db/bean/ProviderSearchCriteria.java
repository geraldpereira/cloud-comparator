package fr.byob.cloud.comparator.db.bean;

import java.math.BigDecimal;

public class ProviderSearchCriteria {
	private String location;
	private BigDecimal cpu;
	private BigDecimal ram;
	private BigDecimal storage;

	public String getLocation() {
		return location;
	}

	public void setLocation(final String location) {
		this.location = location;
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
	
	public void setStorage(BigDecimal storage) {
		this.storage = storage;
	}
	
	public BigDecimal getStorage() {
		return storage;
	}

}
