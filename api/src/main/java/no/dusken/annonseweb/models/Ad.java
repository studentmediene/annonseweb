package no.dusken.annonseweb.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Ad {       //Parent class, should not be used alone
    @Id
    @GeneratedValue
    private long ID;

    private long price;
    private long discount;  //Automatically from customer?

    private String file_location;   //for the ad image


    public Ad(long price, long discount, String file_location) {
        this.price = price;
        this.discount = discount;
        this.file_location = file_location;
    }

    public long getID() {
        return ID;
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

    public String getFile_location() {
        return file_location;
    }

    public void setFile_location(String file_location) {
        this.file_location = file_location;
    }
}
