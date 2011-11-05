package no.dusken.annonseweb.models;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Ad_web extends Ad {

    public enum Web_format {TOP_BANNER, MID_BANNER, SIDEBAR}

    private Date onlineFrom;
    private Date onlineTo;
    private Web_format webFormat;      //Updates price accordingly, or manually when created

    public Ad_web(long price, long discount, String file_location, Date onlineFrom, Date onlineTo, Web_format webFormat) {
        super(price, discount, file_location);
        this.onlineFrom = onlineFrom;
        this.onlineTo = onlineTo;
        this.webFormat = webFormat;
    }

    public Date getOnlineFrom() {
        return onlineFrom;
    }

    public void setOnlineFrom(Date onlineFrom) {
        this.onlineFrom = onlineFrom;
    }

    public Date getOnlineTo() {
        return onlineTo;
    }

    public void setOnlineTo(Date onlineTo) {
        this.onlineTo = onlineTo;
    }

    public Web_format getWebFormat() {
        return webFormat;
    }

    public void setWebFormat(Web_format webFormat) {
        this.webFormat = webFormat;
    }
}
