package no.dusken.annonseweb.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

public class Sale {

    @Id
    @GeneratedValue
    private long ID;

    private String appointment_name;
    private List<Ad>  ads;
    private Customer customer;
    private long total_revenue;              //Automatically from the list of ads

    private Date created_date;
    private String created_user;
    private Date lastedited_date;
    private String lastedited_user;

    private Boolean ad_delivered;

    //faktura
}
