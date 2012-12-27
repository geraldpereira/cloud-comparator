package fr.byob.cloud.comparator.rest.v1x.manager.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.inject.Inject;

import fr.byob.cloud.comparator.db.dao.ProviderDAO;
import fr.byob.cloud.comparator.rest.v1x.manager.ProviderManager;

public class ProviderManagerImpl implements ProviderManager {
	@Inject
	private ProviderDAO providerDAO;

	public List<String> toDTO(final List<String> locations) {

		final Set<String> currentLocations = new HashSet<String>();

		for (final String location : locations) {
			final String[] splited = location.split("_", 3);
			final String continent = splited[0];
			currentLocations.add(continent);
		}

		final List<String> locationsList = new ArrayList<String>();

		for (final String location : currentLocations) {
			locationsList.add(location);
		}
		Collections.sort(locationsList);
		return locationsList;
	}

	@Override
	public List<String> locations() {
		return toDTO(providerDAO.selectLocations());
	}

}
