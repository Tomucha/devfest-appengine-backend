package ma.demo.devfest.profique.guice;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.googlecode.objectify.Objectify;
import ma.demo.devfest.profique.service.MessageService;
import ma.demo.devfest.profique.service.impl.MessageServiceImpl;
import ma.demo.devfest.profique.service.impl.OfyProvider;
import ma.demo.devfest.profique.util.CallContext;
import ma.demo.devfest.profique.util.LogCalls;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * Servisní guice modul.
 *
 */
public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MessageService.class).to(MessageServiceImpl.class);
        bind(Objectify.class).toProvider(OfyProvider.class);

        // ... a tady mocná AOP kouzla!
        MethodInterceptor logger = new CallLogger();
        requestInjection(logger);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(LogCalls.class), logger);
        bindInterceptor(Matchers.annotatedWith(LogCalls.class), Matchers.any(), new MethodInterceptor[]{logger});
    }

}
