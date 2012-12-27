package fr.byob.cloud.comparator.commons.units;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UnitsTest {


	@BeforeClass
	public static void beforeClass() {
	}

	@Before
	public void before() {
	}

	@Test
	public void byteUnitTest() {
		ByteValue value = new ByteValue(new BigDecimal("0.1"), ByteUnit.MEGABYTE);
		Assert.assertEquals("102 kB", value.toString());
		Assert.assertEquals("0.0001", value.toGigaByte().toPlainString());
		value = new ByteValue("102 kB");
		Assert.assertEquals("102 kB", value.toString());
		Assert.assertEquals("0.0001", value.toGigaByte().toPlainString());

		value = new ByteValue(new BigDecimal("1025"), ByteUnit.MEGABYTE);
		Assert.assertEquals("1 GB",value.toString());
		Assert.assertEquals(new BigDecimal("1.0010"), value.toGigaByte()); // Rounded to 1GB value
		value = new ByteValue("1 GB");
		Assert.assertEquals("1 GB",value.toString());
		Assert.assertEquals(new BigDecimal("1"), value.toGigaByte());

		value = new ByteValue(new BigDecimal("0.5"), ByteUnit.GIGABYTE);
		Assert.assertEquals("512 MB",value.toString());
		Assert.assertEquals(new BigDecimal("0.5000"), value.toGigaByte());
		value = new ByteValue("512 MB");
		Assert.assertEquals("512 MB",value.toString());
		Assert.assertEquals(new BigDecimal("0.5000"), value.toGigaByte());

		value = new ByteValue(new BigDecimal("0.2500"), ByteUnit.GIGABYTE);
		Assert.assertEquals("256 MB",value.toString());
		Assert.assertEquals(new BigDecimal("0.2500"), value.toGigaByte());
		value = new ByteValue("256 MB");
		Assert.assertEquals("256 MB",value.toString());
		Assert.assertEquals(new BigDecimal("0.2500"), value.toGigaByte());

		value = new ByteValue(new BigDecimal("0.7500"), ByteUnit.GIGABYTE);
		Assert.assertEquals("768 MB",value.toString());
		Assert.assertEquals(new BigDecimal("0.7500"), value.toGigaByte());
		value = new ByteValue("768 MB");
		Assert.assertEquals("768 MB",value.toString());
		Assert.assertEquals(new BigDecimal("0.7500"), value.toGigaByte());
	}

	@Test
	public void hertzUnitTest() {
		HertzValue value = new HertzValue(new BigDecimal("0.150"), HertzUnit.MEGAHERTZ);
		Assert.assertEquals("150 kHz", value.toString());
		Assert.assertEquals("0.0001", value.toGigaHertz().toPlainString());
		value = new HertzValue("150 kHz");
		Assert.assertEquals("150 kHz", value.toString());
		Assert.assertEquals("0.0001", value.toGigaHertz().toPlainString());

		value = new HertzValue(new BigDecimal(1025d), HertzUnit.MEGAHERTZ);
		Assert.assertEquals("1 GHz", value.toString());
		Assert.assertEquals(new BigDecimal("1.0250"), value.toGigaHertz()); // Rounded to 1GB value
		value = new HertzValue("1 GHz");
		Assert.assertEquals("1 GHz", value.toString());
		Assert.assertEquals(new BigDecimal("1"), value.toGigaHertz());

		value = new HertzValue(new BigDecimal(0.5d), HertzUnit.GIGAHERTZ);
		Assert.assertEquals("500 MHz", value.toString());
		Assert.assertEquals(new BigDecimal("0.5000"), value.toGigaHertz());
		value = new HertzValue("500 MHz");
		Assert.assertEquals("500 MHz", value.toString());
		Assert.assertEquals(new BigDecimal("0.5000"), value.toGigaHertz());

		value = new HertzValue(new BigDecimal("0.2500"), HertzUnit.GIGAHERTZ);
		Assert.assertEquals("250 MHz", value.toString());
		Assert.assertEquals(new BigDecimal("0.2500"), value.toGigaHertz());
		value = new HertzValue("250 MHz");
		Assert.assertEquals("250 MHz", value.toString());
		Assert.assertEquals(new BigDecimal("0.2500"), value.toGigaHertz());

		value = new HertzValue(new BigDecimal("0.7500"), HertzUnit.GIGAHERTZ);
		Assert.assertEquals("750 MHz", value.toString());
		Assert.assertEquals(new BigDecimal("0.7500"), value.toGigaHertz());
		value = new HertzValue("750 MHz");
		Assert.assertEquals("750 MHz", value.toString());
		Assert.assertEquals(new BigDecimal("0.7500"), value.toGigaHertz());
	}
}
