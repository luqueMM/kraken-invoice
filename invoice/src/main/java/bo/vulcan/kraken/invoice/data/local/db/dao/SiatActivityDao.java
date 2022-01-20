package bo.vulcan.kraken.invoice.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import bo.vulcan.kraken.invoice.data.model.db.SiatActivity;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface SiatActivityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SiatActivity> siatActivities);

    @Query("DELETE FROM siat_activity")
    Completable deleteAll();

    @Query("SELECT * FROM siat_activity")
    Single<List<SiatActivity>> loadAll();
}
