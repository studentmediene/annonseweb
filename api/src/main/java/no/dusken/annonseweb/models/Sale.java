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


    private String appointmentName;


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

    private Boolean adDelivered;

    public Sale() {}

    public Sale(String appointmentName, List<Ad> ads, Customer customer, Person createdUser, Boolean adDelivered) {
        this.appointmentName = appointmentName;
        this.ads = ads;
        this.customer = customer;
        this.createdUser = createdUser;
        this.lastEditedDate = Calendar.getInstance();
        this.lastEditedUser = createdUser;
        this.adDelivered = adDelivered;
    }

    public String getAppointmentName() {
        return appointmentName;
    }

    public void setAppointmentName(String appointmentName) {
        this.appointmentName = appointmentName;
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

    public Boolean getAdDelivered() {
        return adDelivered;
    }

    public void setAdDelivered(Boolean adDelivered) {
        this.adDelivered = adDelivered;
    }
}
