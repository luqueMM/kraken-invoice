package bo.vulcan.kraken.invoice.data.model.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import bo.vulcan.kraken.invoice.data.model.db.Invoice;
import bo.vulcan.kraken.invoice.data.model.db.InvoiceDetail;
import bo.vulcan.kraken.invoice.data.model.util.RandomUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveNegativeDetailsResult {
    BigDecimal totalNegative;
    List<InvoiceDetail> list;
    boolean hasError;

    public RemoveNegativeDetailsResult() {
        this.totalNegative = BigDecimal.ZERO;
        this.hasError = false;
    }

    public Set<InvoiceDetail> setInvoiceIdToItems(Invoice invoice) {
        Set<InvoiceDetail> _listMap = new HashSet<>();

        for (InvoiceDetail item : list) {
            item.setInvoiceLocalId(invoice.getLocalId());
            item.setLocalId(RandomUtil.generateUuid());
            _listMap.add(item);
        }

        return _listMap;
    }
}
