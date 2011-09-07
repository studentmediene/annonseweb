package no.dusken.annonseweb.plugin;

import no.dusken.common.dao.GenericDao;
import no.dusken.common.service.MailService;
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

    /**
     * Provides access to the generic dao that can handle <T extends AraneaObject>
     * @return the {@link GenericDao} dao
     */
    GenericDao getGenericDao();
}
