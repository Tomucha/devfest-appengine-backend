package ma.demo.devfest.profique.service.impl;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import ma.demo.devfest.profique.domain.Message;

import javax.inject.Provider;

/**
 * Naše implementace Ofy Providera, která nás odstíní od statické Objectify hnusoty.
 */
public class OfyProvider implements Provider<Objectify> {

    {
        ObjectifyService.register(Message.class);
    }

    @Override
    public Objectify get() {
        return ObjectifyService.ofy();
    }
}
