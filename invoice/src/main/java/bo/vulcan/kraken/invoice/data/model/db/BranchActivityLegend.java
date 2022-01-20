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
@Entity(tableName = "branch_activity_legend")
public class BranchActivityLegend {
    @Expose
    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "invoice_legend_id")
    private Long id;

    @Expose
    @SerializedName("branchId")
    @ColumnInfo(name = "branch_id")
    private Long branchId;

    @Expose
    @SerializedName("salePointId")
    @ColumnInfo(name = "sale_point_id")
    private Long salePointId;

    @Expose
    @SerializedName("description")
    @ColumnInfo(name = "legend")
    private String legend;

    @Expose
    @SerializedName("activityId")
    @ColumnInfo(name = "activity_id")
    private Long activityId;

    @Expose
    @SerializedName("economicActivity")
    @ColumnInfo(name = "economic_activity")
    private String economicActivity;

}
