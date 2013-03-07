package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "sale")
@SequenceGenerator(name = "sale_seq", sequenceName = "sale_id_seq")
public class Sale extends DuskenObject {

    private String description;

    @OneToMany(fetch = LAZY, cascade = ALL)
    private List<Ad>  ads = new LinkedList<Ad>();

    @ManyToOne(optional = false)
    @NotNull
    private Customer customer;

    @ManyToOne
    private AnnonsePerson createdUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastEditedDate;

    @ManyToOne
    private AnnonsePerson lastEditedUser;

    private Boolean adReceived;

    @ManyToOne(optional = true, cascade = ALL)
    public Invoice invoice;

    @OneToMany(fetch = LAZY, mappedBy = "sale")
    private List<AnnonseNote> annonseNotes = new ArrayList<AnnonseNote>();

    public Sale(){}

    public Sale(Customer customer){
        setCustomer(customer);
    }

    public Sale(String description, List<Ad> ads, Customer customer, AnnonsePerson createdUser, Boolean adReceived) {
        this.description = description;
        this.ads = ads;
        this.customer = customer;
        this.createdUser = createdUser;
        this.lastEditedDate = Calendar.getInstance();
        this.lastEditedUser = createdUser;
        this.adReceived = adReceived;
    }

    /**
     * Clones all non-transient information about this <code>Sale</code>.
     * @param other <code>Sale</code> to clone information from
     */
    public void cloneFrom(Sale other) {
        if (other == null) {
            return;
        }
        setDescription(other.description);
        setAds(other.ads);
        setCustomer(other.customer);
        setCreatedUser(other.createdUser);
        setLastEditedDate(other.lastEditedDate);
        setLastEditedUser(other.lastEditedUser);
        setAdReceived(other.adReceived);
        invoice = other.invoice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AnnonsePerson getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(AnnonsePerson createdUser) {
        this.createdUser = createdUser;
    }

    public Calendar getLastEditedDate() {
        return lastEditedDate;
    }

    public void setLastEditedDate(Calendar lastEditedDate) {
        this.lastEditedDate = lastEditedDate;
    }

    public AnnonsePerson getLastEditedUser() {
        return lastEditedUser;
    }

    public void setLastEditedUser(AnnonsePerson lastEditedUser) {
        this.lastEditedUser = lastEditedUser;
    }

    public Boolean getAdReceived() {
        return adReceived;
    }

    public void setAdReceived(Boolean adReceived) {
        this.adReceived = adReceived;
    }

    public List<AnnonseNote> getAnnonseNotes() {
        return annonseNotes;
    }

    public void setAnnonseNotes(List<AnnonseNote> annonseNotes) {
        this.annonseNotes = annonseNotes;
    }
}
