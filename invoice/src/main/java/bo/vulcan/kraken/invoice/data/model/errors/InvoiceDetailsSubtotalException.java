package bo.vulcan.kraken.invoice.data.model.errors;

import java.util.Collections;

public class InvoiceDetailsSubtotalException extends BaseException {
    public InvoiceDetailsSubtotalException() {
        super (Collections.singletonList(
                new BaseException.CodeMessage(
                        StatusType.NOT_FOUND.getStatusCode(),
                        "invoice_details_subtotal",
                        "El subtotal del detalle esta mal definido."
                )));
    }
}
