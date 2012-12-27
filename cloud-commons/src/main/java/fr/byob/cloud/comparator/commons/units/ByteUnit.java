package fr.byob.cloud.comparator.commons.units;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum ByteUnit {
	BYTE(new BigDecimal("1"), "B"),
	KILOBYTE(new BigDecimal("1024"), "kB"),
	MEGABYTE(new BigDecimal("1048576"), "MB"),
	GIGABYTE(new BigDecimal("1073741824"), "GB"),
	TERABYTE(new BigDecimal("1099511627776"), "TB"),
	PETABYTE(new BigDecimal("1125899906842624"), "PB"),
	EXABYTE(PETABYTE.byteCount.multiply(new BigDecimal("1024")), "EB");

	private final BigDecimal byteCount;
	private final String symbol;

	private ByteUnit(final BigDecimal byteCount, final String symnol) {
		this.byteCount = byteCount;
		this.symbol = symnol;
	}

	public long toByteValue(final BigDecimal value) {
		if (value == null) {
			return 0;
		}
		return value.multiply(byteCount).longValue();
	}

	public BigDecimal convert(final BigDecimal value, final ByteUnit unit) {
		if (unit == this) {
			return value;
		}
		final long bytes = unit.toByteValue(value);
		return new BigDecimal(bytes).divide(byteCount, 4, RoundingMode.CEILING);
	}

	@Override
	public String toString() {
		return symbol;
	}

	public static ByteUnit parseUnit(final String unitAsString) {
		for (final ByteUnit unit : values()){
			if (unit.symbol.equals(unitAsString)){
				return unit;
			}
		}
		return null;
	}
}
