package bo.vulcan.kraken.invoice.data.model.errors;

import java.util.Collections;

public class SiatProductNotFoundException extends BaseException {

    public SiatProductNotFoundException() {

        super (Collections.singletonList(
                new BaseException.CodeMessage(
                        StatusType.NOT_FOUND.getStatusCode(),
                        "siat_product_not_found",
                        "SiatProduct not found"
                )));
    }
}
