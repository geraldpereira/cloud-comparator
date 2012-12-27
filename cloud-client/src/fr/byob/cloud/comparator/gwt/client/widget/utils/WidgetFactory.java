package fr.byob.cloud.comparator.gwt.client.widget.utils;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.SimplePanel;

public class WidgetFactory {

	public static SimplePanel createPricePanel(final String priceValue) {
		final SimplePanel pricePanel = new SimplePanel();
		final Hyperlink link = new Hyperlink(priceValue + "$/H", "#");
		link.setStyleName("offre");
		final HorizontalPanel global = new HorizontalPanel();
		global.setStyleName("divoffre");
		final HorizontalPanel imgPanel = new HorizontalPanel();
		imgPanel.add(link);
		global.add(imgPanel);
		pricePanel.add(global);
		return pricePanel;
	}



}
