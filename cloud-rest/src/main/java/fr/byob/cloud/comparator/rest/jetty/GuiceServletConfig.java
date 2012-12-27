package fr.byob.cloud.comparator.rest.jetty;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import fr.byob.cloud.comparator.commons.guice.LoggerModule;
import fr.byob.cloud.comparator.db.guice.CloudMyBatisModule;
import fr.byob.cloud.comparator.db.guice.DAOModule;
import fr.byob.cloud.comparator.rest.DozerModule;

public class GuiceServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
        		new LoggerModule(),
        		new DAOModule(),
        		new CloudMyBatisModule(),
        		new CloudJerseyJettyServletModule(),
        		new DozerModule());
    }
}