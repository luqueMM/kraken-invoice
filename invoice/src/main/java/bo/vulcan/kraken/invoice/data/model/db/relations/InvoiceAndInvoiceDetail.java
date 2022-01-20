package bo.vulcan.kraken.invoice.data.model.db.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import bo.vulcan.kraken.invoice.data.model.db.Invoice;
import bo.vulcan.kraken.invoice.data.model.db.InvoiceDetail;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InvoiceAndInvoiceDetail {

    @Embedded
    private Invoice invoice;

    @Relation(
            parentColumn = "local_id",
            entityColumn = "invoice_local_id"
    )
    private List<InvoiceDetail> invoiceDetails;
}
