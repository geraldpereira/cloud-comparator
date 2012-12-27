package fr.byob.cloud.comparator.rest.v1x.bean;

import java.math.BigDecimal;

public class CompareRequest {
	private Integer instancesCount;
	private Integer hoursPerMonth;
	private BigDecimal cpu; // in GHz
	private BigDecimal ram; // in GB
	private BigDecimal storage; // in GB
	private BigDecimal bandwidthIn; // in GB / month
	private BigDecimal bandwidthOut; // in GB / month
	private String location;

	/**
	 * Used by dozer and JAXB to initialize bean
	 */
	public CompareRequest() {
		hoursPerMonth = 1;
	}

	public void setHoursPerMonth(final Integer hoursPerMonth) {
		this.hoursPerMonth = hoursPerMonth;
	}

	public Integer getHoursPerMonth() {
		return hoursPerMonth;
	}

	public Integer getInstancesCount() {
		return instancesCount;
	}

	public void setInstancesCount(final Integer instancesCount) {
		this.instancesCount = instancesCount;
	}

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

	public BigDecimal getStorage() {
		return storage;
	}

	public void setStorage(final BigDecimal storage) {
		this.storage = storage;
	}

	public BigDecimal getBandwidthIn() {
		return bandwidthIn;
	}

	public void setBandwidthIn(final BigDecimal bandwidthIn) {
		this.bandwidthIn = bandwidthIn;
	}

	public BigDecimal getBandwidthOut() {
		return bandwidthOut;
	}

	public void setBandwidthOut(final BigDecimal bandwidthOut) {
		this.bandwidthOut = bandwidthOut;
	}

	@Override
	public String toString() {
		return "CompareRequest [instancesCount=" + instancesCount + ", hoursPerMonth=" + hoursPerMonth + ", cpu=" + cpu
				+ ", ram=" + ram + ", storage=" + storage + ", bandwidthIn=" + bandwidthIn + ", bandwidthOut="
				+ bandwidthOut + ", location=" + location + "]";
	}
	
	
}
