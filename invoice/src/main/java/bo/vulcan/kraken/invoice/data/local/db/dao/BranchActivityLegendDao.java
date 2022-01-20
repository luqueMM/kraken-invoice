package bo.vulcan.kraken.invoice.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import bo.vulcan.kraken.invoice.data.model.db.BranchActivityLegend;
import io.reactivex.Completable;
import io.reactivex.Single;
import lombok.NonNull;

@Dao
public interface BranchActivityLegendDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<BranchActivityLegend> branchActivityLegends);

    @Query("DELETE FROM branch_activity_legend")
    Completable deleteAll();

    @Query("SELECT * FROM branch_activity_legend")
    Single<List<BranchActivityLegend>> loadAll();

    @Query("SELECT * FROM branch_activity_legend b WHERE b.branch_id LIKE :branchId AND b.activity_id LIKE :activityId LIMIT 1")
    BranchActivityLegend findByBranchIdAndActivityId(@NonNull Long branchId, @NonNull Long activityId);
}
