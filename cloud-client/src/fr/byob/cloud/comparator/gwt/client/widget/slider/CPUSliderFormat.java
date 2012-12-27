package fr.byob.cloud.comparator.gwt.client.widget.slider;


import java.util.HashMap;
import java.util.Map;


public class CPUSliderFormat extends CloudSliderModel {
	private final static HashMap<Double, String> values = new HashMap<Double, String>();
	static {
		values.put(0.0d, "Any");
		values.put(1.0d, "1 GHz");
		values.put(2.0d, "2 GHz");
		values.put(3.0d, "3 GHz");
		values.put(4.0d, "4 GHz");
		values.put(5.0d, "8 GHz");
		values.put(6.0d, "12 GHz");
		values.put(7.0d, "16 GHz");
		values.put(8.0d, "32 GHz");
		values.put(9.0d, "64 GHz");
	}


	@Override
	protected Map<Double, String> getValuesMap() {
		return values;
	}

	@Override
	protected double getMinValue() {
		return 0;
	}

	@Override
	protected double getMaxValue() {
		return 9;
	}

	@Override
	protected double getStep() {
		return 1;
	}


}
