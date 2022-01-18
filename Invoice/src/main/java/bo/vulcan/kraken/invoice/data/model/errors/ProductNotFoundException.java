package bo.vulcan.kraken.invoice.data.model.errors;

import java.util.Collections;

public class ProductNotFoundException extends BaseException {

    public ProductNotFoundException() {

        super (Collections.singletonList(
                new BaseException.CodeMessage(
                        StatusType.NOT_FOUND.getStatusCode(),
                        "product_not_found",
                        "Product not found"
                )));
    }
}
