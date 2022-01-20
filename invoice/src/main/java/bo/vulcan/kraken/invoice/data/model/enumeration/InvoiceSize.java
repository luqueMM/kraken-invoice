package bo.vulcan.kraken.invoice.data.model.enumeration;

public enum InvoiceSize {
    ROLL(1),
    HALF_SHEET(2);

    private Integer value;

    InvoiceSize(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}