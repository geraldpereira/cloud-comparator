package fr.byob.cloud.comparator.commons.units;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CostValue {

	private final BigDecimal value;

	public CostValue(final BigDecimal value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "$" + value.setScale(2, RoundingMode.HALF_EVEN).toPlainString();
	}

}
