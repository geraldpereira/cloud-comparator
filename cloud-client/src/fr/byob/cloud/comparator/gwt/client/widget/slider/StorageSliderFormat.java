package fr.byob.cloud.comparator.gwt.client.widget.slider;


import java.util.HashMap;
import java.util.Map;


public class StorageSliderFormat extends CloudSliderModel {
	private final static HashMap<Double, String> values = new HashMap<Double, String>();
	static {
		values.put(0.0d, "Any");
		values.put(1.0d, "1 GB");
		values.put(2.0d, "2 GB");
		values.put(3.0d, "5 GB");
		values.put(4.0d, "10 GB");
		values.put(5.0d, "20 GB");
		values.put(6.0d, "50 GB");
		values.put(7.0d, "100 GB");
		values.put(8.0d, "200 GB");
		values.put(9.0d, "500 GB");
		values.put(10.0d, "1 TB");
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
		return 10;
	}

	@Override
	protected double getStep() {
		return 1;
	}

}
