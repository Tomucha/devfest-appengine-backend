package ma.demo.devfest.profique.web.api.jersey;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import ma.demo.devfest.profique.util.CallContext;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.core.HttpHeaders;
import java.util.logging.Logger;

/**
 * Jersey filter, ktery z HTTP hlavicek vytahne "autorizacni token".
 */
public class JerseyAuthFilter implements ContainerRequestFilter {

    private static Logger log = Logger.getLogger(JerseyAuthFilter.class.getName());

    @Inject
    private Provider<CallContext> callContextProvider;

    @Override
    public ContainerRequest filter(ContainerRequest request) {
        String user = request.getHeaderValue(HttpHeaders.AUTHORIZATION);
        log.info("Logged user: "+user);
        callContextProvider.get().setUserName(user);

        return request;
    }
}
