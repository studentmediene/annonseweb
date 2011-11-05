package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

/**
 * @author Marvin B. Lillehaug <lillehau@underdusken.no>
 */
@Entity
public class Issue extends DuskenObject {

    private Integer issueNumber;

    private Date fromDate;
    private Date toDate;

    @OneToMany(fetch = LAZY, cascade = ALL, mappedBy = "issue")
    private List<Ad> ads;
}
