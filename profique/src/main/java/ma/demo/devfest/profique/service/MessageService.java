package ma.demo.devfest.profique.service;

import ma.demo.devfest.profique.domain.Message;
import ma.demo.devfest.profique.guice.CallLogger;
import ma.demo.devfest.profique.util.LogCalls;

/**
 * Interface našich operací na {@link Message}.
 *
 */
public interface MessageService {

    Message createOrUpdateMessage(Message message);

    Message[] findMessages();

    Message getMessage(String key);

    void delete(String key);

}
