package ma.demo.devfest.polopatique.guice;

import com.google.inject.AbstractModule;
import com.googlecode.objectify.Objectify;
import ma.demo.devfest.polopatique.service.MessageService;
import ma.demo.devfest.polopatique.service.impl.MessageServiceImpl;

/**
 * Servisní guice modul - tohle jsou služby nad DataStore
 *
 */
public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MessageService.class).to(MessageServiceImpl.class);
        // sem přidávej další servisy ...
    }

}