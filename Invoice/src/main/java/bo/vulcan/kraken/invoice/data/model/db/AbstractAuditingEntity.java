package bo.vulcan.kraken.invoice.data.model.db;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import bo.vulcan.kraken.invoice.utils.DateTimeHelper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class AbstractAuditingEntity {

    @Expose
    @SerializedName("createdBy")
    @ColumnInfo(name = "created_by")
    private String createdBy;

    @Expose
    @SerializedName("createdDate")
    @ColumnInfo(name = "created_date")
    private DateTime createdDate = DateTimeHelper.getCurrentLocalTime();

    @Expose
    @SerializedName("lastModifiedBy")
    @ColumnInfo(name = "last_modified_by")
    private String lastModifiedBy;

    @Expose
    @SerializedName("lastModifiedDate")
    @ColumnInfo(name = "last_modified_date")
    private DateTime lastModifiedDate = DateTimeHelper.getCurrentLocalTime();
}
