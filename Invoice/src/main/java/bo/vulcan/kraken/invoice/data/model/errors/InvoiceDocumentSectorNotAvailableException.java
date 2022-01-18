package bo.vulcan.kraken.invoice.data.model.errors;

import java.util.Collections;

public class InvoiceDocumentSectorNotAvailableException extends BaseException {
    public InvoiceDocumentSectorNotAvailableException() {
        super(Collections.singletonList(
                new BaseException.CodeMessage(
                        StatusType.NOT_FOUND.getStatusCode(),
                        "invoice_document_sector_not_available",
                        "El tipo de Document Sector no esta disponible.")));
    }
}