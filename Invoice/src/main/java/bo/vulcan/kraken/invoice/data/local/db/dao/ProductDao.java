package bo.vulcan.kraken.invoice.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import bo.vulcan.kraken.invoice.data.model.db.Product;
import bo.vulcan.kraken.invoice.data.model.enumeration.InvoiceType;
import io.reactivex.Completable;
import io.reactivex.Single;
import lombok.NonNull;

@Dao
public interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Product> products);

    @Query("DELETE FROM product")
    Completable deleteAll();

    @Query("SELECT * FROM product")
    Single<List<Product>> loadAll();

    @Query("SELECT * FROM product p WHERE p.company_id = :companyId AND p.product_code LIKE :productCode AND p.product_origin LIKE :productOrigin LIMIT 1")
    Product findFirstByCompanyIdAndProductCodeAndProductOrigin(Long companyId, String productCode, InvoiceType productOrigin);
}
