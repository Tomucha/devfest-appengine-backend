package ma.demo.devfest.polopatique.guice;

import com.google.inject.AbstractModule;
import com.googlecode.objectify.Objectify;
import ma.demo.devfest.polopatique.service.MessageService;
import ma.demo.devfest.polopatique.service.impl.MessageServiceImpl;
import ma.demo.devfest.polopatique.service.impl.OfyProvider;

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