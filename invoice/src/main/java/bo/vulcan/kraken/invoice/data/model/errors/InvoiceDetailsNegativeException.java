package bo.vulcan.kraken.invoice.data.model.errors;

import java.util.Collections;

public class InvoiceDetailsNegativeException extends BaseException {
    public InvoiceDetailsNegativeException() {
        super (Collections.singletonList(
                new BaseException.CodeMessage(
                        StatusType.NOT_FOUND.getStatusCode(),
                        "invoice_details_negative_exception",
                        "Hubo un error en los detalles con valores negativos."
                )));
    }
}
