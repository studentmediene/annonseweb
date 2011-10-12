package no.dusken.annonseweb.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

public class Invoice {

    @Id
    @GeneratedValue
    private long ID;

    private Sale sale;
    private String invoice_label;
    private long invoice_nr;

    private Date invoice_date;
    private Date created_date;
    private String created_user;
    private Date lastedited_date;
    private String lastedited_user;


}
