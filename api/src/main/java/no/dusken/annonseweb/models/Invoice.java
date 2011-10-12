package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: kiro
 * Date: Nov 8, 2010
 * Time: 10:07:54 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Invoice extends DuskenObject {

    public Invoice(Long id){
        this.ID = id;
    }

    @Id
    long ID;

    public Long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return null;
    }
}
