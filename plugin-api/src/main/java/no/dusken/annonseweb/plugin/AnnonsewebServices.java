package no.dusken.annonseweb.plugin;

import org.kantega.jexmec.Services;
import org.kantega.jexmec.store.PluginStoreProvider;

/**
 * @author Marvin B. Lillehaug <lillehau@underdusken.no>
 */
public interface AnnonsewebServices extends Services{

    /**
     * Provides access to persistent state so the plugin can store configuration
     * etc.
     * @return the PluginStoreProvider
     */
    PluginStoreProvider getPluginStoreProvider();
}
