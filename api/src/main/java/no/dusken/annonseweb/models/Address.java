package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;

import javax.persistence.Entity;

@Entity
public class Address extends DuskenObject{

    //TODO: se issue on github.
    private String personName;
    private String streetName;
    private String zipCode;
    private String zipCounty;

    public Address() {}

    public Address(String personName, String streetName, String zipCode, String zipCounty) {
        this.personName = personName;
        this.streetName = streetName;
        this.zipCode = zipCode;
        this.zipCounty = zipCounty;
    }

    public String getPersonName() {
        return personName;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getZipCounty() {
        return zipCounty;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setZipCounty(String zipCounty) {
        this.zipCounty = zipCounty;
    }
}
