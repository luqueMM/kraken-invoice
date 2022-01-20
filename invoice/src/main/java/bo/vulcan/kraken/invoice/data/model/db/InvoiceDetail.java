package bo.vulcan.kraken.invoice.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joda.time.LocalDate;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity(tableName = "invoice_detail")
@Getter @Setter @ToString
public class InvoiceDetail extends AbstractAuditingEntity {

    @androidx.annotation.NonNull
    @Expose
    @PrimaryKey
    @SerializedName("localId")
    @ColumnInfo(name = "local_id")
    private String localId;

    @Expose
    @SerializedName("id")
    @ColumnInfo(name = "id")
    private Long id;

    /**
     * Set request.detail.quantity
     */
//    @DecimalMin(value = "0.01")
//    @DecimalMax(value = "999999999"), precision = 20, scale = 5
    @NonNull
    @Expose
    @SerializedName("quantity")
    @ColumnInfo(name = "quantity")
    private BigDecimal quantity;

    /**
     * Set request.detail.concept
     */
//    @Size(min = 1, max = 500), length = 500
    @NonNull
    @Expose
    @SerializedName("concept")
    @ColumnInfo(name = "concept")
    private String concept;

//    @Size(min = 1, max = 10), length = 10
    @NonNull
    @Expose
    @SerializedName("siat_activity_code")
    @ColumnInfo(name = "siat_activity_code")
    private String siatActivityCode;

//    @Min(value = 1)
//    @Max(value = 9999999999L)
    @NonNull
    @Expose
    @SerializedName("siat_product_code")
    @ColumnInfo(name = "siat_product_code")
    private Long siatProductCode;

    /**
     * Set request.detail.productCode
     */
//    @Size(min = 1, max = 100)
    @NonNull
    @Expose
    @SerializedName("product_code")
    @ColumnInfo(name = "product_code")
    private String productCode;

//    @Min(value = 1)
//    @Max(value = 69)
    @NonNull
    @Expose
    @SerializedName("siat_measurement_unit_code")
    @ColumnInfo(name = "siat_measurement_unit_code")
    private Integer siatMeasurementUnitCode;

    /**
     * Set request.detail.unitPrice
     */
//    @DecimalMin(value = "-999999999")
//    @DecimalMax(value = "999999999"), precision = 20, scale = 5
    @NonNull
    @Expose
    @SerializedName("unit_price")
    @ColumnInfo(name = "unit_price")
    private BigDecimal unitPrice;

    /**
     * Set request.detail.discountAmmount
     * Si es nulo sera = BigDecimal.ZERO
     */
//    @DecimalMin(value = "-999999999")
//    @DecimalMax(value = "999999999"), precision = 20, scale = 5
    @Expose
    @SerializedName("discount_amount")
    @ColumnInfo(name = "discount_amount")
    private BigDecimal discountAmount;

    /**
     * Set request.detail.subtotal
     */
//    @DecimalMin(value = "-999999999")
//    @DecimalMax(value = "999999999"), precision = 20, scale = 5
    @NonNull
    @Expose
    @SerializedName("subtotal")
    @ColumnInfo(name = "subtotal")
    private BigDecimal subtotal;

    /**
     * Set request.detail.sequence
     */
//    @Min(value = 0)
//    @Max(value = 999999999)
    @Expose
    @SerializedName("jhi_sequence")
    @ColumnInfo(name = "jhi_sequence")
    private Integer sequence;

    /**
     * Set request.detail.seriesNumber
     */
//    @Size(max = 1500)
    @Expose
    @SerializedName("series_number")
    @ColumnInfo(name = "series_number")
    private String seriesNumber;

    /**
     * Set request.detail.imeiNumber
     */
//    @Size(max = 1500)
    @Expose
    @SerializedName("imei_number")
    @ColumnInfo(name = "imei_number")
    private String imeiNumber;

    @NonNull
    @Expose
    @SerializedName("invoice_date")
    @ColumnInfo(name = "invoice_date")
    private LocalDate invoiceDate;

    // factura electronica alcanzada ice
//    @Min(value = 1)
//    @Max(value = 2)
    @Expose
    @SerializedName("mark_ice")
    @ColumnInfo(name = "mark_ice")
    private Integer markIce;

//    @DecimalMin(value = "0")
//    @DecimalMax(value = "999999999"), precision = 20, scale = 5
    @Expose
    @SerializedName("aliquot_iva")
    @ColumnInfo(name = "aliquot_iva")
    private BigDecimal aliquotIva; //se debe calcular

//    @DecimalMin(value = "0")
//    @DecimalMax(value = "999999999"), precision = 20, scale = 5
    @Expose
    @SerializedName("price_net_sale_ice")
    @ColumnInfo(name = "price_net_sale_ice")
    private BigDecimal priceNetSaleIce; // se debe calcular

//    @DecimalMin(value = "0")
//    @DecimalMax(value = "999999999"), precision = 20, scale = 5
    @Expose
    @SerializedName("aliquot_specify")
    @ColumnInfo(name = "aliquot_specify")
    private BigDecimal aliquotSpecify;

//    @DecimalMin(value = "0")
//    @DecimalMax(value = "999999999"), precision = 20, scale = 5
    @Expose
    @SerializedName("aliquot_percentage")
    @ColumnInfo(name = "aliquot_percentage")
    private BigDecimal aliquotPercentage;

//    @DecimalMin(value = "0")
//    @DecimalMax(value = "999999999"), precision = 20, scale = 5
    @Expose
    @SerializedName("amount_ice_specific")
    @ColumnInfo(name = "amount_ice_specific")
    private BigDecimal amountIceSpecific; // se debe calcular

//    @DecimalMin(value = "0")
//    @DecimalMax(value = "999999999"), precision = 20, scale = 5
    @Expose
    @SerializedName("amount_ice_percentage")
    @ColumnInfo(name = "amount_ice_percentage")
    private BigDecimal amountIcePercentage; // se debe calcular

//    @DecimalMin(value = "0")
//    @DecimalMax(value = "999999999"), precision = 20, scale = 5
    @Expose
    @SerializedName("quantity_ice")
    @ColumnInfo(name = "quantity_ice")
    private BigDecimal quantityIce;

    // factura exportacion

//    @Size(min = 1, max = 50) length = 256
    @Expose
    @SerializedName("nandina_code")
    @ColumnInfo(name = "nandina_code")
    private String nandinaCode;

    @Expose
    @SerializedName("invoiceId")
    @ColumnInfo(name = "invoice_id")
    private Long invoiceId;

    @Expose
    @SerializedName("invoiceLocalId")
    @ColumnInfo(name = "invoice_local_id")
    @NonNull
    private String invoiceLocalId;

//    @Transient
    @Ignore
    private String measurementUnit;
}
