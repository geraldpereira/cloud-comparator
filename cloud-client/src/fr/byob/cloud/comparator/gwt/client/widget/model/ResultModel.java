package fr.byob.cloud.comparator.gwt.client.widget.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;

import fr.byob.cloud.comparator.gwt.client.widget.constants.StaticConstants;
import fr.byob.cloud.comparator.gwt.client.widget.listener.IListenerTable;

public class ResultModel {

	private final StaticConstants staticConstants = GWT.create(StaticConstants.class);

	private final List<ResultRowModel> resultList = new ArrayList<ResultRowModel>();
	private String message;
	private final List<IListenerTable> listeners = new ArrayList<IListenerTable>();

	public void addListener(final IListenerTable listener){
		listeners.add(listener);
	}
	public void removeListener(final IListenerTable listener){
		listeners.remove(listener);
	}

	public String getMessage() {
		return message;
	}

	public List<ResultRowModel> getResultList(){
		return resultList;
	}

	public void updateResult(final List<ResultRowModel> resultList){
		this.resultList.clear();
		this.resultList.addAll(resultList);
		if (resultList.isEmpty()) {
			message = staticConstants.no_result();
		} else {
			message = "";
		}
		notifyListener();
	}

	public void updateResult(final String error) {
		this.resultList.clear();
		this.message = error;
		notifyListener();
	}

	private void notifyListener(){
		for(final IListenerTable listener : listeners){
			listener.refreshDatas();
		}
	}

}
