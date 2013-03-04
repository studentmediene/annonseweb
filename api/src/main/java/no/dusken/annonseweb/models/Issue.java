package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

/**
 * @author Marvin B. Lillehaug <lillehau@underdusken.no>
 */
@Entity
public class Issue extends DuskenObject {
    private Integer issueNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fromDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar toDate;

    @OneToMany(fetch = LAZY, cascade = ALL, mappedBy = "issue")
    private List<PrintedAd> printedAds;

    @Override
    public String getTitle() {
        return null;
    }

    public Integer getIssueNumber(){
        return issueNumber;
    }

    public void setIssueNumber(Integer issueNumber){
        this.issueNumber = issueNumber;
    }
}
