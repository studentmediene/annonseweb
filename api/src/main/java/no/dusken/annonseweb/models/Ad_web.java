package no.dusken.annonseweb.models;

import java.util.Date;

public class Ad_web extends Ad {

    public enum Web_format {TOP_BANNER, MID_BANNER, SIDEBAR}

    private Date online_from;
    private Date online_to;
    private Web_format web_format;      //Updates price accordingly, or manually when created

    public Ad_web(Date online_from, Date online_to, Web_format web_format,
                  long price, long discount, String file_location) {
        super(price, discount, file_location);
        this.online_from = online_from;
        this.online_to = online_to;
        this.web_format = web_format;
    }

    public Date getOnline_from() {
        return online_from;
    }

    public void setOnline_from(Date online_from) {
        this.online_from = online_from;
    }

    public Date getOnline_to() {
        return online_to;
    }

    public void setOnline_to(Date online_to) {
        this.online_to = online_to;
    }

    public Web_format getWeb_format() {
        return web_format;
    }

    public void setWeb_format(Web_format web_format) {
        this.web_format = web_format;
    }
}
