package fr.byob.cloud.comparator.gwt.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.byob.cloud.comparator.gwt.client.RestService;
import fr.byob.cloud.comparator.gwt.client.RestServiceAsync;
import fr.byob.cloud.comparator.gwt.client.widget.constants.StaticConstants;
import fr.byob.cloud.comparator.gwt.client.widget.handler.AsyncCallBackLocations;
import fr.byob.cloud.comparator.gwt.client.widget.handler.CompareClickHandler;
import fr.byob.cloud.comparator.gwt.client.widget.model.BestProviderModel;
import fr.byob.cloud.comparator.gwt.client.widget.model.ResultModel;
import fr.byob.cloud.comparator.gwt.client.widget.slider.BandwidthSliderFormat;
import fr.byob.cloud.comparator.gwt.client.widget.slider.CPUSliderFormat;
import fr.byob.cloud.comparator.gwt.client.widget.slider.CloudSlider;
import fr.byob.cloud.comparator.gwt.client.widget.slider.RAMSliderFormat;
import fr.byob.cloud.comparator.gwt.client.widget.slider.StorageSliderFormat;

public class InputPanel extends SimplePanel {

	private final StaticConstants staticConstants = GWT.create(StaticConstants.class);
	private final RestServiceAsync restSvc = GWT.create(RestService.class);

	public InputPanel(final ResultModel model,final BestProviderModel bestProviderModel) {

		// Place the check above the text box using a vertical panel.
		final IntegerBox nbOfServersTxtBox = new IntegerBox();
		nbOfServersTxtBox.setMaxLength(3);
		nbOfServersTxtBox.setValue(1);
		final LabeledWidget nbOfServers = new LabeledWidget(staticConstants.input_panel_nb_of_servers(),
				nbOfServersTxtBox);

		// 730 heures par mois
		final IntegerBox hoursPerMonthTxtBox = new IntegerBox();
		hoursPerMonthTxtBox.setMaxLength(3);
		hoursPerMonthTxtBox.setValue(730);
		final LabeledWidget hoursPerMonth = new LabeledWidget(staticConstants.input_panel_hours_per_month(),
				hoursPerMonthTxtBox);

		final FlowPanel topPanel = new FlowPanel();
		topPanel.add(nbOfServers);
		topPanel.add(hoursPerMonth);

		final CloudSlider cpuPanel = new CloudSlider(staticConstants.input_panel_cpu(), new CPUSliderFormat());
		final CloudSlider ramPanel = new CloudSlider(staticConstants.input_panel_ram(), new RAMSliderFormat());
		final CloudSlider storagePanel = new CloudSlider(staticConstants.input_panel_storage(),
				new StorageSliderFormat());
		final CloudSlider inBWPanel = new CloudSlider(staticConstants.input_panel_bandwidth_in(),
				new BandwidthSliderFormat());
		final CloudSlider outBWPanel = new CloudSlider(staticConstants.input_panel_bandwidth_out(),
				new BandwidthSliderFormat());

		final Label error = new Label();
		error.setStyleName("cloud-input-error", true);
		final ListBox locationsListBox = new ListBox();
		locationsListBox.setWidth("150px");
		final AsyncCallBackLocations callbackLocations = new AsyncCallBackLocations(locationsListBox, error);
		restSvc.getLocations(callbackLocations);
		final LabeledWidget locations = new LabeledWidget(staticConstants.input_panel_location(), locationsListBox);
		final FlowPanel locationPanel = new FlowPanel();
		locationPanel.add(locations);
		locationPanel.add(error);

		final CompareClickHandler compareHandler = new CompareClickHandler(hoursPerMonthTxtBox, nbOfServersTxtBox,
				cpuPanel, ramPanel,
 storagePanel, inBWPanel, outBWPanel, locationsListBox, newLoadingPanel(),
				model, bestProviderModel);
		final Button compareButton = new Button(staticConstants.input_panel_compare(), compareHandler);
		compareButton.addStyleName("cloud-input-button");

		// Create a table to layout the form options
		final VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(5);
		panel.add(topPanel);
		panel.setCellVerticalAlignment(nbOfServers, HasVerticalAlignment.ALIGN_MIDDLE);
		panel.add(ramPanel);
		panel.add(cpuPanel);
		panel.add(storagePanel);
		panel.add(inBWPanel);
		panel.add(outBWPanel);
		panel.add(locationPanel);
		panel.add(compareButton);

		// Wrap the contents in a DecoratorPanel
		final DecoratorPanel decPanel = new DecoratorPanel();
		decPanel.setWidget(panel);
		setWidget(decPanel);
		setStyleName("cloud-input-panel", true);
	}

	private DecoratedPopupPanel newLoadingPanel() {
		final DecoratedPopupPanel simplePopup = new DecoratedPopupPanel(true);
		simplePopup.setWidth("90px");
		simplePopup.add(new HTML(staticConstants.loading()));
		simplePopup.setAutoHideEnabled(false);
		simplePopup.setModal(true);
		return simplePopup;
	}
}
