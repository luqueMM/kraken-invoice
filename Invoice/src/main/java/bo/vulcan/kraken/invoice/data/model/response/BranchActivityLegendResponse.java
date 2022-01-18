package bo.vulcan.kraken.invoice.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import bo.vulcan.kraken.invoice.data.model.db.BranchActivityLegend;
import bo.vulcan.kraken.invoice.data.model.db.SiatActivity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BranchActivityLegendResponse {

    @Expose
    @SerializedName("siatInvoiceLeyend")
    private BranchActivityLegend branchActivityLegend;

    @Expose
    @SerializedName("siatActivity")
    private SiatActivity siatActivity;

    @Expose
    @SerializedName("branchId")
    private Long branchId;
}
