/*
 * Copyright 2013 Studentmediene i Trondheim AS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Entity
public class AnnonseNote extends DuskenObject implements ActiveAnnonseElement{

    @ManyToOne(optional = true)
    private Ad ad;

    @ManyToOne(optional = true)
    private Customer customer;

    @ManyToOne(optional = true)
    private ContactPerson contactPerson;

    @ManyToOne(optional = true)
    private Sale sale;

    @ManyToOne(optional = false)
    private AnnonsePerson createdUser;

    @ManyToOne(optional = true)
    private AnnonsePerson delegatedUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dueDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDate;

    @NotNull
    private String text;

    @NotNull
    private Boolean active = Boolean.TRUE;

    public AnnonseNote() {}

    public AnnonseNote(String text, ContactPerson contactPerson, Customer customer) {
        this.text = text;
        this.contactPerson = contactPerson;
        this.customer = customer;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public AnnonsePerson getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(AnnonsePerson createdUser) {
        this.createdUser = createdUser;
    }

    public AnnonsePerson getDelegatedUser() {
        return delegatedUser;
    }

    public void setDelegatedUser(AnnonsePerson delegatedUser) {
        this.delegatedUser = delegatedUser;
    }

    public Calendar getDueDate() {
        return dueDate;
    }

    public void setDueDate(Calendar dueDate) {
        this.dueDate = dueDate;
    }

    public Calendar getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}


