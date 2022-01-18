package bo.vulcan.kraken.invoice.data.model.errors;

import java.util.Collections;

public class DigitalSignNotFoundException extends BaseException {

    public DigitalSignNotFoundException() {
        super(Collections.singletonList(
                new BaseException.CodeMessage(
                        StatusType.NOT_FOUND.getStatusCode(),
                        "digital_sign_not_found",
                        "Digital Sign not found")));
    }
}
