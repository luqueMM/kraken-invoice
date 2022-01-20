package bo.vulcan.kraken.invoice.data.model.errors;

import java.util.Collections;
import java.util.List;

public class SiatCurrencyNotFoundException extends BaseException {

    private static final long serialVersionUID = 1L;

    public SiatCurrencyNotFoundException() {
        super(Collections.singletonList(new BaseException.CodeMessage(
                StatusType.NOT_FOUND.getStatusCode(),
                "siat_currency_not_found",
                "SiatCurrency not found")));
    }

    public SiatCurrencyNotFoundException(List<CodeMessage> codeMessageList) {
        super(codeMessageList);
    }
}
