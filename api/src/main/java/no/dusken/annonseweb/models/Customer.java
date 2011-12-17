package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;
import no.dusken.common.model.Person;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
public class Customer extends DuskenObject{

    private String customerName;
    private String centralEmail;
    private String centralTlf;
    private String invoiceAddress;
    private String subscriberAddress;
    private BigInteger discount;
    private List<String> industryTags;
    private String homepage;

    @OneToMany(fetch = LAZY, cascade = ALL, mappedBy = "customer")
    private List<ContactNote> contactNotes;

    @OneToOne(cascade = ALL)
    private ContactPerson contactPerson;

    @OneToMany(fetch = LAZY, cascade = ALL, mappedBy = "customer")
    private List<Sale> sales;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDate;

    private String createdUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastEditedDate;

    private Person lastEditedUser;

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

    public Calendar getCreatedDate() {
        return createdDate;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public Calendar getLastEditedDate() {
        return lastEditedDate;
    }

    public List<ContactNote> getContactNotes() {
        return contactNotes;
    }

    public void setContactNotes(List<ContactNote> contactNotes) {
        this.contactNotes = contactNotes;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public Person getLastEditedUser() {
        return lastEditedUser;
    }

    public void setLastEditedUser(Person lastEditedUser) {
        this.lastEditedUser = lastEditedUser;
    }


    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }
}
