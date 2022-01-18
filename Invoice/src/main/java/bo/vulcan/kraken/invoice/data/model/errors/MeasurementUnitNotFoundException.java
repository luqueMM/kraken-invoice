package bo.vulcan.kraken.invoice.data.model.errors;

import java.util.Collections;

public class MeasurementUnitNotFoundException extends BaseException {

    public MeasurementUnitNotFoundException() {
        super (Collections.singletonList(
                new BaseException.CodeMessage(
                        StatusType.NOT_FOUND.getStatusCode(),
                        "siat_measurement_unit_not_found",
                        "SiatMeasurementUnit not found"
                )));
    }
}
