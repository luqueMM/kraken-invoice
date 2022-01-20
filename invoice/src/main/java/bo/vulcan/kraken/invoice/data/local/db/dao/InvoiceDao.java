package bo.vulcan.kraken.invoice.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import bo.vulcan.kraken.invoice.data.model.db.Invoice;
import bo.vulcan.kraken.invoice.data.model.db.relations.InvoiceAndInvoiceDetail;
import bo.vulcan.kraken.invoice.data.model.enumeration.InvoiceType;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface InvoiceDao {
    @Insert
    void insert(Invoice invoice);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Invoice> invoices);

    @Query("DELETE FROM invoice")
    Completable deleteAll();

    @Query("SELECT * FROM invoice")
    Single<List<Invoice>> loadAll();

    @Query("SELECT * FROM invoice WHERE id LIKE :id LIMIT 1")
    Single<Invoice> loadById(int id);

    @Query("SELECT * FROM invoice i order by i.created_date DESC LIMIT 1")
    Invoice findFirstInvoiceOrderByCreationDateDesc();

    @Query("SELECT * FROM invoice i WHERE i.external_id LIKE :externalId AND i.invoice_type LIKE :invoiceType AND i.company_id LIKE :companyId LIMIT 1")
    Invoice findOneByExternalIdAndInvoiceTypeAndCompany_Id(String externalId, InvoiceType invoiceType, Long companyId);

    @Transaction
    @Query("SELECT * FROM invoice WHERE id LIKE :id LIMIT 1")
    Single<InvoiceAndInvoiceDetail> getInvoiceWithInvoiceDetailById(Long id);

    @Transaction
    @Query("SELECT * FROM invoice WHERE local_id LIKE :localId LIMIT 1")
    Single<InvoiceAndInvoiceDetail> getInvoiceWithInvoiceDetailByLocalId(String localId);

    @Transaction
    @Query("SELECT * FROM invoice")
    Single<List<InvoiceAndInvoiceDetail>> getInvoiceWithInvoiceDetailList();
}
