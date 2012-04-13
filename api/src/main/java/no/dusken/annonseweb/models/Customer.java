package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
public class Customer extends DuskenObject{

    @NotNull
    private String customerName;
    @NotNull
    private String contactPerson;
    @NotNull
    private String centralEmail;
    @NotNull
    private String centralTlf;
    @NotNull
    private String invoiceAddress;

    private String subscriberAddress;

    private BigInteger discount;

    @ElementCollection
    private List<String> industryTags = new ArrayList<String>();
    private String homepage;

    @OneToMany(fetch = LAZY, cascade = ALL, mappedBy = "customer")
    private List<ContactNote> contactNotes= new ArrayList<ContactNote>();

    @OneToMany(fetch = LAZY, cascade = ALL, mappedBy = "customer")
    private List<Sale> sales = new ArrayList<Sale>();

    public Customer() {}

    public Customer(String customerName, String centralEmail, String centralTlf, String invoiceAddress,
                    String subscriberAddress, BigInteger discount, List<String> industryTags) {
        this.customerName = customerName;
        this.centralEmail = centralEmail;
        this.centralTlf = centralTlf;
        this.invoiceAddress = invoiceAddress;
        this.subscriberAddress = subscriberAddress;
        this.discount = discount;
        this.industryTags = industryTags;
    }

    public void addSale(Sale sale){
        sales.add(sale);
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCentralEmail(String centralEmail) {
        this.centralEmail = centralEmail;
    }

    public void setCentralTlf(String centralTlf) {
        this.centralTlf = centralTlf;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public void setSubscriberAddress(String subscriberAddress) {
        this.subscriberAddress = subscriberAddress;
    }

    public void setDiscount(BigInteger discount) {
        this.discount = discount;
    }

    public void setIndustryTags(List<String> industryTags) {
        this.industryTags = industryTags;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCentralEmail() {
        return centralEmail;
    }

    public String getCentralTlf() {
        return centralTlf;
    }

    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public String getSubscriberAddress() {
        return subscriberAddress;
    }

    public BigInteger getDiscount() {
        return discount;
    }

    public List<String> getIndustryTags() {
        return industryTags;
    }

    public List<ContactNote> getContactNotes() {
        return contactNotes;
    }

    public void setContactNotes(List<ContactNote> contactNotes) {
        this.contactNotes = contactNotes;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
}
