package bo.vulcan.kraken.invoice.data.model.errors;

import java.util.Collections;

public class SiatActivityNotFoundException extends BaseException {

    public SiatActivityNotFoundException() {
        super (Collections.singletonList(
                new BaseException.CodeMessage(
                        StatusType.NOT_FOUND.getStatusCode(),
                        "siat_activity_not_found",
                        "SiatActivity not found"
                )));
    }
}
