package ma.demo.devfest.polopatique.guice;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.ServletModule;
import com.googlecode.objectify.ObjectifyFilter;
import ma.demo.devfest.polopatique.service.MessageService;
import ma.demo.devfest.polopatique.service.impl.MessageServiceImpl;
import ma.demo.devfest.polopatique.web.api.BetterMessageResource;
import ma.demo.devfest.polopatique.web.api.MessageResource;

import javax.inject.Singleton;

/**
 * Webový guice modul, definující naše REST API.
 *
 * Jsou tu dvě implementace: MessageResource - ta je skutečně hodně polopatická a BetterMessageResource,
 * která je o trošilinku rozumnější a umožňuje snadné přidávání entit.
 *
 */
public class ApiModule extends ServletModule {

    @Override
    protected void configureServlets() {
        filter("/*").through(ObjectifyFilter.class);
        bind(ObjectifyFilter.class).in(Singleton.class);

        serve("/api/messages/*", "/api/messages").with(MessageResource.class);

        serve("/better-api/messages/*", "/better-api/messages").with(BetterMessageResource.class);
    }

}