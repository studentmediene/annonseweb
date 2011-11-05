package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Ad extends DuskenObject {

    private long price;
    private long discount;

    private String fileLocation;   //for the ad image


    public Ad(long price, long discount, String fileLocation) {
        this.price = price;
        this.discount = discount;
        this.fileLocation = fileLocation;
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
