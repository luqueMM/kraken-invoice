package bo.vulcan.kraken.invoice.data.model.errors;

import java.util.Collections;

public class BranchActivityLegendNotFoundException extends BaseException {

    public BranchActivityLegendNotFoundException() {
        super(Collections.singletonList(
                new BaseException.CodeMessage(
                        StatusType.NOT_FOUND.getStatusCode(),
                        "branch_activity_legend_not_found",
                        "La sucursal no tiene configurada esta actividad economica")));
    }
}
