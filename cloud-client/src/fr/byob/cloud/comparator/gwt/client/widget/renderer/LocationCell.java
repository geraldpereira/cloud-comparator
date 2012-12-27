package fr.byob.cloud.comparator.gwt.client.widget.renderer;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;

import fr.byob.cloud.comparator.gwt.client.widget.constants.LocationConstants;
import fr.byob.cloud.comparator.gwt.client.widget.constants.ProviderConstants;
import fr.byob.cloud.comparator.gwt.client.widget.constants.StaticConstants;
import fr.byob.cloud.comparator.gwt.client.widget.model.ResultRowModel;

public class LocationCell extends AbstractCell<ResultRowModel> {

	private final ProviderConstants providerConstants = GWT.create(ProviderConstants.class);
	private final StaticConstants staticConstants = GWT.create(StaticConstants.class);
	private final LocationConstants locationConstants = GWT.create(LocationConstants.class);
	private final String ZERO_HERTZ = "0 Hz";
	private final String ZERO_BYTE = "0 B";

	@Override
	public void render(final Context context, final ResultRowModel result, final SafeHtmlBuilder sb) {
		if (result == null) {
			return;
		}

		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<b>Location</b> " + locationConstants.getString(result.getLocation()) + " <br/>");
		stringBuilder.append("<b>Server</b> " + result.getComputeCost() + " (RAM " + result.getRam());
		if (!ZERO_HERTZ.equals(result.getCpu())) {
			stringBuilder.append(" / CPU " + result.getCpu());
		}
		if (!ZERO_BYTE.equals(result.getStorage())) {
			stringBuilder.append(" / Storage " + result.getStorage());
		}
		stringBuilder.append(") <br/> <b>Bandwidth</b> " + result.getBandwidthCost() + " (In "
				+ result.getBandwidthInCost() + " / Out " + result.getBandwidthOutCost() + ")<br/>");
		final HTML config = new HTML(stringBuilder.toString());
		config.addStyleName("cloud-location-config");

		final String link = providerConstants.getString(result.getProviderName() + "_details_site");
		final Anchor anchor = new Anchor(staticConstants.location_details(), link);
		anchor.setTarget("_top");
		anchor.addStyleName("cloud-location-details");

		final FlowPanel panel = new FlowPanel();
		panel.add(config);
		panel.add(anchor);
		panel.addStyleName("cloud-location");
		final DecoratorPanel decPanel = new DecoratorPanel();
		decPanel.setWidget(panel);
		sb.appendHtmlConstant(decPanel.toString());
	}

}
