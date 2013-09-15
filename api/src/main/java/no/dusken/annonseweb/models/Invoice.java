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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
public class Invoice extends DuskenObject{

    //todo cleanup

    @OneToMany
    private List<Sale> sales = new ArrayList<Sale>();

    private String description;
    private Long invoiceNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar invoiceDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDate;

    @ManyToOne
    private AnnonsePerson createdUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastEditedDate;

    @ManyToOne
    private AnnonsePerson lastEditedUser;


    public Invoice(){}

    public Invoice(List<Sale> sales, String description, Long invoiceNumber, Calendar invoiceDate) {
        this.setSales(sales);
        this.setDescription(description);
        this.setInvoiceNumber(invoiceNumber);
        this.invoiceDate = invoiceDate;
    }

    public void cloneFrom(Invoice other) {
        if (other == null){
            return;
        }
        this.setSales(other.getSales());
        this.setDescription(other.getDescription());
        this.setInvoiceNumber(other.getInvoiceNumber());
        this.setInvoiceDate(other.getInvoiceDate());
        this.setCreatedUser(other.getCreatedUser());
        this.setLastEditedUser(other.getLastEditedUser());
        this.setCreatedDate(other.createdDate);
        this.setLastEditedDate(other.lastEditedDate);
    }

    public List<Sale> getSales() {
        return sales;
    }

    /*@Override
    public String getTitle() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    } */

    public void setSales(List<Sale> sales) {
        this.sales = sales;
        for(Sale sale : this.sales){
            sale.setInvoiceGenerated(true);
            sale.setInvoice(this);
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setInvoiceDate(String invoiceDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            this.invoiceDate.setTime(sdf.parse(invoiceDate));
        } catch (Exception e) {}
    }

    public String getInvoiceDate() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        if (this.invoiceDate == null) {
            this.invoiceDate = Calendar.getInstance();
            this.invoiceDate.add(Calendar.DATE,14);
        }
        return sdf.format(this.invoiceDate.getTime());
    }

    public String getCreatedDate() {
        // todo
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        String strdate = null;
        if (this.createdDate != null) {
            strdate = sdf.format(this.createdDate.getTime());
        }
        return strdate;
    }

    public void setCreatedDate(Calendar time) {
        this.createdDate = time;
    }

    public AnnonsePerson getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(AnnonsePerson createdUser) {
        this.createdUser = createdUser;
    }

    public String getLastEditedDate() {
        //todo
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        String strdate = null;
        if (this.lastEditedDate != null) {
            strdate = sdf.format(this.lastEditedDate.getTime());
        }
        return strdate;
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
}
