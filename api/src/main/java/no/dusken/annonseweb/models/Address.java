package no.dusken.annonseweb.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Address {

    @Id
    @GeneratedValue
    private long ID;

    private String person_name;
    private String street_name;
    private String zip_code;
    private String zip_county;


    public Address(String person_name, String street_name, String zip_code, String zip_county) {
        this.person_name = person_name;
        this.street_name = street_name;
        this.zip_code = zip_code;
        this.zip_county = zip_county;
    }

    public long getID() {
        return ID;
    }


    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getZip_county() {
        return zip_county;
    }

    public void setZip_county(String zip_county) {
        this.zip_county = zip_county;
    }
}
