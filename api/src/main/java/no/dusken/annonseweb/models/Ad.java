package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;
import no.dusken.common.model.Person;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
public abstract class Ad extends DuskenObject {

    private BigDecimal price;
    private BigDecimal discount;
    private Boolean active;
    private Boolean placedOnProd;

    @OneToMany(fetch = LAZY, cascade = ALL)
    private List<Sale> sales = new LinkedList<Sale>();

    @ManyToOne
    private Sale sale;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDate = new GregorianCalendar();

    @ManyToOne(cascade = ALL)
    private Person createdUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastEditedDate = new GregorianCalendar();

    @ManyToOne(cascade = ALL)
    private Person lastEditedUser;

    public Ad() {}

    public Ad( BigDecimal price, BigDecimal discount) {
        this.price = price;
        this.discount = discount;
    }

    public Ad(Sale sale, BigDecimal price, BigDecimal discount, Boolean active, Boolean placedOnProd) {
        this.sale = sale;
        this.price = price;
        this.discount = discount;
        this.active = active;
        this.placedOnProd = placedOnProd;
        this.setLastEditedDate();
        this.setCreatedDate();
    }

    public abstract void cloneFrom(WebAd other);
    public abstract void cloneFrom(PrintedAd other);

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getPlacedOnProd() {
        return placedOnProd;
    }

    public void setPlacedOnProd(Boolean placedOnProd) {
        this.placedOnProd = placedOnProd;
    }

    public Person getCreatedUser() {
        return createdUser;
    }

    public String getCreatedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        String strdate = null;
        if (this.createdDate != null) {
            strdate = sdf.format(this.createdDate.getTime());
        }
        return strdate;
    }

    public void setCreatedDate() {
        this.createdDate = Calendar.getInstance();
    }

    public void setCreatedUser(Person createdUser) {
        this.createdUser = createdUser;
    }

    public String getLastEditedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        String strdate = null;
        if (this.lastEditedDate != null) {
            strdate = sdf.format(this.lastEditedDate.getTime());
        }
        return strdate;
    }

    public void setLastEditedDate() {
        this.lastEditedDate = Calendar.getInstance();
    }

    public Person getLastEditedUser() {
        return lastEditedUser;
    }

    public void setLastEditedUser(Person lastEditedUser) {
        this.lastEditedUser = lastEditedUser;
    }

    public BigDecimal getFinalPrice() {
        if(discount == BigDecimal.ZERO){
            return this.price;
        }
        else {
            return this.price.multiply (new BigDecimal(1).subtract((discount.divide(new BigDecimal(100)))));
        }

    }


}
