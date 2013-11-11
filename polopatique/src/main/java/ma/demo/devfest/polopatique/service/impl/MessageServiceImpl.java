package ma.demo.devfest.polopatique.service.impl;

import com.google.inject.Guice;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Work;
import ma.demo.devfest.polopatique.domain.Message;
import ma.demo.devfest.polopatique.service.MessageService;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * Implementace našich operací nad {@link Message}.
 *
 */
public class MessageServiceImpl implements MessageService {

    private Logger log = Logger.getLogger(MessageServiceImpl.class.getName());

    private Provider<Objectify> ofyProvider;

    @Inject
    public void setOfyProvider(OfyProvider ofyProvider) {
        this.ofyProvider = ofyProvider;
    }

    @Override
    public Message createOrUpdateMessage(final Message message) {
        return ofy().transact(
                new Work<Message>() {
                    @Override
                    public Message run() {
                        Message saved = ofy().load().key(message.createOfyKey()).now();
                        if (saved != null) {
                            saved.setMessage(message.getMessage());
                            saved.setDateUpdated(new Date());

                            ofy().save().entity(saved);
                            return saved;

                        } else {
                            message.setDateUpdated(new Date());
                            message.setDateCreated(new Date());

                            ofy().save().entity(message);
                            return message;
                        }
                    }
                }
        );
    }

    @Override
    public Message[] findMessages() {
        return ofy().load().type(Message.class).list().toArray(new Message[0]);
    }

    @Override
    public Message getMessage(String key) {
        return ofy().load().key(Message.createOfyKey(key)).now();
    }

    @Override
    public void delete(String key) {
        ofy().delete().key(Message.createOfyKey(key)).now();
    }

    private Objectify ofy() {
        return ofyProvider.get();
    }

}
