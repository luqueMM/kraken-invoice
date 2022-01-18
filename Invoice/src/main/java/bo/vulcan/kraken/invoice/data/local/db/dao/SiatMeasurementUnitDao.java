package bo.vulcan.kraken.invoice.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import bo.vulcan.kraken.invoice.data.model.db.SiatMeasurementUnit;
import io.reactivex.Completable;
import io.reactivex.Single;
import lombok.NonNull;

@Dao
public interface SiatMeasurementUnitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SiatMeasurementUnit> invoices);

    @Query("DELETE FROM siat_measurement_unit")
    Completable deleteAll();

    @Query("SELECT * FROM siat_measurement_unit")
    Single<List<SiatMeasurementUnit>> loadAll();

    @Query("SELECT * FROM siat_measurement_unit s WHERE s.classifier_code LIKE :classifierCode AND s.company_id LIKE :companyId")
    SiatMeasurementUnit findFirstByClassifierCodeAndCompanyId(@NonNull String classifierCode, @NonNull Long companyId);
}
