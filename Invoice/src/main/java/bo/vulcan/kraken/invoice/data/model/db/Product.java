package bo.vulcan.kraken.invoice.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import bo.vulcan.kraken.invoice.data.model.enumeration.InvoiceType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(tableName = "product")
public class Product {

    @Expose
    @SerializedName("id")
    @PrimaryKey
    private Integer id;

    @Expose
    @SerializedName("productCode")
    @ColumnInfo(name = "product_code")
    private String productCode;

    @Expose
    @SerializedName("siatProductCode")
    @ColumnInfo(name = "siat_product_code")
    private Long siatProductCode;

    @Expose
    @SerializedName("siatMeasurementUnitCode")
    @ColumnInfo(name = "siat_measurement_unit_code")
    private Long siatMeasurementUnitCode;

    @Expose
    @SerializedName("description")
    @ColumnInfo(name = "description")
    private String description;

    @Expose
    @SerializedName("activityCode")
    @ColumnInfo(name = "activity_code")
    private String activityCode;

    @Expose
    @SerializedName("productOrigin")
    @ColumnInfo(name = "product_origin")
    private InvoiceType productOrigin;

    @Expose
    @SerializedName("nandina")
    @ColumnInfo(name = "nandina")
    private String nandina;

    @Expose
    @SerializedName("companyId")
    @ColumnInfo(name = "company_id")
    private Long companyId;

}
