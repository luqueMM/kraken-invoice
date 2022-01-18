package bo.vulcan.kraken.invoice.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(tableName = "siat_measurement_unit")
@Data
@NoArgsConstructor
public class SiatMeasurementUnit {

    @Expose
    @PrimaryKey
    private Long id;

    @Expose
    @SerializedName("classifierCode")
    @ColumnInfo(name = "classifier_code")
    private String classifierCode;

    @Expose
    @SerializedName("description")
    @ColumnInfo(name = "description")
    private String description;

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
