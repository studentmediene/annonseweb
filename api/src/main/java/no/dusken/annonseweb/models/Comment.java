package no.dusken.annonseweb.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

public class Comment {

    @Id
    @GeneratedValue
    private long ID;

    String text;

    Date created_date;
    String created_user;
    Date lastedited_date;

    public Comment(String text) {
        this.text = text;
    }

    public long getID() {
        return ID;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public String getCreated_user() {
        return created_user;
    }

    public Date getLastedited_date() {
        return lastedited_date;
    }

    public String getLastedited_user() {
        return lastedited_user;
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    String lastedited_user;
}
