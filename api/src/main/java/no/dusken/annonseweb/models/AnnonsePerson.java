package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The <code>AnnonsePerson</code> is a class created for user logging and tracking of sales.
 * It is thought only to be temporary, until a common db for MediaStud is created.
 *
 * @author Sitron Te
 */
@Entity
public class AnnonsePerson extends DuskenObject implements Authentication {
    @Column(length = 100)
    @Size(max = 100, min = 2)
    private String credentials = null;

    @Column(length = 100)
    @NotNull
    @Size(max = 100, min = 2)
    private String principal;

    private boolean authenticated;

    @Column
    private RoleAuth authority;

    @Column
    private boolean active;

    public AnnonsePerson() {
        active = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> ret = new ArrayList<GrantedAuthority>();
        ret.add(authority);
        return ret;
    }

    @Override
    public Object getCredentials() {
        return credentials;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean setCredentials(String credentials, String oldCredentials) {
        if (this.credentials == null || this.credentials.equals(oldCredentials)) {
            this.credentials = credentials;
            return true;
        }
        return false;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (getId() != null)
            if (active)
                this.authenticated = isAuthenticated;
        else if (isAuthenticated)
            throw new IllegalArgumentException("Could not set authenticated");
    }

    @Override
    public String getName() {
        return principal;
    }

    public void setAuthority(RoleAuth role) {
        this.authority = role;
    }

    private boolean getActive() {
        return active;
    }

    private void setActive(boolean active){
        this.active = active;
    }
}
