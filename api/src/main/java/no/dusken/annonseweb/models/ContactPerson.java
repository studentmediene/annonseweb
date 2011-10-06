package no.dusken.annonseweb.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

public class ContactPerson {
    @Id
    @GeneratedValue
    private long ID;

    private String person_name;
    private String email;
    private String telephone_number;
    private String company_position;

    private Customer customer;

    private Date last_contacted_time;
    private Date last_contacted_user;

    private Boolean active;

    public void setLast_contacted_time(Date last_contacted_time) { //Only class ContactNote should be able to edit
        this.last_contacted_time = last_contacted_time;
    }

    public void setLast_contacted_user(Date last_contacted_user) { //Only class ContactNote should be able to edit
        this.last_contacted_user = last_contacted_user;
    }


    public ContactPerson(String person_name, String email, String telephone_number, String company_position, Customer customer) {
        this.person_name = person_name;
        this.email = email;
        this.telephone_number = telephone_number;
        this.company_position = company_position;
        this.customer = customer;
    }

    public long getID() {
        return ID;
    }

    public Date getLast_contacted_time() {
        return last_contacted_time;
    }

    public Date getLast_contacted_user() {
        return last_contacted_user;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone_number() {
        return telephone_number;
    }

    public void setTelephone_number(String telephone_number) {
        this.telephone_number = telephone_number;
    }

    public String getCompany_position() {
        return company_position;
    }

    public void setCompany_position(String company_position) {
        this.company_position = company_position;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
