package ma.demo.devfest.profique.guice;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import ma.demo.devfest.profique.web.api.jersey.JerseyApplication;
import ma.demo.devfest.profique.web.api.jersey.JerseyAuthFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * Servisní guice modul.
 *
 */
public class ApiModule extends ServletModule {
    @Override
    protected void configureServlets() {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("javax.ws.rs.Application", JerseyApplication.class.getName());
        params.put("com.sun.jersey.spi.container.ContainerRequestFilters", JerseyAuthFilter.class.getName());
        serve("/api/*").with(GuiceContainer.class, params);
    }
}
