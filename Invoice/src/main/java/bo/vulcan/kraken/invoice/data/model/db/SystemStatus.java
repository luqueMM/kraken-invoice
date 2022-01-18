package bo.vulcan.kraken.invoice.data.model.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import bo.vulcan.kraken.invoice.data.model.enumeration.NetworkState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SystemStatus {

    private String id;

    @Expose
    @SerializedName("network_state")
    private NetworkState networkState;

    @Expose
    @SerializedName("company_id")
    private Long companyId;
}
