package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public abstract class Ad extends DuskenObject {

    private BigDecimal price;
    private BigDecimal discount;

    @ManyToOne
    private Sale sale;

    public Ad() {}

    public Ad(BigDecimal price, BigDecimal discount) {
        this.price = price;
        this.discount = discount;
    }

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
}
