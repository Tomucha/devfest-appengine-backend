package ma.demo.devfest.polopatique.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * Konfigurace Guice - dva moduly: servis a api.
 */
public class GuiceConfig extends GuiceServletContextListener {

    @Override
    public Injector getInjector() {
        return Guice.createInjector(
                new ServiceModule(),
                new ApiModule()
        );
    }

}
