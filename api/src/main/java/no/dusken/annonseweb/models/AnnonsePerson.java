package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

/**
 * The <code>AnnonsePerson</code> is a class created for user logging and tracking of sales.
 * It is thought only to be temporary, until a common db for MediaStud is created.
 *
 * @author Inge Halsaunet
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"ID", "principal"}))
public class AnnonsePerson extends DuskenObject implements Authentication {
    @Column(length = 100)
    @Size(max = 100, min = 2)
    private String credentials;

    @Column(length = 100)
    @Size(max = 100, min = 2)
    private String principal;

    @OneToMany(fetch = LAZY, mappedBy = "createdUser")
    private List<Sale> sales = new ArrayList<Sale>();

    private boolean authenticated;

    @Column
    private String authority;

    @Column
    private boolean active;

    @OneToMany(fetch = LAZY, mappedBy = "createdUser")
    private List<AnnonseNote> myNotes = new ArrayList<AnnonseNote>();

    @OneToMany(fetch = LAZY, mappedBy = "delegatedUser")
    private List<AnnonseNote> delegatedNotes = new ArrayList<AnnonseNote>();

    public AnnonsePerson() {
        active = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> ret = new ArrayList<GrantedAuthority>();
        ret.add(RoleAuth.valueOf(authority));
        return ret;
    }

    @Override
    public Object getCredentials() {
        return credentials;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setCredentials(String credentials) {
        if (credentials != null)
            this.credentials = credentials;
    }

    public String getPassword() {
        return "Dette får du ikke se!";
    }

    public void setPassword(String passord) {
        this.credentials = passord;
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

    public void setName(String name) {
        principal = name;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        if (authority == null)
            return "";
        return authority;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public List<AnnonseNote> getMyNotes() {
        return myNotes;
    }

    public void setMyNotes(List<AnnonseNote> myNotes) {
        this.myNotes = myNotes;
    }

    public List<AnnonseNote> getDelegatedNotes() {
        return delegatedNotes;
    }

    public void setDelegatedNotes(List<AnnonseNote> delegatedNotes) {
        this.delegatedNotes = delegatedNotes;
    }
}
