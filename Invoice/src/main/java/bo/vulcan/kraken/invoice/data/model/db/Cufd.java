package bo.vulcan.kraken.invoice.data.model.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Cufd {

    @Expose
    @SerializedName("id")
    private Long id;

    @Expose
    @SerializedName("code")
    private String code;

    @Expose
    @SerializedName("controlCode")
    private String controlCode;

    @Expose
    @SerializedName("sequenceName")
    private String sequenceName;

    @Expose
    @SerializedName("address")
    private String address;

    @Expose
    @SerializedName("validityDate")
    private DateTime validityDate;

    @Expose
    @SerializedName("enabled")
    private Boolean enabled;

    @Expose
    @SerializedName("cuis")
    private Cuis cuis;
}
