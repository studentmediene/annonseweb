package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Invoice extends DuskenObject{

    private Sale sale;
    private String invoiceLabel;
    private long invoiceNr;

    private Date invoiceDate;
    private Date createdDate;
    private String createdUser;
    private Date lastEditedDate;
    private String lastEditedUser;

}
