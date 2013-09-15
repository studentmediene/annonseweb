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
public class WebAd extends Ad {

    public enum WebFormat {
        SONE_1_FULL_BANNER("Innen 5 første artikkerader (hovedside - øverst) - heldekkende (1170x380px)", 10000),// Beskrivelse , pris
        SONE_1_MEDIUM_BANNER("Innen 5 første artikkerader (hovedside - øverst) - medium størrelse (780x380px)", 8500),
        SONE_1_SMALL_BANNER("Innen 5 første artikkerader (hovedside - øverst) - liten størrelse (270x380px)", 7000),
        SONE_2_FULL_BANNER("Innen artikkerader 6 - 15 (hovedside) - heldekkende (1170x380px)", 8000),
        SONE_2_MEDIUM_BANNER("Innen artikkerader 6 - 15 (hovedside) - medium størrelse (780x380px)", 6500),
        SONE_2_SMALL_BANNER("Innen artikkerader 6 - 15 (hovedside) - liten størrelse (270x380px)", 5000),
        SONE_3_FULL_BANNER("Innen artikkerader 16 - 25 (hovedside - nederst) - heldekkende (1170x380px)", 4500),
        SONE_3_MEDIUM_BANNER("Innen artikkerader 16 - 25 (hovedside - nederst) - medium størrelse (780x380px)", 4000),
        SONE_3_SMALL_BANNER("Innen artikkerader 16 - 25 (hovedside - nederst) - liten størrelse (270x380px)", 3000),
        POPULAR_ARTICLE("Annonse på en populær artikkelside", 3000),
        SUBPAGE_RADIO_TV("Annonse på en av undersidene (radio/tv)", 4000);
        private final Integer price;
        private final String description;

        WebFormat(String desc, Integer value){
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

    private WebFormat webFormat;      //Updates price accordingly, or manually when created

    private transient String editNumber;

    public WebAd(){}

    public WebAd(Sale sale, BigDecimal price, BigDecimal discount, Boolean active, Boolean placedOnProd,
                 AnnonsePerson createdUser, Integer webFormat, String onlineFrom, String onlineTo) {
        super(sale, price, discount, active, placedOnProd, createdUser);
        this.setWebFormat(webFormat);
        this.setOnlineFrom(onlineFrom);
        this.setOnlineTo(onlineTo);
    }

    public void cloneFrom(WebAd other) {
        if (other == null){
            return;
        }
        this.setPrice(other.getPrice());
        this.setDiscount(other.getDiscount());
        this.setActive(other.getActive());
        this.setSale(other.getSale());
        this.setLastEditedDate();
        this.webFormat = other.webFormat;
        this.onlineFrom = other.onlineFrom;
        this.onlineTo = other.onlineTo;
        this.setPlacedOnProd(other.getPlacedOnProd());

    }

    public void cloneFrom(PrintedAd other) {}


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
        } catch (Exception e) {}
    }

    public String getOnlineTo() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String strdate = null;
        if (this.onlineTo != null) {
            strdate = sdf.format(this.onlineTo.getTime());
        }
        return strdate;
    }

    public void setOnlineTo(String onlineTo) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            this.onlineTo.setTime(sdf.parse(onlineTo));
        } catch (Exception e) {}
    }

    public Integer getWebFormat() {
        WebFormat[] formats = WebFormat.values();
        for (int i = 0; i < formats.length; i++)
            if (formats[i] == this.webFormat)
                return i + 1;
        return 0;
    }

    public void setWebFormat(Integer webFormat) {
        WebFormat[] formats = WebFormat.values();
        int indice = webFormat - 1;
        if (indice < 0 || indice > formats.length)
            return;
        this.webFormat = formats[indice];
    }

    public Map<Integer,String> getWebFormatList(){
        Map<Integer,String> webFormatList = new HashMap<Integer,String>();
        Integer i = 1;
        for(WebFormat sizes : webFormat.values()){
            webFormatList.put(i,sizes.getDescription());
            i++;
        }
        return webFormatList;
    }

    public List<Integer> getPriceByWebFormat(){
        List<Integer> prices = new ArrayList<Integer>();
        Integer i = 1;
        for(WebFormat sizes : webFormat.values()){
            prices.add(sizes.getPrice());
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
