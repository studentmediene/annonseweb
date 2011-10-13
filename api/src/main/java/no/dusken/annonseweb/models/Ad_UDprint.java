package no.dusken.annonseweb.models;

public class Ad_UDprint extends Ad {

    public enum PrintFormat {VERTICAL,HORIZONTAL}

    private long udNr;
    private PrintFormat printFormat;     //Updates price accordingly, or manually when created

    private Boolean placedRight;
    private Boolean placedEarly;
    private long pageNumber;

    public Ad_UDprint(long price, long discount, String fileLocation, long udNr, PrintFormat printFormat,
                      Boolean placedRight, Boolean placedEarly, long pageNumber) {
        super(price, discount, fileLocation);
        this.udNr = udNr;
        this.printFormat = printFormat;
        this.placedRight = placedRight;
        this.placedEarly = placedEarly;
        this.pageNumber = pageNumber;
    }

    public long getUdNr() {
        return udNr;
    }

    public void setUdNr(long udNr) {
        this.udNr = udNr;
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

    public long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(long pageNumber) {
        this.pageNumber = pageNumber;
    }
}
