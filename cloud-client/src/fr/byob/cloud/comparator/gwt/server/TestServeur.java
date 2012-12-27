package fr.byob.cloud.comparator.gwt.server;

import java.util.ArrayList;
import java.util.List;

public class TestServeur {
	
	public List<String> getLocations(){
		List<String> locationsList = new ArrayList<String>();
		locationsList.add("US");
		locationsList.add("Europe");
		locationsList.add("Asie");
		return locationsList;
	}

}
