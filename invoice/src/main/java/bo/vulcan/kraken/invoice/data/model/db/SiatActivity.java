package bo.vulcan.kraken.invoice.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity(tableName = "siat_activity")
public class SiatActivity {

    @Expose
    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    private Long id;

    @Expose
    @SerializedName("caebCode")
    @ColumnInfo(name = "caeb_code")
    private String caebCode;

    @Expose
    @SerializedName("description")
    @ColumnInfo(name = "description")
    private String description;

    @Expose
    @SerializedName("typeActivity")
    @ColumnInfo(name = "type_activity")
    private String typeActivity;

    @Expose
    @SerializedName("branchId")
    @ColumnInfo(name = "branch_id")
    private Long branchId;

    @Expose
    @SerializedName("salePointId")
    @ColumnInfo(name = "sale_point_id")
    private Long salePointId;

    @Expose
    @SerializedName("companyId")
    @ColumnInfo(name = "company_id")
    private Long companyId;

}
