package fr.byob.cloud.comparator.gwt.client.widget.renderer;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

import fr.byob.cloud.comparator.gwt.client.widget.constants.ProviderConstants;
import fr.byob.cloud.comparator.gwt.client.widget.constants.StaticConstants;
import fr.byob.cloud.comparator.gwt.client.widget.model.ResultRowModel;

public class PriceCell extends AbstractCell<ResultRowModel>  {

	private final ProviderConstants providerConstants = GWT.create(ProviderConstants.class);
	private final StaticConstants staticConstants = GWT.create(StaticConstants.class);

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void render(final Context context, final ResultRowModel result, final SafeHtmlBuilder sb) {
		if (result == null) {
			return;
		}

		final FlowPanel pricePanel = new FlowPanel();
		pricePanel.addStyleName("cloud-price");

		final Label price = new Label(result.getTotalCost());
		price.addStyleName("cloud-price-cost-label");
		final String link = providerConstants.getString(result.getProviderName() + "_price_site");

		final Anchor select = new Anchor(staticConstants.price_select(), link);
		select.setTarget("_top");
		select.setStyleName("cloud-price-select");
		final SimplePanel selecDiv = new SimplePanel(select);
		selecDiv.setStyleName("gwt-Button");
		selecDiv.addStyleName("cloud-price-select-div");

		pricePanel.add(price);
		pricePanel.add(selecDiv);

		sb.appendHtmlConstant(pricePanel.toString());

		//		// If the value comes from the user, we escape it to avoid XSS attacks.
		//	      SafeHtml safeValue = SafeHtmlUtils.fromString();
		//
		//	      // Append some HTML that sets the text color.
		//	      sb.appendHtmlConstant("<div><b><span style=\"color:red;padding:2px;\">");
		//	      sb.append(safeValue);
		//	      sb.appendHtmlConstant("$/h</span><br><a style=\"color:white;background-color:red;padding:3px;\" href=\"#\">Select   <img src=\"/images/offre.png\"/></a></b>");
		//	      sb.appendHtmlConstant("</div>");
	}
}
