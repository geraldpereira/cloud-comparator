package fr.byob.cloud.comparator.db.bean;

import java.math.BigDecimal;

public class ProviderComputeSize {
	private Provider provider;
	private final String size;
	private BigDecimal ram; 
	private BigDecimal cpu; 
	private BigDecimal storage; 
	private BigDecimal cost; // for one hour

	public ProviderComputeSize(final String size) {
		super();
		this.size = size;
	}

	public void setCpu(final BigDecimal cpu) {
		this.cpu = cpu;
	}

	public void setRam(final BigDecimal ram) {
		this.ram = ram;
	}

	public BigDecimal getCpu() {
		return cpu;
	}

	public BigDecimal getRam() {
		return ram;
	}
	
	public void setStorage(BigDecimal storage) {
		this.storage = storage;
	}
	
	public BigDecimal getStorage() {
		return storage;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(final Provider provider) {
		this.provider = provider;
	}


	public String getSize() {
		return size;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(final BigDecimal cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "ProviderComputeSize [size=" + size + ", ram=" + ram + ", cpu=" + cpu + ", cost=" + cost + "]";
	}

}
