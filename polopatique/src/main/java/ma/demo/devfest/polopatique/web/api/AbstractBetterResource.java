package ma.demo.devfest.polopatique.web.api;

import com.google.gson.Gson;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

@Singleton
public abstract class AbstractBetterResource<RESOURCE_TYPE, ID_TYPE> extends HttpServlet {

    private final Class<RESOURCE_TYPE> resourceType;
    private final Class<ID_TYPE> idType;

    private Logger log = Logger.getLogger(AbstractBetterResource.class.getName());

    private Gson gson = new Gson();

    protected AbstractBetterResource(Class<RESOURCE_TYPE> resourceType, Class<ID_TYPE> idType) {
        this.resourceType = resourceType;
        this.idType = idType;

        if (! idType.equals(Long.class) && ! idType.equals(String.class) ) {
            throw new IllegalStateException("Hele, AppEnginovy IDcka jsou Longy nebo Stringy, tak co tu nacvicujes se svym "+idType+"?");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ID_TYPE recordId = readRecordIdFromRequest(req);
        if (recordId == null) {
            respond(resp, findRecords(req));

        } else {
            respond(resp, findRecord(recordId));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ID_TYPE recordId = readRecordIdFromRequest(req);

        if (recordId != null) {
            respond(resp, 404, "Co jako za tim lomitkem? Tam nic být nemá!");
            return;
        }

        RESOURCE_TYPE sentData = gson.fromJson(new InputStreamReader(req.getInputStream(), "UTF-8"), resourceType);

        ID_TYPE newId = createRecord(sentData);

        resp.sendRedirect(req.getServletPath()+"/"+newId);
    }



    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ID_TYPE recordKey = readRecordIdFromRequest(req);

        if (recordKey == null) {
            respond(resp, 404, "Kam jako PUTujes? Neco dej za lomitko.");
            return;
        }

        RESOURCE_TYPE sentData = gson.fromJson(new InputStreamReader(req.getInputStream(), "UTF-8"), resourceType);

        updateRecord(sentData, recordKey);

        resp.sendRedirect(req.getServletPath()+"/"+recordKey);

    }


    private void respond(HttpServletResponse resp, int code, String record) throws IOException {
        resp.sendError(code, record);
    }

    private void respond(HttpServletResponse resp, Object data) throws IOException {
        resp.setContentType("application/json");
        resp.getOutputStream().write(
                gson.toJson(data).getBytes("UTF-8")
        );
    }

    private ID_TYPE readRecordIdFromRequest(HttpServletRequest req) {
        String path = req.getPathInfo();

        if (path == null || "".equals(path) || "/".equals(path) ) {
            return null;

        } else {
            String keyString = path.replaceFirst("/", "");
            if (idType.equals(String.class)) return (ID_TYPE) keyString;
            return (ID_TYPE) new Long(keyString);
        }
    }

    protected abstract RESOURCE_TYPE findRecord(ID_TYPE recordKey);

    protected abstract RESOURCE_TYPE[] findRecords(HttpServletRequest req);

    protected abstract ID_TYPE createRecord(RESOURCE_TYPE newRecord);

    protected abstract void updateRecord(RESOURCE_TYPE body, ID_TYPE recordKey);

}