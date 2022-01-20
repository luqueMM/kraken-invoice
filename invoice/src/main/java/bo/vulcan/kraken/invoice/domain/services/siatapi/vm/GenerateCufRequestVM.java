package bo.vulcan.kraken.invoice.domain.services.siatapi.vm;

import org.joda.time.DateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GenerateCufRequestVM {

    private final Long nit;
    private final DateTime dateTime;
    private final Integer branchCode;
    private final Integer modalityCode;
    private final Integer emissionType;
    private final Integer invoiceType;
    private final Integer sectorDocumentType;
    private final Long invoiceNumber;
    private final Integer pointSaleCode;
}
