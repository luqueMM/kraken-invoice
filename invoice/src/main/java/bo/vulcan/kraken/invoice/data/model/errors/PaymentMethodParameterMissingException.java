package bo.vulcan.kraken.invoice.data.model.errors;

import java.util.Collections;

public class PaymentMethodParameterMissingException extends BaseException {
    public PaymentMethodParameterMissingException(String message) {

        super (Collections.singletonList(
                new BaseException.CodeMessage(
                        StatusType.NOT_FOUND.getStatusCode(),
                        "payment_method_parameter_missin",
                        "El parametro para el metodo de pago seleccionado ha producido un error: " + message + "."
        )));
    }
}
