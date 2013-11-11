package ma.demo.devfest.polopatique.web.api;

import com.google.gson.Gson;
import ma.demo.devfest.polopatique.domain.Message;
import ma.demo.devfest.polopatique.service.MessageService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

@Singleton
public class MessageResource extends HttpServlet {

    private Logger log = Logger.getLogger(MessageResource.class.getName());

    private Gson gson = new Gson();

    private MessageService messageService;

    @Inject
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String messageKey = readMessageIdFromRequest(req);
        if (messageKey == null) {
            respond(resp, messageService.findMessages());

        } else {
            Message found = messageService.getMessage(messageKey);
            if (found == null) {
                respond(resp, 404, "Nic takoveho ona nema");
            } else {
                respond(resp, found);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String messageKey = readMessageIdFromRequest(req);

        if (messageKey != null) {
            respond(resp, 404, "Co jako za tim lomitkem?");
            return;
        }

        Message body = gson.fromJson(new InputStreamReader(req.getInputStream(), "UTF-8"), Message.class);

        messageService.createOrUpdateMessage(body);

        resp.sendRedirect(req.getServletPath()+"/"+body.getKey());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String messageKey = readMessageIdFromRequest(req);

        if (messageKey == null) {
            respond(resp, 404, "Kam jako PUTujes? Neco dej za lomitko.");
            return;
        }

        Message body = gson.fromJson(new InputStreamReader(req.getInputStream(), "UTF-8"), Message.class);
        body.setKey(messageKey);

        messageService.createOrUpdateMessage(body);

        resp.sendRedirect(req.getServletPath()+"/"+body.getKey());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String messageKey = readMessageIdFromRequest(req);
        if (messageKey == null) {
            respond(resp, 404, "Co jako mazes? Chce to neco za lomitko.");
            return;
        }
        messageService.delete(messageKey);
        respond(resp, 200, "Deleted");
    }

    private void respond(HttpServletResponse resp, int code, String message) throws IOException {
        if (code < 400) {
            resp.setStatus(code);
        } else {
            resp.sendError(code, message);
        }
    }

    private void respond(HttpServletResponse resp, Object data) throws IOException {
        resp.setContentType("application/json");
        resp.getOutputStream().write(
                gson.toJson(data).getBytes("UTF-8")
        );
    }

    private String readMessageIdFromRequest(HttpServletRequest req) {
        String path = req.getPathInfo();
        if (path == null || "".equals(path) || "/".equals(path) ) {
            return null;

        } else {
            return path.replaceFirst("/", "");
        }
    }

}