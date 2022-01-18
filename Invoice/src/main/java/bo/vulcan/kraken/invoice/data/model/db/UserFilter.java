package bo.vulcan.kraken.invoice.data.model.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class UserFilter {

    @Expose
    @SerializedName("id")
    private Long id;

    @Expose
    @SerializedName("login")
    private String login;

    @Expose
    @SerializedName("firstName")
    private String firstName;

    @Expose
    @SerializedName("lastName")
    private String lastName;

    @Expose
    @SerializedName("email")
    private String email;

    @Expose
    @SerializedName("seeFullInvoice")
    private Boolean seeFullInvoice = true;

    @Expose
    @SerializedName("roleId")
    private Long roleId;
}
