package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;
import no.dusken.common.model.Person;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
public class Invoice extends DuskenObject{

    @OneToMany(fetch = LAZY, cascade = ALL)
    private List<Sale> sales = new ArrayList<Sale>();

    private String description;
    private Long invoiceNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar invoiceDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDate;

    @ManyToOne(cascade = ALL)
    private Person createdUser;

    public Invoice(){}

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Calendar getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Calendar invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Calendar getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }

    public Person getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(Person createdUser) {
        this.createdUser = createdUser;
    }
}
