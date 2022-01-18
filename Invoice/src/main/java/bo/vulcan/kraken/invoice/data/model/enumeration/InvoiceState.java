package bo.vulcan.kraken.invoice.data.model.enumeration;

public enum InvoiceState {

    PENDING('P'),
    VALIDATED('V'),
    CANCELED('A'),
    REJECTED('R');

    public final Character code;

    InvoiceState(Character code) {
        this.code = code;
    }
}
