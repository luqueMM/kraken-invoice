package bo.vulcan.kraken.invoice.data.model.request;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import bo.vulcan.kraken.invoice.utils.ErrorsManager;
import bo.vulcan.kraken.invoice.utils.Validations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class BuyAndSellRequest extends AbstractHeaderRequest<BuyAndSellDetailsRequest> {
    /**
     * Nombre o razon social del cliente
     * NotNull
     * Size(min = 1, max = 500)
     */
    private String name;

    /**
     * Número de documento de cliente
     * NotNull
     * Size(min = 1, max = 50)
     */
    private String documentNumber;

    /**
     * Mandar true cuando una factura es rechazada por nit invalido
     */
    private Boolean invalidNit;

    /**
     * Código tipo documento parametricas de impuestos
     * NotNull
     * Min(value = 1)
     * Max(value = 9)
     */
    private Integer documentTypeCode;

    /**
     * Código complemento enviar solo si el tipo de documento es CI
     * Size(max = 5)
     */
    private String documentComplement;

    /**
     * Pago con una gif card
     * DecimalMin(value = "0.00")
     */
    private BigDecimal giftCard;

    /**
     * Dirección del cliente
     * Size(min = 1, max = 500)
     */
    private String extraCustomerAddress;

    public BuyAndSellRequest(
            String name,
            String documentNumber,
            Integer documentTypeCode,
            List<BuyAndSellDetailsRequest> details) {
        super(1, details);
        this.name = name;
        this.documentNumber = documentNumber;
        this.invalidNit = false;
        this.documentTypeCode = documentTypeCode;
    }

    public String validate() {
        ErrorsManager manager = new ErrorsManager();
        manager.addAll(super.validateList());

        manager.check("name", Validations.Str.notNull(name));
        manager.check("name", Validations.Str.size(name,1, 500));

        manager.check("documentNumber", Validations.Str.notNull(documentNumber));
        manager.check("documentNumber", Validations.Str.size(documentNumber,1, 50));

        manager.check("documentTypeCode", Validations.IntegerNumber.notNull(documentTypeCode));
        manager.check("documentTypeCode", Validations.IntegerNumber.range(documentTypeCode, 1, 9));

        manager.check("documentComplement", Validations.Str.maxLength(documentComplement,5));

        manager.check("giftCard", Validations.BigDec.min(giftCard, new BigDecimal("0.00")));

        manager.check("extraCustomerAddress", Validations.Str.size(extraCustomerAddress, 1, 500));

        return manager.getErrors();
    }
}
