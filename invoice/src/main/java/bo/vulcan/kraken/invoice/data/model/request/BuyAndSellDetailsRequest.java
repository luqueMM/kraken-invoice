package bo.vulcan.kraken.invoice.data.model.request;

import java.math.BigDecimal;

import bo.vulcan.kraken.invoice.utils.ErrorsManager;
import bo.vulcan.kraken.invoice.utils.Validations;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class BuyAndSellDetailsRequest extends AbstractDetailsRequest {

    /**
     * Size(min = 1, max = 1500)
     */
    private String seriesNumber;

    /**
     * Size(min = 1, max = 1500)
     */
    private String imeiNumber;

    public BuyAndSellDetailsRequest(
            BigDecimal quantity,
            String concept,
            BigDecimal unitPrice,
            BigDecimal subtotal,
            String productCode) {
        super(quantity, concept, unitPrice, subtotal, productCode);
    }

    public String validate() {
        ErrorsManager manager = new ErrorsManager();
        manager.addAll(super.validateList());

        manager.check("seriesNumber", Validations.Str.size(seriesNumber,1,1500));

        manager.check("imeiNumber", Validations.Str.size(imeiNumber,1,1500));

        return manager.getErrors();
    }
}
