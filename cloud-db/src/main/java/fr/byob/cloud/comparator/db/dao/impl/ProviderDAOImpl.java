package fr.byob.cloud.comparator.db.dao.impl;

import java.util.List;

import org.mybatis.guice.transactional.Transactional;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import fr.byob.cloud.comparator.db.bean.Provider;
import fr.byob.cloud.comparator.db.bean.ProviderSearchCriteria;
import fr.byob.cloud.comparator.db.bean.SimpleProvider;
import fr.byob.cloud.comparator.db.bean.SizedProvider;
import fr.byob.cloud.comparator.db.dao.ProviderDAO;
import fr.byob.cloud.comparator.db.mappers.ProviderMapper;


@Singleton
public class ProviderDAOImpl implements ProviderDAO {
	@Inject
	ProviderMapper mapper;

	@Override
	@Transactional
	public List<Provider> selectAll() {
		final List<Provider> providers = mapper.selectAll();
		return providers;
	}

	@Override
	@Transactional
	public Provider select(final Provider provider) {
		return mapper.select(provider);
	}

	@Override
	@Transactional
	public List<SimpleProvider> searchSimples(final ProviderSearchCriteria criteria) {
		initCriteriaLocation(criteria);
		return mapper.searchSimples(criteria);
	}

	@Override
	@Transactional
	public List<SizedProvider> searchSized(final ProviderSearchCriteria criteria) {
		initCriteriaLocation(criteria);
		return mapper.searchSized(criteria);
	}


	private void initCriteriaLocation(final ProviderSearchCriteria criteria) {
		final String location = criteria.getLocation();
		if (location != null) {
			criteria.setLocation(criteria.getLocation() + "%");
		}
	}

	@Override
	public List<String> selectLocations() {
		return mapper.selectLocations();
	}

}
