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

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
public class ContactPerson extends DuskenObject implements ActiveAnnonseElement{

    @NotNull
    private String personName;

    @NotNull
    private String email;

    @NotNull
    private String telephoneNumber;

    private String companyPosition;
    private String otherInfo;

    @ManyToOne
    private Customer customer;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastEditedTime;

    private AnnonsePerson lastEditedUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDate;

    @OneToMany(fetch = LAZY, mappedBy = "contactPerson")
    private List<AnnonseNote> annonseNotes = new ArrayList<AnnonseNote>();

    private AnnonsePerson createdUser;

    private Boolean active = Boolean.TRUE;

    public ContactPerson() {}

    public ContactPerson(String personName, String email, String telephoneNumber, String companyPosition) {
        this.setPersonName(personName);
        this.setEmail(email);
        this.setTelephoneNumber(telephoneNumber);
        this.setCompanyPosition(companyPosition);
        this.setActive(true);
    }

    public void copyInformationFrom(ContactPerson other) {
        if (other == null || this == other)
            return;
        personName = other.personName;
        email = other.email;
        telephoneNumber = other.telephoneNumber;
        companyPosition = other.companyPosition;
        otherInfo = other.otherInfo;
        customer = other.customer;
        active = other.active;
    }

    public String getPersonName() {
        return personName;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getCompanyPosition() {
        return companyPosition;
    }

    public Calendar getLastEditedTime() {
        return lastEditedTime;
    }

    public void setLastEditedTime(Calendar lastEditedTime) {
        this.lastEditedTime = lastEditedTime;
    }

    public AnnonsePerson getLastEditedUser() {
        return lastEditedUser;
    }

    public void setLastEditedUser(AnnonsePerson lastContactedUser) {
        this.lastEditedUser = lastContactedUser;
    }

    public Boolean getActive() {
        return active;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setCompanyPosition(String companyPosition) {
        this.companyPosition = companyPosition;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public Calendar getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }

    public AnnonsePerson getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(AnnonsePerson createdUser) {
        this.createdUser = createdUser;
    }

    public List<AnnonseNote> getAnnonseNotes() {
        return annonseNotes;
    }

    public void setAnnonseNotes(List<AnnonseNote> annonseNotes) {
        this.annonseNotes = annonseNotes;
    }
}