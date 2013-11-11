package ma.demo.devfest.polopatique.service;

import ma.demo.devfest.polopatique.domain.Message;

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
