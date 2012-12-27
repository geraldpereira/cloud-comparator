package fr.byob.cloud.comparator.gwt.client.widget.model;

import java.util.ArrayList;
import java.util.List;

import fr.byob.cloud.comparator.gwt.client.widget.listener.IListenerTable;

public class BestProviderModel {
	private List<SimpleProviderModel> resultList; 
	
	private List<IListenerTable> listeners = new ArrayList<IListenerTable>();
	
	public void addListener(IListenerTable listener){
		listeners.add(listener);
	}
	public void removeListener(IListenerTable listener){
		listeners.remove(listener);
	}
	
	
	public List<SimpleProviderModel> getResultList(){
		return resultList;
	}
	
	public void updateResult(List<SimpleProviderModel> resultList){
		this.resultList = resultList;
		notifyListener();
		
	}

	private void notifyListener(){
		for(IListenerTable listener : listeners){
			listener.refreshDatas();
		}
	}
	
}
