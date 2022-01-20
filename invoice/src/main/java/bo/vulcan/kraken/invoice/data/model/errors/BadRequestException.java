package bo.vulcan.kraken.invoice.data.model.errors;

import java.util.Collections;

public class BadRequestException extends BaseException {

    public BadRequestException(String errorKey, String detail) {
        super(Collections.singletonList(
                new BaseException.CodeMessage(
                        StatusType.NOT_FOUND.getStatusCode(),
                        errorKey,
                        detail)));
    }

    public BadRequestException(Integer statusCode, String errorKey, String detail) {
        super(Collections.singletonList(
                new BaseException.CodeMessage(
                        statusCode,
                        errorKey,
                        detail)));
    }

}
