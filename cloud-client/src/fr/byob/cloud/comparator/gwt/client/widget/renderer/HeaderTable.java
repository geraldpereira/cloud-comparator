package fr.byob.cloud.comparator.gwt.client.widget.renderer;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.cellview.client.SafeHtmlHeader;

public class HeaderTable extends SafeHtmlHeader {

	public HeaderTable(final String text) {
		super(new SafeHtml() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 4221351874942516189L;
			@Override
			public String asString() {
				return "<div style=\"color: #8DA1AD;\">"+text+"</div>";
			}
		});
	}

}
