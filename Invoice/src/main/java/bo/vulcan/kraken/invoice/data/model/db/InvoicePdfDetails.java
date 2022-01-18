package bo.vulcan.kraken.invoice.data.model.db;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class InvoicePdfDetails {

    private Long id;
    private BigDecimal quantity;
    private String concept;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
    private Integer sequence;
    private String productCode;
    private BigDecimal discount;
    private String unitMeasurement;
    private BigDecimal amountIceSpecific;
    private BigDecimal amountIcePercentage;
    private String nandinaCode;
}
