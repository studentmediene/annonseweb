package no.dusken.annonseweb.models;

public class Ad_UDprint extends Ad {

    public enum Print_format {VERTICAL,HORIZONTAL}

    private long udNr;
    private Print_format print_format;     //Updates price accordingly, or manually when created

    private Boolean placed_right;
    private Boolean placed_early;
    private long page_number;




    public Ad_UDprint(long udNr, Print_format print_format, Boolean placed_right, Boolean placed_early, long page_number,
                      long price, long discount, String file_location)    {
        super(price, discount, file_location);

        this.udNr = udNr;
        this.print_format = print_format;
        this.placed_right = placed_right;
        this.placed_early = placed_early;
        this.page_number = page_number;
    }

    public long getUdNr() {
        return udNr;
    }

    public void setUdNr(long udNr) {
        this.udNr = udNr;
    }

    public Print_format getPrint_format() {
        return print_format;
    }

    public void setPrint_format(Print_format print_format) {
        this.print_format = print_format;
    }

    public Boolean getPlaced_right() {
        return placed_right;
    }

    public void setPlaced_right(Boolean placed_right) {
        this.placed_right = placed_right;
    }

    public Boolean getPlaced_early() {
        return placed_early;
    }

    public void setPlaced_early(Boolean placed_early) {
        this.placed_early = placed_early;
    }

    public long getPage_number() {
        return page_number;
    }

    public void setPage_number(long page_number) {
        this.page_number = page_number;
    }
}
