package bo.vulcan.kraken.invoice.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import bo.vulcan.kraken.invoice.data.model.db.SiatDocumentType;
import io.reactivex.Completable;
import io.reactivex.Single;
import lombok.NonNull;

@Dao
public interface SiatDocumentTypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SiatDocumentType> siatDocumentTypes);

    @Query("DELETE FROM siat_document_type")
    Completable deleteAll();

    @Query("SELECT * FROM siat_document_type")
    Single<List<SiatDocumentType>> loadAll();

    @Query("SELECT * FROM siat_document_type s WHERE s.classifier_code LIKE :classifierCode LIMIT 1")
    Single<SiatDocumentType> findFirstByClassifierCode(@NonNull String classifierCode);
}