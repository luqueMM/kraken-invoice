package bo.vulcan.kraken.invoice.data.model.errors;

import java.util.List;

public class CuisRequestException extends BaseException {
    public CuisRequestException(List<CodeMessage> codeMessageList) {
        super(codeMessageList);
    }
}
