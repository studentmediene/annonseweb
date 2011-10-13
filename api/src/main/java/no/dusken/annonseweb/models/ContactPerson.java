package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

public class ContactPerson extends DuskenObject{

    private String personName;
    private String email;
    private String telephoneNumber;
    private String companyPosition;

    private Customer customer;

    private Date lastContactedTime;
    private Date lastContactedUser;

    private Boolean active;

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

    public Customer getCustomer() {
        return customer;
    }

    public Date getLastContactedTime() {
        return lastContactedTime;
    }

    public Date getLastContactedUser() {
        return lastContactedUser;
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

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


}