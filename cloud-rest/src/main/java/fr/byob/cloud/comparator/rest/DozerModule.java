package fr.byob.cloud.comparator.rest;

import java.util.Arrays;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import com.google.inject.AbstractModule;

public class DozerModule extends AbstractModule{

	@Override
	protected void configure() {
		final DozerBeanMapper mapper = new DozerBeanMapper(Arrays.asList("dozer-mapping.xml"));
		bind(Mapper.class).toInstance(mapper);
	}

}
