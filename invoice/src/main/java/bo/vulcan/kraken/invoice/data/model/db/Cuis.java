package bo.vulcan.kraken.invoice.data.model.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Cuis {

    @Expose
    @SerializedName("id")
    private Long id;

    @Expose
    @SerializedName("code")
    private String code;

    @Expose
    @SerializedName("validityDate")
    private DateTime validityDate;

    @Expose
    @SerializedName("enabled")
    private Boolean enabled;

    @Expose
    @SerializedName("company")
    private Company company;

    @Expose
    @SerializedName("branch")
    private Branch branch;

    @Expose
    @SerializedName("salePoint")
    private SalePoint salePoint;
}
