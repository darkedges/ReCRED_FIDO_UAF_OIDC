package eu.recred.fidouaf.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import eu.recred.fidouaf.res.About;
import eu.recred.fidouaf.res.FidoUafResource;
import eu.recred.fidouaf.res.Hello;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(About.class);
		register(FidoUafResource.class);
		register(Hello.class);
	}
}
