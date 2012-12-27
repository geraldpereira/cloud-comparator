package fr.byob.cloud.comparator.gwt.client.widget.handler;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

import fr.byob.cloud.comparator.gwt.client.widget.constants.LocationConstants;

public class AsyncCallBackLocations implements AsyncCallback<List<String>>{

	private final LocationConstants locationConstants = GWT.create(LocationConstants.class);

	private final ListBox locations;
	private final Label error;
	public AsyncCallBackLocations(final ListBox locations, final Label error) {
		this.locations = locations;
		this.error = error;
	}
	@Override
	public void onFailure(final Throwable caught) {
		error.setText("Problem for filling locations.");
		locations.setEnabled(false);
	}

	@Override
	public void onSuccess(final List<String> result) {
		locations.addItem("", "");
		for(final String item : result){
			locations.addItem(locationConstants.getString(item), item);
		}
	}
}
