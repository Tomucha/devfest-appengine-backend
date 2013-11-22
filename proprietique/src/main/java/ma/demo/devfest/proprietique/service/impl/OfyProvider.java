package ma.demo.devfest.proprietique.service.impl;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import ma.demo.devfest.proprietique.domain.Message;

import javax.inject.Provider;

public class OfyProvider implements Provider<Objectify> {

    {
        ObjectifyService.register(Message.class);
    }

    @Override
    public Objectify get() {
        return ObjectifyService.ofy();
    }
}