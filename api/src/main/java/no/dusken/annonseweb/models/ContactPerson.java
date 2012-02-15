package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

import static javax.persistence.CascadeType.ALL;

@Entity
public class ContactPerson extends DuskenObject{

    private String personName;
    private String email;
    private String telephoneNumber;
    private String companyPosition;

    @OneToOne(cascade = ALL)
    private Customer customer;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastContactedTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastContactedUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDate;

    private String createdUser;

    private Boolean active;

    public ContactPerson() {}

    public ContactPerson(String personName, String email, String telephoneNumber, String companyPosition) {
        this.personName = personName;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.companyPosition = companyPosition;
        this.active = true;
    }

    public String getPersonName() {
        return personName;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getCompanyPosition() {
        return companyPosition;
    }

    public Calendar getLastContactedTime() {
        return lastContactedTime;
    }

    public void setLastContactedTime(Calendar lastContactedTime) {
        this.lastContactedTime = lastContactedTime;
    }

    public Calendar getLastContactedUser() {
        return lastContactedUser;
    }

    @Override
    public String getTitle() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setLastContactedUser(Calendar lastContactedUser) {
        this.lastContactedUser = lastContactedUser;
    }

    public Boolean getActive() {
        return active;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setCompanyPosition(String companyPosition) {
        this.companyPosition = companyPosition;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}