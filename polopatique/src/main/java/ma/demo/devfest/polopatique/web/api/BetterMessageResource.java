package ma.demo.devfest.polopatique.web.api;

import ma.demo.devfest.polopatique.domain.Message;
import ma.demo.devfest.polopatique.service.MessageService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;

@Singleton
public class BetterMessageResource extends AbstractBetterResource<Message, String> {

    @Inject
    private MessageService messageService;

    protected BetterMessageResource() {
        super(Message.class, String.class);
    }

    @Override
    protected Message findRecord(String recordKey) {
        return messageService.getMessage(recordKey);
    }

    @Override
    protected Message[] findRecords(HttpServletRequest req) {
        return messageService.findMessages();
    }

    @Override
    protected String createRecord(Message newRecord) {
        return messageService.createOrUpdateMessage(newRecord).getKey();
    }

    @Override
    protected void updateRecord(Message body, String recordKey) {
        body.setKey(recordKey);
        messageService.createOrUpdateMessage(body);
    }

}