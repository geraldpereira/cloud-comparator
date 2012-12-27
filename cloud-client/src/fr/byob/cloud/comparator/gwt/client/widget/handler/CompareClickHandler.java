package fr.byob.cloud.comparator.gwt.client.widget.handler;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.ListBox;

import fr.byob.cloud.comparator.gwt.client.RestService;
import fr.byob.cloud.comparator.gwt.client.RestServiceAsync;
import fr.byob.cloud.comparator.gwt.client.widget.model.BestProviderModel;
import fr.byob.cloud.comparator.gwt.client.widget.model.ResultModel;
import fr.byob.cloud.comparator.gwt.client.widget.model.ResultRowModel;
import fr.byob.cloud.comparator.gwt.client.widget.model.SimpleProviderModel;
import fr.byob.cloud.comparator.gwt.client.widget.slider.CloudSlider;

public class CompareClickHandler implements ClickHandler{
	private final RestServiceAsync restSvc = GWT.create(RestService.class);

	private final IntegerBox nbHours;
	private final IntegerBox nbServer;
	private final CloudSlider cpu;
	private final CloudSlider ram;
	private final CloudSlider storage;
	private final CloudSlider inBW;
	private final CloudSlider outBW;
	private final ListBox locations;
	private final DecoratedPopupPanel loading;
	private final ResultModel resultModel;
	private final BestProviderModel bestProviderModel;

	public CompareClickHandler(final IntegerBox nbHours, final IntegerBox nbServer, final CloudSlider cpu,
			final CloudSlider ram,
			final CloudSlider storage, final CloudSlider inBW, final CloudSlider outBW, final ListBox locations,
			final DecoratedPopupPanel loading, final ResultModel resultModel, final BestProviderModel bestProviderModel) {
		this.nbHours = nbHours;
		this.nbServer=nbServer;
		this.cpu=cpu;
		this.ram=ram;
		this.storage=storage;
		this.inBW=inBW;
		this.outBW=outBW;
		this.locations=locations;
		this.resultModel = resultModel;
		this.bestProviderModel = bestProviderModel;
		this.loading=loading;
	}

	@Override
	public void onClick(final ClickEvent event) {
		//		Window.alert("Values : server " + nbServer.getText()+" -cpu "+cpu.getText()+" -ram "+ram.getText()+" -storage "+storage.getText()+
		//				" -inBW "+inBW.getText()+" -outBW "+outBW.getText()+" -locations "+locations.getItemText(locations.getSelectedIndex()));
		loading.show();
		loading.setPopupPosition(325, 300);
		restSvc.getResults(nbHours.getValue(), nbServer.getValue(), cpu.getSelectedValue(), ram.getSelectedValue(),
				storage.getSelectedValue(), inBW.getSelectedValue(), outBW.getSelectedValue(),
				locations.getSelectedIndex() != -1 ? locations.getValue(locations.getSelectedIndex()) : "",
						new AsyncCallback<List<ResultRowModel>>() {
					@Override
					public void onSuccess(final List<ResultRowModel> result) {
						resultModel.updateResult(result);
						loading.hide();
					}

					@Override
					public void onFailure(final Throwable caught) {
						final String message = caught.getMessage();
						resultModel.updateResult(message);
						loading.hide();
					}
				});

		restSvc.getBestProviders(nbHours.getValue(), nbServer.getValue(), cpu.getSelectedValue(),
				ram.getSelectedValue(),
				storage.getSelectedValue(), inBW.getSelectedValue(), outBW.getSelectedValue(),
				locations.getSelectedIndex() != -1 ? locations.getValue(locations.getSelectedIndex()) : "",
						new AsyncCallback<List<SimpleProviderModel>>() {
					@Override
					public void onSuccess(final List<SimpleProviderModel> result) {
						bestProviderModel.updateResult(result);
						loading.hide();
					}

					@Override
					public void onFailure(final Throwable caught) {
						loading.hide();
					}
				});
	}
}
