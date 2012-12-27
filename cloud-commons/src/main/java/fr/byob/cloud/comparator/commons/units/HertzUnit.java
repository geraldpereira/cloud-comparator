package fr.byob.cloud.comparator.commons.units;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum HertzUnit {
	HERTZ(new BigDecimal("1"), "Hz"),
	KILOHERTZ(new BigDecimal("1000"), "kHz"),
	MEGAHERTZ(new BigDecimal("1000000"), "MHz"),
	GIGAHERTZ(new BigDecimal("1000000000"), "GHz"),
	TERAHERTZ(new BigDecimal("1000000000000"), "THz"),
	PETAHERTZ(new BigDecimal("1000000000000000"), "PHz"),
	EXAHERTZ(new BigDecimal("1000000000000000000"), "EHz");

	private final BigDecimal hertzCount;
	private final String symbol;

	private HertzUnit(final BigDecimal hertzCount, final String symnol) {
		this.hertzCount = hertzCount;
		this.symbol = symnol;
	}

	public long toHertzValue(final BigDecimal value) {
		if (value == null) {
			return 0;
		}
		return value.multiply(hertzCount).longValue();
	}

	public BigDecimal convert(final BigDecimal value, final HertzUnit unit) {
		if (unit == this) {
			return value;
		}
		final long bytes = unit.toHertzValue(value);
		return new BigDecimal(bytes).divide(hertzCount, 4, RoundingMode.FLOOR);
	}

	@Override
	public String toString() {
		return symbol;
	}

	public static HertzUnit parseUnit(final String unitAsString) {
		for (final HertzUnit unit : values()){
			if (unit.symbol.equals(unitAsString)){
				return unit;
			}
		}
		return null;
	}
}
