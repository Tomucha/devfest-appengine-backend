package ma.demo.devfest.proprietique.guice;

import com.google.inject.AbstractModule;
import com.googlecode.objectify.Objectify;
import ma.demo.devfest.proprietique.service.MessageService;
import ma.demo.devfest.proprietique.service.impl.MessageServiceImpl;
import ma.demo.devfest.proprietique.service.impl.OfyProvider;

/**
 * Servisní guice modul.
 *
 */
public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MessageService.class).to(MessageServiceImpl.class);
        bind(Objectify.class).toProvider(OfyProvider.class);
    }

}