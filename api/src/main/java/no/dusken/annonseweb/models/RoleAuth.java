package no.dusken.annonseweb.models;

import org.springframework.security.core.GrantedAuthority;

/**
 * The <code>RoleAuth</code> enum is thought to be a temporary way to authorize users in annonseweb until
 * a common db is created for mediastud
 *
 * @author Sitron Te
 */
public enum RoleAuth implements GrantedAuthority {
    MASKINIST, ANNONSE_ANSVARLIG, ANNONSE_ARBEIDER
    ;

    @Override
    public String getAuthority() {
        switch (this) {
            case MASKINIST: return "ROLE_MASKINIST";
            case ANNONSE_ANSVARLIG: return "ROLE_ANNONSE_ANSVARLIG";
            case ANNONSE_ARBEIDER: return "ROLE_ANNONSE_ARBEIDER";
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
