package fr.byob.cloud.comparator.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.byob.cloud.comparator.gwt.client.widget.InputPanel;
import fr.byob.cloud.comparator.gwt.client.widget.ResultPanel;
import fr.byob.cloud.comparator.gwt.client.widget.model.BestProviderModel;
import fr.byob.cloud.comparator.gwt.client.widget.model.ResultModel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Cloud_client implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {
		final ResultModel model = new ResultModel();
		final BestProviderModel bestProviderModel = new BestProviderModel();
		final VerticalPanel global =  new VerticalPanel();

		global.addStyleName("bodyCustom");
		global.add(new InputPanel(model,bestProviderModel));
		//global.add(new CloudBestOffer(bestProviderModel));
		global.add(new ResultPanel(model));
		global.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
			@Override
			public void onUncaughtException(final Throwable e) {
				GWT.log(e.getMessage(), e);
			}
		});

		RootPanel.get("mainContent").add(global);

	}
}
