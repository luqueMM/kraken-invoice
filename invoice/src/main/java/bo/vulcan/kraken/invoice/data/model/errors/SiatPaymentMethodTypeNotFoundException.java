package bo.vulcan.kraken.invoice.data.model.errors;

import java.util.Collections;

public class SiatPaymentMethodTypeNotFoundException extends BaseException {

    private static final long serialVersionUID = 1L;

    public SiatPaymentMethodTypeNotFoundException() {
        super(Collections.singletonList(new BaseException.CodeMessage(
                StatusType.NOT_FOUND.getStatusCode(),
                "siat_payment_type_not_found",
                "SiatPaymentType not found")));
    }

}
