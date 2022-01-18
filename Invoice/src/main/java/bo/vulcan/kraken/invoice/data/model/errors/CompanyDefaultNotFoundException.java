package bo.vulcan.kraken.invoice.data.model.errors;

import java.util.Collections;

public class CompanyDefaultNotFoundException extends BaseException {

    private static final long serialVersionUID = 1L;

    public CompanyDefaultNotFoundException(String field) {
        super(Collections.singletonList(
                new BaseException.CodeMessage(
                        StatusType.NOT_FOUND.getStatusCode(),
                        "company_default_not_found",
                        "Company Default not found" +field)
        ));
    }
}
