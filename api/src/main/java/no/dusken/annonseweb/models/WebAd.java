package no.dusken.annonseweb.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.*;
import java.text.SimpleDateFormat;

import static javax.persistence.CascadeType.ALL;

@Entity
public class WebAd extends Ad {

    public enum WebFormat {
        BANNER("Horisontalt banner (Forside - 1180x160px)",10000),     // Beskrivelse , pris
        BLOCK_MAIN("Blokkannonse (Forside - 380x290px)",10000),
        BLOCK_ARTICLE("Blokkannonse (Artikkel - 380x410px)",10000);
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
        if(this.webFormat == WebFormat.BANNER) { return 1;}
        else if(this.webFormat == WebFormat.BLOCK_MAIN) {return 2;}
        else if (this.webFormat == WebFormat.BLOCK_ARTICLE) {return 3;}
        else {return 0;}
    }

    public void setWebFormat(Integer webFormat) {
        switch (webFormat) {
            case 1: this.webFormat = WebFormat.BANNER;
                break;
            case 2: this.webFormat = WebFormat.BLOCK_MAIN;
                break;
            case 3: this.webFormat = WebFormat.BLOCK_ARTICLE;
                break;
        }
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
