package bo.vulcan.kraken.invoice.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import bo.vulcan.kraken.invoice.data.model.db.SiatProduct;
import io.reactivex.Completable;
import io.reactivex.Single;
import lombok.NonNull;

@Dao
public interface SiatProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SiatProduct> siatProducts);

    @Query("DELETE FROM siat_product")
    Completable deleteAll();

    @Query("SELECT * FROM siat_product")
    Single<List<SiatProduct>> loadAll();

    @Query("SELECT * FROM siat_product p WHERE p.product_code LIKE :productCode AND p.company_id = :companyId LIMIT 1")
    SiatProduct findFirstByProductCodeAndCompanyId(Long productCode, @NonNull Long companyId);
}
