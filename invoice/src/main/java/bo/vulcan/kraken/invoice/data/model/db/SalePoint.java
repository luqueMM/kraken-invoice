package bo.vulcan.kraken.invoice.data.model.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SalePoint {

    @Expose
    @SerializedName("id")
    private Long id;

    @NonNull
    @Expose
    @SerializedName("code")
    private Integer code;

    @NonNull
    @Expose
    @SerializedName("name")
    private String name;

//    @Size(max = 256)
    @NonNull
    @Expose
    @SerializedName("description")
    private String description;

//    @Size(max = 256)
    @NonNull
    @Expose
    @SerializedName("salePointType")
    private String salePointType;

    @NonNull
    @Expose
    @SerializedName("enabled")
    private Boolean enabled;

    @NonNull
    @Expose
    @SerializedName("company")
    private Company company;

    @NonNull
    @Expose
    @SerializedName("branch")
    private Branch branch;
}
