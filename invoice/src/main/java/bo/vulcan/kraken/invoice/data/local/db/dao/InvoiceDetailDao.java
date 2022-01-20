package bo.vulcan.kraken.invoice.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import bo.vulcan.kraken.invoice.data.model.db.InvoiceDetail;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface InvoiceDetailDao {

    @Insert
    void insert(InvoiceDetail invoiceDetail);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<InvoiceDetail> invoiceDetails);

    @Query("DELETE FROM invoice_detail")
    Completable deleteAll();

    @Query("SELECT * FROM invoice_detail")
    Single<List<InvoiceDetail>> loadAll();
}
