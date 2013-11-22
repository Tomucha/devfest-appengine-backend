package ma.demo.devfest.profique.web.api;

import com.sun.jersey.api.Responses;
import ma.demo.devfest.profique.domain.Message;
import ma.demo.devfest.profique.service.MessageService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

    @Inject
    private MessageService messageService;

    @GET
    public Response findMessages() {
        // lze vracet přímo objekty, ale hodí se vracet Response, lze pracovat s HTTP cache apod.
        return Response.ok(messageService.findMessages()).build();
    }

    @GET
    @Path("/{messageKey}")
    public Response findMessage(@PathParam("messageKey") String key) {
        Message message = messageService.getMessage(key);
        if (message != null) {
            return Response.ok(message).build();
        } else {
            return Responses.notFound().build();
        }
    }

    @PUT
    @Path("/{messageKey}")
    public Response updateMessage(@PathParam("messageKey") String messageKey, Message message) {
        message.setKey(messageKey);
        messageService.createOrUpdateMessage(message);
        return Response.ok().build();
    }

    @POST
    public Response createMessage(Message message) {
        messageService.createOrUpdateMessage(message);

        return Response.created(URI.create(message.getKey())).build();
    }

}