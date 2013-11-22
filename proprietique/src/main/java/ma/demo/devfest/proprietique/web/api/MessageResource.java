package ma.demo.devfest.proprietique.web.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import ma.demo.devfest.proprietique.domain.Message;
import ma.demo.devfest.proprietique.service.MessageService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.logging.Logger;

@Singleton
@Api(name = "messages")
public class MessageResource {

    private Logger log = Logger.getLogger(MessageResource.class.getName());

    private MessageService messageService;

    @Inject
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @ApiMethod(httpMethod = ApiMethod.HttpMethod.POST)
    public Message createMessage(Message newMessage) {
        return messageService.createOrUpdateMessage(newMessage);
    }

    @ApiMethod(httpMethod = ApiMethod.HttpMethod.GET)
    public Message findMessage(@Named("messageKey") String key) {
        return messageService.getMessage(key);
    }

    @ApiMethod(httpMethod = ApiMethod.HttpMethod.GET)
    public Message[] findMessages() {
        return messageService.findMessages();
    }

}