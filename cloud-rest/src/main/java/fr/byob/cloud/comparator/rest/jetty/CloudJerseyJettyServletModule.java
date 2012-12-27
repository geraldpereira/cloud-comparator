package fr.byob.cloud.comparator.rest.jetty;

import java.util.HashMap;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import fr.byob.cloud.comparator.rest.v1x.manager.CompareManager;
import fr.byob.cloud.comparator.rest.v1x.manager.ProviderManager;
import fr.byob.cloud.comparator.rest.v1x.manager.impl.CompareManagerImpl;
import fr.byob.cloud.comparator.rest.v1x.manager.impl.ProviderManagerImpl;


public class CloudJerseyJettyServletModule extends JerseyServletModule {

	@Override
	protected void configureServlets() {
		bind(ProviderManager.class).to(ProviderManagerImpl.class);
		bind(CompareManager.class).to(CompareManagerImpl.class);

		/* bind the REST resources */
		bind(fr.byob.cloud.comparator.rest.v1x.resource.ProviderResource.class);
		bind(fr.byob.cloud.comparator.rest.v1x.resource.CompareResource.class);

		/* bind jackson converters for JAXB/JSON serialization */
		bind(MessageBodyReader.class).to(JacksonJsonProvider.class);
		bind(MessageBodyWriter.class).to(JacksonJsonProvider.class);

		final HashMap<String, String> initParams = new HashMap<String, String>();

		//		 <init-param>
		//		               <param-name>com.sun.jersey.api.json.POJOMappingFeature</param-name>
		//		               <param-value>true</param-value>
		//		           </init-param>
		//		<init-param>
		//	    <param-name>com.sun.jersey.config.property.packages</param-name>
		//	    <param-value>
		//	        com.myapp.userservice; // semi-colon seperated
		//	        com.myapp.mappedexception
		//	    </param-value>
		//	</init-param>

		initParams.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
		initParams.put("com.sun.jersey.config.property.packages",
				"fr.byob.cloud.comparator.rest.exception;fr.byob.cloud.comparator.rest.v1x");
		serve("/*").with(GuiceContainer.class, initParams);
	}
}
