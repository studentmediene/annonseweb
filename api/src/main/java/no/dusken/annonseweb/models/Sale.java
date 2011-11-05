package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Entity
public class Sale extends DuskenObject {


    private String appointmentName;
    private List<Ad>  ads;

    @ManyToOne
    private Customer customer;

    private Date createdDate;
    private String createdUser;
    private Date lastEditedDate;
    private String lastEditedUser;

    private Boolean adDelivered;

    //faktura
}
