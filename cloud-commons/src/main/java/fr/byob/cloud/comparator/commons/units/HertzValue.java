package fr.byob.cloud.comparator.commons.units;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HertzValue {

	private final static int HERTZ1000 = 1000;

	private BigDecimal value;
	private HertzUnit unit;

	public HertzValue(final BigDecimal value, final HertzUnit unit) {
		this.unit = computeBestUnit(value, unit);
		this.value = this.unit.convert(value, unit);
	}

	public HertzValue(final String value){
		try {
			final String[] split = value.split(" ");
			final String numberAsString= split [0];
			final String unitAsString = split[1];

			this.value =new BigDecimal(Double.parseDouble(numberAsString));
			this.unit = HertzUnit.parseUnit(unitAsString);
		} catch (final Throwable t) {
			// failed to parse the value
			this.value = null;
			this.unit = null;
		}
	}

	public BigDecimal toGigaHertz() {
		if (value == null || unit == null) {
			return null;
		}
		return HertzUnit.GIGAHERTZ.convert(value, unit);
	}

	private HertzUnit computeBestUnit(final BigDecimal value, final HertzUnit unit) {
		final long hertz = unit.toHertzValue(value);
		if (hertz < HERTZ1000) {
			return HertzUnit.HERTZ;
		}
		int index = (int) (Math.log(hertz) / Math.log(HERTZ1000));

		if (index < 0){
			index = 0;
		}

		if (index >= ByteUnit.values().length){
			index = ByteUnit.values().length - 1;
		}

		final HertzUnit bestUnit = HertzUnit.values()[index];

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
