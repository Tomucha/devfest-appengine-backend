package ma.demo.devfest.polopatique.guice;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.ServletModule;
import ma.demo.devfest.polopatique.service.MessageService;
import ma.demo.devfest.polopatique.service.impl.MessageServiceImpl;
import ma.demo.devfest.polopatique.web.api.MessageResource;

/**
 * Servisní guice modul.
 */
public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MessageService.class).to(MessageServiceImpl.class);
    }

}