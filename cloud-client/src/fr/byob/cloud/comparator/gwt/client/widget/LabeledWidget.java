package fr.byob.cloud.comparator.gwt.client.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class LabeledWidget extends Composite {

	public LabeledWidget(final String labelStr, final Widget widget) {
		final Label label = new Label(labelStr);
		label.setStyleName("cloud-labeled-widget-label", true);
		widget.setStyleName("cloud-labeled-widget-widget", true);

		// Place the check above the text box using a vertical panel.
		final FlowPanel panel = new FlowPanel();
		panel.add(label);
		panel.add(widget);

		// All composites must call initWidget() in their constructors.
		initWidget(panel);

		// Give the overall composite a style name.
		setStyleName("cloud-labeled-widget", true);
	}

}
