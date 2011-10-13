package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

public class Customer extends DuskenObject{

    @Id
    @GeneratedValue
    private long customerNumber;

    private String customerName;
    private String centralEmail;
    private String centralTlf;
    private String invoiceAddress;
    private String subscriberAddress;


    private int discount;
    private List<String> industryTags;

    private Date createdDate;
    private String createdUser;
    private Date lasteditedDate;
    private String lasteditedUser;


    /*public String getInfo(){    //What is needed here, organized differently in web development?
        return "Customer name: " + customerName + "\n"
    }  */

    public Customer(String customerName, String centralEmail, String centralTlf, String invoiceAddress,
                    String subscriberAddress, int discount, List<String> industryTags) {
        this.customerName = customerName;
        this.centralEmail = centralEmail;
        this.centralTlf = centralTlf;
        this.invoiceAddress = invoiceAddress;
        this.subscriberAddress = subscriberAddress;
        this.discount = discount;
        this.industryTags = industryTags;
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

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setIndustryTags(List<String> industryTags) {
        this.industryTags = industryTags;
    }

    public long getCustomerNumber() {
        return customerNumber;
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

    public int getDiscount() {
        return discount;
    }

    public List<String> getIndustryTags() {
        return industryTags;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public Date getLasteditedDate() {
        return lasteditedDate;
    }

    public String getLasteditedUser() {
        return lasteditedUser;
    }

    @Override
    public String toString() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
