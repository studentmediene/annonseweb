package no.dusken.annonseweb.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

import static javax.persistence.CascadeType.ALL;

@Entity
public class WebAd extends Ad {

    public enum WebFormat {TOP_BANNER, MID_BANNER, SIDEBAR}

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar onlineFrom;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar onlineTo;

    private WebFormat webFormat;      //Updates price accordingly, or manually when created

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

    public WebFormat getWebFormat() {
        return webFormat;
    }

    public void setWebFormat(WebFormat webFormat) {
        this.webFormat = webFormat;
    }
}
