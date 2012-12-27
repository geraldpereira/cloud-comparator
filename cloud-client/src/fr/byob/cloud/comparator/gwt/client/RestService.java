package fr.byob.cloud.comparator.gwt.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.byob.cloud.comparator.gwt.client.widget.model.CompareException;
import fr.byob.cloud.comparator.gwt.client.widget.model.ResultRowModel;
import fr.byob.cloud.comparator.gwt.client.widget.model.SimpleProviderModel;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("rest")
public interface RestService extends RemoteService {
	List<String> getLocations();

	List<ResultRowModel> getResults(Integer nbHours, Integer nbServer, String cpu, String ram, String storage,
			String inBW, String outBW, String location) throws CompareException;

	List<SimpleProviderModel> getBestProviders(Integer nbHours, Integer nbServer, String cpu, String ram,
			String storage,
			String inBW, String outBW, String location);
}
