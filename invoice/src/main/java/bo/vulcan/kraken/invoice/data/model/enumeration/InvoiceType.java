package bo.vulcan.kraken.invoice.data.model.enumeration;

public enum InvoiceType {

    WEB("WEB"),
    GENERIC("GENERIC");

    public final String name;

    InvoiceType(String name) {
        this.name = name;
    }
}
