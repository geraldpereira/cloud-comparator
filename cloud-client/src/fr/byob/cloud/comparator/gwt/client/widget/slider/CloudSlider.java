package fr.byob.cloud.comparator.gwt.client.widget.slider;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.widgetideas.client.SliderBar;

public class CloudSlider extends Composite {

	final SliderBar sliderBar;
	final CloudSliderModel sliderBarModel;

	public CloudSlider(final String labelStr, final CloudSliderModel model) {
		final double maxValue = model.getMaxValue();
		sliderBarModel = model;
		sliderBar = new SliderBar(model.getMinValue(), maxValue, model,
				(CloudSliderImages) GWT.create(CloudSliderImages.class));
		sliderBar.setStepSize(model.getStep());
		sliderBar.setCurrentValue(0);
		sliderBar.setNumLabels((int) maxValue);
		sliderBar.setWidth("100%");

		final Label label = new Label(labelStr);
		label.setStyleName("cloud-slider-label", true);

		// Place the check above the text box using a vertical panel.
		final FlowPanel panel = new FlowPanel();
		panel.add(label);
		final SimplePanel simple = new SimplePanel(sliderBar);
		simple.setStyleName("cloud-slider-bar", true);
		panel.add(simple);

		// All composites must call initWidget() in their constructors.
		initWidget(panel);

		// Give the overall composite a style name.
		setStyleName("cloud-slider", true);
	}

	public String getSelectedValue() {
		return sliderBarModel.formatLabel(sliderBar, sliderBar.getCurrentValue());
	}

}
