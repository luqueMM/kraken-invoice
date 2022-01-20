package bo.vulcan.kraken.invoice.data.model.request;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.List;

import bo.vulcan.kraken.invoice.utils.ErrorsManager;
import bo.vulcan.kraken.invoice.utils.Validations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class AbstractHeaderRequest<T extends AbstractDetailsRequest> {

    /**
     * Id externo de la empresa
     * */
    private String id;

    //TODO get DB
    private Long branchId;

    //TODO get DB
//    @Min(value = 0)
    private Integer branchNumber;

    //TODO get DB by default
    private Long economicActivityId;

    //TODO get DB
    private Long posId;

    /**
     * Código cliente de la empresa
     * Size(min = 1, max = 100)
     */
    private String customerCode;

    /**
     * Código del método de pago
     * Min(value = 1)
     * Max(value = 400)
     */
    private Integer paymentMethodType;

    /**
     * Enviar solo si el método de pago es tarjeta
     * Min(value = 0)
     * Max(value = 9999999999999999)
     */
    private String cardNumber;

    /**
     * DecimalMin(value = "0.00")
     */
    private BigDecimal additionalDiscount;

    //TODO get DB by default de la empresa
    /**
     * Tipo de Cambio de Moneda
     * Enviar el código de moneda (parametricas de impuestos) 1 = BOLIVIANOS
     * Min(value = 1)
     * Max(value = 400)
     */
    private Integer currencyIso;

    //TODO get DB by default
    /**
     * Tipo de Cambio de Moneda
     * Enviar el valor de la moneda. 1 para BOLIVIANOS
     * DecimalMin(value = "0.00")
     */
    private BigDecimal exchangeRate;

    /**
     * Código de usuario de la empresa
     * Size(min = 1, max = 100)
     */
    private String userCode;

    /**
     * Correo para envio de facturas al cliente
     * Email
     */
    private String emailNotification;




    /**
     * Número de factura
     * enviar solo si la empresa manejará su propia numeración de facturas
     */
    private Long invoiceNumber;

    /**
     * Fecha de emisión,
     * enviar solo para facturas por contingencia,
     * tiene que ser mayor a la fecha de inicio de la contingencia
     * Ejem "2021-11-22T14:58:11.262Z"
     */
    private String emissionDate;

    //TODO get DB? CAFC
    /**
     * Código de autorización de facturas por contingencia, enviar solo si el sistema esta en modo contingencia
     * Size(min = 1, max = 50)
     */
    private String cafc;

//    @Size(max = 256)
    private String extraMesssage;

    /**
     * NotNull
     * Size.List(min = 1)
     * Size.List(max = 10000)
     */
    private List<T> details;

    public AbstractHeaderRequest(
            Integer paymentMethodType,
            List<T> details) {
        this.paymentMethodType = paymentMethodType;
        this.details = details;
    }

    public List<String> validateList() {
        return validate().getList();
    }

    public String validateStr() {
        return validate().getErrors();
    }

    private ErrorsManager validate() {
        ErrorsManager manager = new ErrorsManager();

        manager.check("branchNumber", Validations.IntegerNumber.min(branchNumber, 0));

        manager.check("customerCode", Validations.Str.size(customerCode, 1, 100));

        manager.check("paymentMethodType", Validations.IntegerNumber.range(paymentMethodType, 1, 400));

        manager.check("cardNumber", Validations.Str.size(cardNumber, 0, 999999999));

        manager.check("additionalDiscount", Validations.BigDec.min(additionalDiscount, new BigDecimal("0.00")));

        manager.check("currencyIso", Validations.IntegerNumber.range(currencyIso, 1, 400));

        manager.check("exchangeRate", Validations.BigDec.min(exchangeRate, new BigDecimal("0.00")));

        manager.check("userCode", Validations.Str.size(userCode, 1, 100));

        manager.check("emailNotification", Validations.Str.email(emailNotification));

        manager.check("cafc", Validations.Str.size(cafc, 1, 50));

        manager.check("extraMesssage", Validations.Str.maxLength(extraMesssage, 256));

        manager.check("details", Validations.Lists.notNull(details));
        manager.check("details", Validations.Lists.range(details, 1, 10000));

        if(details != null && !details.isEmpty()){
            for (int i=0; i<details.size(); i++) {
                manager.check("details "+i+": ", details.get(i).validateStr());
            }
        }

        return manager;
    }
}
