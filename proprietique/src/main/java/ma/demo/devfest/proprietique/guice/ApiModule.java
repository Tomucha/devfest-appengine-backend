package ma.demo.devfest.proprietique.guice;

import com.google.api.server.spi.guice.GuiceSystemServiceServletModule;
import com.googlecode.objectify.ObjectifyFilter;
import ma.demo.devfest.proprietique.web.api.MessageResource;

import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;

/**
 * Webový guice modul, definující naše API.
 *
 */
public class ApiModule extends GuiceSystemServiceServletModule {

    @Override
    protected void configureServlets() {
        super.configureServlets();

        filter("/*").through(ObjectifyFilter.class);
        bind(ObjectifyFilter.class).in(Singleton.class);


        Set<Class<?>> serviceClasses = new HashSet<>();
        serviceClasses.add(MessageResource.class);
        serveGuiceSystemServiceServlet("/_ah/spi/*", serviceClasses);
    }

}