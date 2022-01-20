package bo.vulcan.kraken.invoice.data.model.request;

import java.math.BigDecimal;
import java.util.List;

import bo.vulcan.kraken.invoice.utils.ErrorsManager;
import bo.vulcan.kraken.invoice.utils.Validations;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class AbstractDetailsRequest {

    /**
     * Cantidad
     * NotNull
     * DecimalMin(value = "0.00001")
     * DecimalMax(value = "999999999")
     */
    protected BigDecimal quantity;

    /**
     * detalle del producto o servicio
     * NotNull
     * Size(min = 1, max = 500)
     */
    protected String concept;

    /**
     * NotNull
     * DecimalMin(value = "-999999999")
     * DecimalMax(value = "999999999")
     */
    protected BigDecimal unitPrice;

    /**
     * NotNull
     * DecimalMin(value = "-999999999")
     * DecimalMax(value = "999999999")
     */
    protected BigDecimal subtotal;

    /**
     * Nro de posicion de los items para la impresion de factura
     * Min(value = 0)
     * Max(value = 999999999)
     */
    protected Integer sequence;

    /**
     * Código de producto configurado en kraken
     * Size(min = 1, max = 100)
     */
    protected String productCode;

    /**
     * descuento
     * DecimalMin(value = "-999999999")
     */
    protected BigDecimal discountAmount;

    public AbstractDetailsRequest(
            BigDecimal quantity,
            String concept,
            BigDecimal unitPrice,
            BigDecimal subtotal,
            String productCode) {
        this.quantity = quantity;
        this.concept = concept;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
//        this.sequence = sequence;
        this.productCode = productCode;
        this.discountAmount = new BigDecimal(0);
    }

    public List<String> validateList() {
        return validate().getList();
    }

    public String validateStr() {
        return validate().getErrors();
    }

    private ErrorsManager validate() {
        ErrorsManager manager = new ErrorsManager();

        manager.check("quantity", Validations.BigDec.notNull(quantity));
        manager.check("quantity", Validations.BigDec.range(quantity, new BigDecimal("0.00001"), new BigDecimal(999999999)));

        manager.check("concept", Validations.Str.notNull(concept));
        manager.check("concept", Validations.Str.size(concept, 1, 500));

        manager.check("unitPrice", Validations.BigDec.notNull(unitPrice));
        manager.check("unitPrice", Validations.BigDec.range(unitPrice, new BigDecimal(-999999999), new BigDecimal(999999999)));

        manager.check("subtotal", Validations.BigDec.notNull(subtotal));
        manager.check("subtotal", Validations.BigDec.range(subtotal, new BigDecimal(-999999999), new BigDecimal(999999999)));

        manager.check("sequence", Validations.IntegerNumber.range(sequence, 0, 999999999));

        manager.check("productCode", Validations.Str.size(productCode, 1, 100));

        manager.check("discountAmount", Validations.BigDec.min(discountAmount, new BigDecimal(-999999999)));

        return manager;
    }
}
