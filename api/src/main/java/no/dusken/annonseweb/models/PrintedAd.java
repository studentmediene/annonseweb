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
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.persistence.CascadeType.ALL;

@Entity
public class PrintedAd extends Ad {

    public enum PrintSize {
        DOUBLEPAGE("Dobbeltside",27300),     // Beskrivelse , pris
        WHOLEAGE_LARGE("Bakside (330x233mm (+3mm))",19800),
        WHOLEPAGE_SMALL("Helside (300x203mm)",15800),
        HALFPAGE_HORISONTAL("1/2 side - liggende (147x203mm)",8400),
        HALFPAGE_VERTICAL("1/2 side - stående (300x96mm)",8400),
        QUARTPAGE_HORISONTAL("1/4 side - liggende (147x96mm)",4800),
        QUARTPAGE_VERTICAL("1/4 side - stående (77x203mm)",4800),
        OTHER("Annet",0);
        private final Integer price;
        private final String description;

        PrintSize(String desc, Integer value){
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



    @ManyToOne(cascade = ALL)
    private Issue issue;

    private PrintSize printSize;

    private String placementDescription;
    private Boolean placedInDisp;
    private transient String editNumber;



    public PrintedAd() {}

    public PrintedAd( BigDecimal price, BigDecimal discount) {
        super(price, discount);
    }

    public PrintedAd(Sale sale, BigDecimal price, BigDecimal discount, Boolean active, Boolean placedOnProd,
                     AnnonsePerson createdUser, String placementDescription, Boolean placedInDisp, Integer issueNumber) {
        super(sale, price, discount, active, placedOnProd, createdUser);
        this.placementDescription = placementDescription;
        this.placedInDisp = placedInDisp;
        this.issue.setIssueNumber(issueNumber);
    }

    public void cloneFrom(PrintedAd other) {
        if (other == null ){
            return;
        }
        this.setPrice(other.getPrice());
        this.setDiscount(other.getDiscount());
        this.setActive(other.getActive());
        this.setSale(other.getSale());
        this.placementDescription = other.placementDescription;
        this.placedInDisp = other.placedInDisp;
        this.setPlacedOnProd(other.getPlacedOnProd());
        this.issue.setIssueNumber(other.issue.getIssueNumber());
    }
    public void cloneFrom(WebAd other) {}

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public String getPlacementDescription() {
        return placementDescription;
    }

    public void setPlacementDescription(String placementDescription) {
        this.placementDescription = placementDescription;
    }

    public Boolean getPlacedInDisp() {
        return placedInDisp;
    }

    public void setPlacedInDisp(Boolean placedInDisp) {
        this.placedInDisp = placedInDisp;
    }

    public void setEditNumber(String editNumber) {
        try {
            Long.valueOf(editNumber);
            this.editNumber = editNumber;
        } catch (NumberFormatException e) {}
    }

    public String getEditNumber() {
        return editNumber;
    }

    public Integer getPrintSize() {
        if(this.printSize == PrintSize.DOUBLEPAGE) { return 1;}
        else if(this.printSize == PrintSize.WHOLEAGE_LARGE) {return 2;}
        else if (this.printSize == PrintSize.WHOLEPAGE_SMALL) {return 3;}
        else if (this.printSize == PrintSize.HALFPAGE_HORISONTAL) {return 4;}
        else if (this.printSize == PrintSize.HALFPAGE_VERTICAL) {return 5;}
        else if (this.printSize == PrintSize.QUARTPAGE_HORISONTAL) {return 6;}
        else if (this.printSize == PrintSize.QUARTPAGE_VERTICAL) {return 7;}
        else {return 0;}
    }

    public void setPrintSize(Integer printSize) {
        switch (printSize) {
            case 1: this.printSize = PrintSize.DOUBLEPAGE;
                break;
            case 2: this.printSize = PrintSize.WHOLEAGE_LARGE;
                break;
            case 3: this.printSize = PrintSize.WHOLEPAGE_SMALL;
                break;
            case 4: this.printSize = PrintSize.HALFPAGE_HORISONTAL;
                break;
            case 5: this.printSize = PrintSize.HALFPAGE_VERTICAL;
                break;
            case 6: this.printSize = PrintSize.QUARTPAGE_HORISONTAL;
                break;
            case 7: this.printSize = PrintSize.QUARTPAGE_VERTICAL;
                break;
            default: this.printSize = PrintSize.OTHER;
        }
    }

    public Map<Integer,String> getPrintSizeList(){
        Map<Integer,String> printSizeList = new HashMap<Integer,String>();
        Integer i = 1;
        for(PrintSize sizes : printSize.values()){
            printSizeList.put(i,sizes.getDescription());
            i++;
        }
        return printSizeList;
    }

    public List<Integer> getPriceByPrintSize(){
        List<Integer> prices = new ArrayList<Integer>();
        Integer i = 1;
        for(PrintSize sizes : printSize.values()){
            prices.add(sizes.getPrice());
            i++;
        }
        return prices;
    }

}
