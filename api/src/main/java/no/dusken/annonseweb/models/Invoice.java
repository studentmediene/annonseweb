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
    private String invoiceLabel;
    private long invoiceNr;

    private Date invoiceDate;
    private Date createdDate;
    private String createdUser;
    private Date lastEditedDate;
    private String lastEditedUser;

    public Invoice(long ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return String.valueOf(this.ID);
    }
}
