package bo.vulcan.kraken.invoice.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity(tableName = "siat_payment_method_type")
@Getter @Setter @ToString
public class SiatPaymentMethodType {

    @Expose
    @PrimaryKey
    public Long id;

    @NonNull
    @Expose
    @SerializedName("classifierCode")
    @ColumnInfo(name = "classifier_code")
    public String classifierCode;

    @Expose
    @SerializedName("description")
    @ColumnInfo(name = "description")
    public String description;

    @NonNull
    @Expose
    @SerializedName("branchId")
    @ColumnInfo(name = "branch_id")
    public Long branchId;

    @Expose
    @SerializedName("salePointId")
    @ColumnInfo(name = "sale_point_id")
    public Long salePointId;

    @NonNull
    @Expose
    @SerializedName("companyId")
    @ColumnInfo(name = "company_id")
    public Long companyId;
}
