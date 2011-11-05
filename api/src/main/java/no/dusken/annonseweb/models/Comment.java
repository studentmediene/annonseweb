package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Comment extends DuskenObject {

    private String text;

    private Date createdDate;
    private String createdUser;
    private Date lastEditedDate;
    private String lastEditedUser;

    public Comment(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public Date getLastEditedDate() {
        return lastEditedDate;
    }

    public String getLastEditedUser() {
        return lastEditedUser;
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
