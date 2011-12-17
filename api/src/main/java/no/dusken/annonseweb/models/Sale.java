package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;
import no.dusken.common.model.Person;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
public class Sale extends DuskenObject {

    private String description;

    @OneToMany(fetch = LAZY, cascade = ALL)
    private List<Ad>  ads = new LinkedList<Ad>();

    @ManyToOne(optional = false, cascade = ALL)
    @NotNull
    private Customer customer;

    @ManyToOne(cascade = ALL)
    private Person createdUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastEditedDate;

    @ManyToOne(cascade = ALL)
    private Person lastEditedUser;

    private Boolean adReceived;

    @ManyToOne(optional = true, cascade = ALL)
    public Invoice invoice;

    public Sale() {}

    public Sale(String description, List<Ad> ads, Customer customer, Person createdUser, Boolean adReceived) {
        this.description = description;
        this.ads = ads;
        this.customer = customer;
        this.createdUser = createdUser;
        this.lastEditedDate = Calendar.getInstance();
        this.lastEditedUser = createdUser;
        this.adReceived = adReceived;
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

    public Person getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(Person createdUser) {
        this.createdUser = createdUser;
    }

    public Calendar getLastEditedDate() {
        return lastEditedDate;
    }

    public void setLastEditedDate(Calendar lastEditedDate) {
        this.lastEditedDate = lastEditedDate;
    }

    public Person getLastEditedUser() {
        return lastEditedUser;
    }

    public void setLastEditedUser(Person lastEditedUser) {
        this.lastEditedUser = lastEditedUser;
    }

    public Boolean getAdReceived() {
        return adReceived;
    }

    public void setAdReceived(Boolean adReceived) {
        this.adReceived = adReceived;
    }
}
