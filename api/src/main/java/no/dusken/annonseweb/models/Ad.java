package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table
public class Ad extends DuskenObject {

    private BigDecimal price;
    private BigDecimal discount;


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
}
