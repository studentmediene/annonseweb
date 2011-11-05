package no.dusken.annonseweb.models;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
public class Sale {

    private String appointmentName;
    private List<Ad>  ads;
    private Customer customer;

    private Date createdDate;
    private String createdUser;
    private Date lastEditedDate;
    private String lastEditedUser;

    private Boolean adDelivered;

    //faktura
}
