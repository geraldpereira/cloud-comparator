package fr.byob.cloud.comparator.gwt.client.widget.slider;

import java.util.Map;

import com.google.gwt.widgetideas.client.SliderBar;
import com.google.gwt.widgetideas.client.SliderBar.LabelFormatter;

public abstract class CloudSliderModel implements LabelFormatter {

	protected abstract Map<Double, String> getValuesMap();

	protected abstract double getMinValue();

	protected abstract double getMaxValue();

	protected abstract double getStep();

	@Override
	public String formatLabel(final SliderBar slider, final double value) {
		return getValuesMap().get(value);
	}


}
