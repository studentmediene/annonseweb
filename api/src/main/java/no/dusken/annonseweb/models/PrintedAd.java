package no.dusken.annonseweb.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static javax.persistence.CascadeType.ALL;

@Entity
public class PrintedAd extends Ad {

    public enum PrintSize {
        WHOLEPAGE("Helside",15300),     // Beskrivelse , pris
        HALFPAGE("Halvside",8400),
        QUARTPAGE("Kvartside",4800);
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

    public PrintedAd(BigDecimal price, BigDecimal discount) {
        super(price, discount);
    }
    /*
    public PrintedAd(Sale sale, BigDecimal price, BigDecimal discount, Boolean active) {
        super(sale, price, discount, active);
    }  */

    public PrintedAd(Sale sale, BigDecimal price, BigDecimal discount, Boolean active, Boolean placedOnProd,
                     String placementDescription, Boolean placedInDisp, Integer issueNumber) {
        super(sale, price, discount, active, placedOnProd);
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
        if(this.printSize == PrintSize.WHOLEPAGE) { return 1;}
        else if(this.printSize == PrintSize.HALFPAGE) {return 2;}
        else if (this.printSize == PrintSize.QUARTPAGE) {return 3;}
        else {return 0;}
    }

    public void setPrintSize(Integer printSize) {
        switch (printSize) {
            case 1: this.printSize = PrintSize.WHOLEPAGE;
                break;
            case 2: this.printSize = PrintSize.HALFPAGE;
                break;
            case 3: this.printSize = PrintSize.QUARTPAGE;
                break;
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
