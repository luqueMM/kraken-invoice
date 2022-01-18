package bo.vulcan.kraken.invoice.data.model.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joda.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DigitalSign {

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("public_key_name")
    private String publicKeyName;

    @Expose
    @SerializedName("private_key_name")
    private String privateKeyName;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("private_content")
    private String privateContent;

    @Expose
    @SerializedName("public_content")
    private String publicContent;

    @Expose
    @SerializedName("expiration_date")
    private LocalDate expirationDate;

    @Expose
    @SerializedName("enabled")
    private Boolean enabled;

    @Expose
    @SerializedName("revoked")
    private Boolean revoked;

    @Expose
    @SerializedName("company_id")
    private Integer companyId;

}
