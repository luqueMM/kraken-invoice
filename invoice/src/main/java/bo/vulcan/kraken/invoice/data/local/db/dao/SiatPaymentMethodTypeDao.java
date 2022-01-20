package bo.vulcan.kraken.invoice.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import bo.vulcan.kraken.invoice.data.model.db.SiatPaymentMethodType;
import io.reactivex.Completable;
import io.reactivex.Single;
import lombok.NonNull;

@Dao
public interface SiatPaymentMethodTypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SiatPaymentMethodType> siatPaymentMethodTypes);

    @Query("DELETE FROM siat_payment_method_type")
    Completable deleteAll();

    @Query("SELECT * FROM siat_payment_method_type")
    Single<List<SiatPaymentMethodType>> loadAll();

    @Query("SELECT * FROM siat_payment_method_type s WHERE s.classifier_code LIKE :classifierCode LIMIT 1")
    SiatPaymentMethodType findFirstByClassifierCode(@NonNull String classifierCode);
}
