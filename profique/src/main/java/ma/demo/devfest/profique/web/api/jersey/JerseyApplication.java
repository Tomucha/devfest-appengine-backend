package ma.demo.devfest.profique.web.api.jersey;

import ma.demo.devfest.profique.web.api.MessageResource;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Tohle je naše Jersey aplikace.
 * Sem je potřeba přidávat další vznikající resources.
 *
 */
public class JerseyApplication extends Application {

    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();

        // resources
        classes.add(MessageResource.class);

        // utils
        classes.add(JsonWriter.class);
        classes.add(JsonReader.class);

        return classes;
    }

}
