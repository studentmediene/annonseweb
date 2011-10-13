package no.dusken.annonseweb.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

public class Sale {

    @Id
    @GeneratedValue
    private long ID;

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
