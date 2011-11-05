package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;
import no.dusken.common.model.Person;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Comment extends DuskenObject {

    private String text;

    @ManyToOne
    private Person createdBy;

    public Comment() {}

    public Comment(Person createdBy, String text) {
        this.createdBy = createdBy;
        this.text = text;
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Person getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Person createdBy) {
        this.createdBy = createdBy;
    }
}
