package bo.vulcan.kraken.invoice.data.model.errors;

import java.util.Collections;

public class InvoiceCurrencyFieldsException extends BaseException {
    public InvoiceCurrencyFieldsException() {
        super(Collections.singletonList(new BaseException.CodeMessage(
                StatusType.NOT_FOUND.getStatusCode(),
                "invoice_currency_fields",
                "Los campos de moneda no estan bien definidos.")));
    }
}
