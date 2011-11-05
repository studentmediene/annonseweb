package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
public class Invoice extends DuskenObject{

    @OneToMany(fetch = LAZY, cascade = ALL)
    private List<Sale> sales;
    private String invoiceLabel;
    private long invoiceNr;

    private Date invoiceDate;
    private Date createdDate;
    private String createdUser;
    private Date lastEditedDate;
    private String lastEditedUser;

}
