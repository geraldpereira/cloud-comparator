package fr.byob.cloud.comparator.db.dao;

import java.util.List;

import fr.byob.cloud.comparator.db.bean.Provider;
import fr.byob.cloud.comparator.db.bean.ProviderSearchCriteria;
import fr.byob.cloud.comparator.db.bean.SimpleProvider;
import fr.byob.cloud.comparator.db.bean.SizedProvider;

public interface ProviderDAO {

	public List<Provider> selectAll();

	public Provider select(Provider provider);

	public List<SimpleProvider> searchSimples(ProviderSearchCriteria criteria);

	public List<SizedProvider> searchSized(ProviderSearchCriteria criteria);

	public List<String> selectLocations();
}
