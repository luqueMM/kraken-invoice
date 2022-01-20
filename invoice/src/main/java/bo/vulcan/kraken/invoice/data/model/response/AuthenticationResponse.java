package bo.vulcan.kraken.invoice.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class AuthenticationResponse {

    @Expose
    @SerializedName("id_token")
    private String idToken;
}