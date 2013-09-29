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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "sale")
@SequenceGenerator(name = "sale_seq", sequenceName = "sale_id_seq")
public class Sale extends DuskenObject implements ActiveAnnonseElement{

    private String description;

    @OneToMany(fetch = LAZY, mappedBy = "sale")
    private List<Ad>  ads = new ArrayList<Ad>();

    @ManyToOne(optional = false)
    @NotNull
    private Customer customer;

    @ManyToOne
    private AnnonsePerson createdUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastEditedDate;

    @ManyToOne
    private AnnonsePerson lastEditedUser;

    private Boolean adReceived;

    private Boolean invoiceGenerated;

    @ManyToOne(optional = true, cascade = ALL)
    public Invoice invoice;

    @OneToMany(fetch = LAZY, mappedBy = "sale")
    private List<AnnonseNote> annonseNotes = new ArrayList<AnnonseNote>();

    @NotNull
    private Boolean active = Boolean.TRUE;

    public Sale(){}

    public Sale(Customer customer){
        setCustomer(customer);
    }

    public Sale(String description, List<Ad> ads, Customer customer, AnnonsePerson createdUser, Boolean adReceived, Boolean invoiceGenerated ) {
        this.description = description;
        this.ads = ads;
        this.customer = customer;
        this.createdUser = createdUser;
        this.lastEditedDate = Calendar.getInstance();
        this.lastEditedUser = createdUser;
        this.adReceived = adReceived;
        this.invoiceGenerated = invoiceGenerated;
    }

    /**
     * Clones all non-transient information about this <code>Sale</code>.
     * @param other <code>Sale</code> to clone information from
     */
    public void cloneFrom(Sale other) {
        if (other == null) {
            return;
        }
        setDescription(other.description);
        setAds(other.ads);
        setCustomer(other.customer);
        setCreatedUser(other.createdUser);
        setLastEditedDate(other.lastEditedDate);
        setLastEditedUser(other.lastEditedUser);
        setAdReceived(other.adReceived);
        invoice = other.invoice;
        this.active = other.active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AnnonsePerson getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(AnnonsePerson createdUser) {
        this.createdUser = createdUser;
    }

    public Calendar getLastEditedDate() {
        return lastEditedDate;
    }

    public void setLastEditedDate(Calendar lastEditedDate) {
        this.lastEditedDate = lastEditedDate;
    }

    public AnnonsePerson getLastEditedUser() {
        return lastEditedUser;
    }

    public void setLastEditedUser(AnnonsePerson lastEditedUser) {
        this.lastEditedUser = lastEditedUser;
    }

    public Boolean getAdReceived() {
        return adReceived;
    }

    public void setAdReceived(Boolean adReceived) {
        this.adReceived = adReceived;
    }

    public Boolean getInvoiceGenerated() {
        return invoiceGenerated;
    }

    public void setInvoiceGenerated(Boolean invoiceGenerated) {
        this.invoiceGenerated = invoiceGenerated;
    }

    public Invoice getInvoice() {
        return this.invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public List<AnnonseNote> getAnnonseNotes() {
        return annonseNotes;
    }

    public void setAnnonseNotes(List<AnnonseNote> annonseNotes) {
        this.annonseNotes = annonseNotes;
    }

    @Override
    public Boolean getActive() {
        return active;
    }

    @Override
    public void setActive(Boolean active) {
        this.active = active;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal price = new BigDecimal(0);
        if (ads != null) {
            for (Ad ad: ads) {
                price = price.add(ad.getFinalPrice());
            }
        }
        return price;
    }
}