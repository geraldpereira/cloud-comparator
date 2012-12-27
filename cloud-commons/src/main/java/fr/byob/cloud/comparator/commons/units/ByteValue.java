package fr.byob.cloud.comparator.commons.units;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ByteValue {

	private final static int BYTE1024 = 1024;

	private BigDecimal value;
	private ByteUnit unit;

	public ByteValue(final BigDecimal value, final ByteUnit unit){
		this.unit = computeBestUnit(value, unit);
		this.value = this.unit.convert(value, unit);
	}

	public ByteValue(final String value){
		try {
			final String[] split = value.split(" ");
			final String numberAsString= split [0];
			final String unitAsString= split [1];

			this.value =new BigDecimal(Double.parseDouble(numberAsString));
			this.unit = ByteUnit.parseUnit(unitAsString);
		} catch (final Throwable t) {
			this.value = null;
			this.unit = null;
		}
	}

	public BigDecimal toGigaByte() {
		if (value == null || unit == null) {
			return null;
		}
		return ByteUnit.GIGABYTE.convert(value, unit);
	}

	private ByteUnit computeBestUnit(final BigDecimal value, final ByteUnit unit){
		final long bytes = unit.toByteValue(value);
		if (bytes < BYTE1024) {
			return ByteUnit.BYTE;
		}
		int index = (int) (Math.log(bytes) / Math.log(BYTE1024));

		if (index < 0){
			index = 0;
		}

		if (index >= ByteUnit.values().length){
			index = ByteUnit.values().length -1;
		}

		final ByteUnit bestUnit = ByteUnit.values()[index];

		return bestUnit;
	}

	@Override
	public String toString() {
		if (value == null || unit == null) {
			return "null";
		}
		return value.setScale(0, RoundingMode.HALF_EVEN).toPlainString() + " "+unit.toString();
	}

}
