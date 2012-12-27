package fr.byob.cloud.comparator.gwt.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.byob.cloud.comparator.gwt.client.widget.model.CompareException;
import fr.byob.cloud.comparator.gwt.client.widget.model.ResultRowModel;
import fr.byob.cloud.comparator.gwt.client.widget.model.SimpleProviderModel;

/**
 * The async counterpart of <code>RestService</code>.
 */
public interface RestServiceAsync {
	void getLocations(AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	void getResults(Integer nbHours, Integer nbServer, String cpu, String ram, String storage, String inBW,
			String outBW, String location, AsyncCallback<List<ResultRowModel>> callback) throws CompareException;

	void getBestProviders(Integer nbHours, Integer nbServer, String cpu, String ram, String storage, String inBW,
			String outBW, String location, AsyncCallback<List<SimpleProviderModel>> callback);

}
