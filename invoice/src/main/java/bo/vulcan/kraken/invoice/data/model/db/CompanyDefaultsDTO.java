package bo.vulcan.kraken.invoice.data.model.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CompanyDefaultsDTO {

    @Expose
    @SerializedName("documentTypeCode")
    private String documentTypeCode;

    @Expose
    @SerializedName("paymentMethodTypeCode")
    private String paymentMethodTypeCode;

    @Expose
    @SerializedName("currencyCode")
    private String currencyCode;

    @Expose
    @SerializedName("exchangeRate")
    private BigDecimal exchangeRate;

    @Expose
    @SerializedName("siatProductCode")
    private Long siatProductCode;

    @Expose
    @SerializedName("siatMeasurementUnitCode")
    private Integer siatMeasurementUnitCode;

    @Expose
    @SerializedName("productCode")
    private String productCode;

    @Expose
    @SerializedName("cancelReasonCode")
    private String cancelReasonCode;

    @Expose
    @SerializedName("branchId")
    private Long branchId;

    @Expose
    @SerializedName("economicActivityId")
    private Long economicActivityId;

}
