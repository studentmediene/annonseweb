package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;

import javax.persistence.*;

import java.util.Calendar;

import static javax.persistence.CascadeType.ALL;

@Entity
public class ContactNote extends DuskenObject{

    @ManyToOne(optional = true)
    private Customer customer;

    @ManyToOne(optional = true)
    private ContactPerson contactPerson;

    @ManyToOne(optional = true)
    private Sale sale;

    @ManyToOne(optional = false)
    private AnnonsePerson createdUser;

    @ManyToOne(optional = true)
    private AnnonsePerson delegatedUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dueDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDate;

    private String text;

    private Boolean active;

    public ContactNote() {}

    public ContactNote(String text, ContactPerson contactPerson, Customer customer) {
        this.text = text;
        this.contactPerson = contactPerson;
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public AnnonsePerson getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(AnnonsePerson createdUser) {
        this.createdUser = createdUser;
    }

    public AnnonsePerson getDelegatedUser() {
        return delegatedUser;
    }

    public void setDelegatedUser(AnnonsePerson delegatedUser) {
        this.delegatedUser = delegatedUser;
    }

    public Calendar getDueDate() {
        return dueDate;
    }

    public void setDueDate(Calendar dueDate) {
        this.dueDate = dueDate;
    }

    public Calendar getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}


