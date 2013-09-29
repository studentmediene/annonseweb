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

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class RadioAd extends Ad {

    public enum AdFormat {
        PROGRAM("Radio",10000),     // Beskrivelse , pris
        PODCAST("Podcast",10000);
        private final Integer price;
        private final String description;

        AdFormat(String desc, Integer value){
            price = value;
            description = desc;
        }

        public Integer getPrice(){
            return price;
        }

        public String getDescription(){
            return description;
        }
    }

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar onlineFrom = Calendar.getInstance();

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar onlineTo = Calendar.getInstance();

    private AdFormat adFormat;      //Updates price accordingly, or manually when created

    private transient String editNumber;

    public RadioAd(){}

    public RadioAd(Sale sale, BigDecimal price, BigDecimal discount, Boolean active, Boolean placedOnProd,
                 AnnonsePerson createdUser, Integer adFormat, String onlineFrom) {
        super(sale, price, discount, active, placedOnProd, createdUser);
        this.setAdFormat(adFormat);
        this.setOnlineFrom(onlineFrom);
    }

    public void cloneFrom(RadioAd other) {
        if (other == null){
            return;
        }
        this.setPrice(other.getPrice());
        this.setDiscount(other.getDiscount());
        this.setActive(other.getActive());
        this.setSale(other.getSale());
        this.setLastEditedDate();
        this.adFormat = other.adFormat;
        this.onlineFrom = other.onlineFrom;
        this.onlineTo = other.onlineTo;
        this.setPlacedOnProd(other.getPlacedOnProd());

    }

    public void cloneFrom(PrintedAd other) {}

    @Override
    public String getDescription() {
        return "Radio Revolt annonse " + adFormat.getDescription() + " - " + onlineFrom.getTime() + " - " + onlineTo.getTime();
    }

    public void cloneFrom(WebAd other) {}

    public String getOnlineFrom() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String strdate = null;
        if (this.onlineFrom != null) {
            strdate = sdf.format(this.onlineFrom.getTime());
        }
        return strdate;
    }

    public void setOnlineFrom(String onlineFrom) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            this.onlineFrom.setTime(sdf.parse(onlineFrom));
            setOnlineTo(onlineFrom);
        } catch (Exception e) {}
    }

    public void setOnlineTo(String onlineFrom) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            this.onlineTo.setTime(sdf.parse(onlineFrom));
            if(this.adFormat == AdFormat.PROGRAM){
                this.onlineTo.add(Calendar.DATE,7);
            }
            else{
                this.onlineTo.add(Calendar.DATE, 10000);
            }
        } catch (Exception e) {}
    }

    public String getOnlinePeriod() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String strdate = null;
        if (this.onlineFrom != null && this.onlineTo != null) {
            strdate = sdf.format(this.onlineFrom.getTime());
            strdate += " - ";
            strdate += sdf.format(this.onlineTo.getTime());
        }
        return strdate;
    }

    public Integer getAdFormat() {
        if(this.adFormat == AdFormat.PROGRAM) { return 1;}
        else if(this.adFormat == AdFormat.PODCAST) {return 2;}
        else {return 0;}
    }

    public void setAdFormat(Integer webFormat) {
        switch (webFormat) {
            case 1: this.adFormat = AdFormat.PROGRAM;
                break;
            case 2: this.adFormat = AdFormat.PODCAST;
                break;
        }
    }

    public String getAdDescription() {
        return this.adFormat.getDescription();
    }

    public Map<Integer,String> getAdFormatList(){
        Map<Integer,String> adFormatList = new HashMap<Integer,String>();
        Integer i = 1;
        for(AdFormat formats : adFormat.values()){
            adFormatList.put(i,formats.getDescription());
            i++;
        }
        return adFormatList;
    }

    public List<Integer> getPriceByAdFormat(){
        List<Integer> prices = new ArrayList<Integer>();
        Integer i = 1;
        for(AdFormat formats : adFormat.values()){
            prices.add(formats.getPrice());
            i++;
        }
        return prices;
    }

    public String getEditNumber() {
        return editNumber;
    }

    public void setEditNumber(String editNumber) {
        try {
            Long.valueOf(editNumber);
            this.editNumber = editNumber;
        } catch (NumberFormatException e) {}
    }

    public Integer getFromDay() {
        return this.onlineFrom.get(Calendar.DAY_OF_MONTH);
    }

    public Integer getFromMonth() {
        return this.onlineFrom.get(Calendar.MONTH) + 1;
    }

    public Integer getFromYear() {
        return this.onlineFrom.get(Calendar.YEAR);
    }

    public Integer getToDay() {
        return this.onlineTo.get(Calendar.DAY_OF_MONTH);
    }

    public Integer getToMonth() {
        return this.onlineTo.get(Calendar.MONTH) + 1;
    }

    public Integer getToYear() {
        return this.onlineTo.get(Calendar.YEAR);
    }
}
