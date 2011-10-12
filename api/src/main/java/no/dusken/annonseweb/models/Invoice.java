package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

public class Invoice extends DuskenObject{

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

    public Invoice(long ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return String.valueOf(this.ID);
    }
}
