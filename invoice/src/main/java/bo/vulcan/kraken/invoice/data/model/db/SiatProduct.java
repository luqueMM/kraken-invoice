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
@Entity(tableName = "siat_product")
public class SiatProduct {

    @Expose
    @SerializedName("id")
    @PrimaryKey
    private Long id;

    @Expose
    @SerializedName("activityCode")
    @ColumnInfo(name = "activity_code")
    private String activityCode;

    @Expose
    @SerializedName("productCode")
    @ColumnInfo(name = "product_code")
    private Long productCode;

    @Expose
    @SerializedName("productDescription")
    @ColumnInfo(name = "product_description")
    private String productDescription;

    @Expose
    @SerializedName("nandinaQuantity")
    @ColumnInfo(name = "nandina_quantity")
    private Integer nandinaQuantity;

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
