package bo.vulcan.kraken.invoice.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.NonNull;

@Entity(tableName = "siat_currency_type")
public class SiatCurrencyType {

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
