package no.dusken.annonseweb.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

import static javax.persistence.CascadeType.ALL;

@Entity
public class Ad_web extends Ad {

    public enum Web_format {TOP_BANNER, MID_BANNER, SIDEBAR}

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar onlineFrom;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar onlineTo;

    private Web_format webFormat;      //Updates price accordingly, or manually when created

    @ManyToOne(cascade = ALL)
    private Issue issue;

    public Calendar getOnlineFrom() {
        return onlineFrom;
    }

    public void setOnlineFrom(Calendar onlineFrom) {
        this.onlineFrom = onlineFrom;
    }

    public Calendar getOnlineTo() {
        return onlineTo;
    }

    public void setOnlineTo(Calendar onlineTo) {
        this.onlineTo = onlineTo;
    }

    public Web_format getWebFormat() {
        return webFormat;
    }

    public void setWebFormat(Web_format webFormat) {
        this.webFormat = webFormat;
    }
}
