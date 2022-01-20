package bo.vulcan.kraken.invoice.data.model.errors;

import java.util.Collections;

public class SiatDocumentTypeNotFoundException extends BaseException {

    private static final long serialVersionUID = 1L;

    public SiatDocumentTypeNotFoundException() {
        super(Collections.singletonList(new BaseException.CodeMessage(
                StatusType.NOT_FOUND.getStatusCode(),
                "siat_document_type_not_found",
                "SiatDocumentType not found")));
    }
}
