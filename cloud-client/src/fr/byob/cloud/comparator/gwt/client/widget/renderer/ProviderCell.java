package fr.byob.cloud.comparator.gwt.client.widget.renderer;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Image;

import fr.byob.cloud.comparator.gwt.client.widget.constants.ProviderConstants;
import fr.byob.cloud.comparator.gwt.client.widget.model.ResultRowModel;

public class ProviderCell extends AbstractCell<ResultRowModel>  {

	private final ProviderConstants providerConstants;

	public ProviderCell() {
		this.providerConstants = GWT.create(ProviderConstants.class);
	}

	@Override
	public void render(final Context context, final ResultRowModel result, final SafeHtmlBuilder sb) {
		if (result == null) {
			return;
		}
		final Image image = new Image(providerConstants.getString(result.getProviderName() + "_img"));
		image.setWidth("100px");
		sb.appendHtmlConstant(image.toString());
	}

}
