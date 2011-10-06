package no.dusken.annonseweb.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

public class Customer {

    @Id
    @GeneratedValue
    long customerNumber;

    private String customerName;
    private String central_email;
    private String central_tlf;
    private String invoice_adress;
    private String abonnent_adress;


    private int discount;
    private List<String> industry_tags;

    private Date created_date;
    private String created_user;
    private Date lastedited_date;
    private String lastedited_user;

    public Customer(String customerName, String central_email, String central_tlf, String invoice_adress, String abonnent_adress) {
        this.customerName = customerName;
        this.central_email = central_email;
        this.central_tlf = central_tlf;
        this.invoice_adress = invoice_adress;
        this.abonnent_adress = abonnent_adress;
    }

    /*public String getInfo(){    //What is needed here, organized differently in web development?
        return "Customer name: " + customerName + "\n"
    }  */

    public void addIndustry_tags(String industry_tag){
        this.industry_tags.add(industry_tag);
    }

    public void removeIndustry_tags(String industry_tag){
        this.industry_tags.remove(industry_tag);
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setCentral_email(String central_email) {
        this.central_email = central_email;
    }

    public void setCentral_tlf(String central_tlf) {
        this.central_tlf = central_tlf;
    }

    public void setInvoice_adress(String invoice_adress) {
        this.invoice_adress = invoice_adress;
    }

    public void setAbonnent_adress(String abonnent_adress) {
        this.abonnent_adress = abonnent_adress;
    }

    public String getCustomerName() {

        return customerName;
    }

    public String getCentral_email() {
        return central_email;
    }

    public String getCentral_tlf() {
        return central_tlf;
    }

    public String getInvoice_adress() {
        return invoice_adress;
    }

    public String getAbonnent_adress() {
        return abonnent_adress;
    }

    public List getIndustry_tags() {
        return industry_tags;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public String getCreated_user() {
        return created_user;
    }

    public Date getLastedited_date() {
        return lastedited_date;
    }

    public String getLastedited_user() {
        return lastedited_user;
    }

    public long getCustomerNumber() {

        return customerNumber;
    }

    public int getDiscount() {
        return discount;
    }

}
