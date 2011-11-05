package no.dusken.annonseweb.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Ad_UDprint extends Ad {

    public enum PrintFormat {VERTICAL,HORIZONTAL}

    @ManyToOne
    private Issue issue;

    private PrintFormat printFormat;     //Updates price accordingly, or manually when created

    private Boolean placedRight;
    private Boolean placedEarly;
    private Integer pageNumber;


    public Ad_UDprint() {}

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public PrintFormat getPrintFormat() {
        return printFormat;
    }

    public void setPrintFormat(PrintFormat printFormat) {
        this.printFormat = printFormat;
    }

    public Boolean getPlacedRight() {
        return placedRight;
    }

    public void setPlacedRight(Boolean placedRight) {
        this.placedRight = placedRight;
    }

    public Boolean getPlacedEarly() {
        return placedEarly;
    }

    public void setPlacedEarly(Boolean placedEarly) {
        this.placedEarly = placedEarly;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
}
