package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ContactNote extends DuskenObject{

    private Customer customer;
    private ContactPerson contactPerson;

    private String method; //email,telephonenumber or in person
    private String text;

    //should update ContactPerson last_contacted_time and last_contacted_user


    public ContactNote(String text, String method, ContactPerson contactPerson, Customer customer) {
        this.text = text;
        this.method = method;
        this.contactPerson = contactPerson;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}


