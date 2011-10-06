package no.dusken.annonseweb.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ContactNote {
    @Id
    @GeneratedValue
    private long ID;

    private Customer customer;
    private ContactPerson contactPerson;

    private String Method; //email,telephonenumber or in person
    private String text;

    //should update ContactPerson last_contacted_time and last_contacted_user


    public ContactNote(String text, String method, ContactPerson contactPerson, Customer customer) {
        this.text = text;
        Method = method;
        this.contactPerson = contactPerson;
        this.customer = customer;
    }

    public long getID() {
        return ID;
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

    public String getMethod() {
        return Method;
    }

    public void setMethod(String method) {
        Method = method;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}


