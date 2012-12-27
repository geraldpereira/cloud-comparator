package fr.byob.cloud.comparator.gwt.client.widget.slider;


import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.widgetideas.client.SliderBar;
import com.google.gwt.widgetideas.client.SliderBar.LabelFormatter;


public class CloudConstantSliderFormat implements LabelFormatter {
	ConstantsWithLookup constants;

	public CloudConstantSliderFormat(final ConstantsWithLookup constants) {
		this.constants = constants;
	}
	@Override
	public String formatLabel(final SliderBar slider, final double value) {
		return constants.getString(Double.toString(value));
	}



}
