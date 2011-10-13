package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Ad extends DuskenObject {       //Parent class, should not be used alone

    private long price;
    private long discount;  //Automatically from customer?

    private String fileLocation;   //for the ad image


    public Ad(long price, long discount, String fileLocation) {
        this.price = price;
        this.discount = discount;
        this.fileLocation = fileLocation;
    }

    @Override
    public String toString() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFile_location(String fileLocation) {
        this.fileLocation = fileLocation;
    }
}
