package bo.vulcan.kraken.invoice.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import bo.vulcan.kraken.invoice.data.model.db.BranchActivityLegend;
import bo.vulcan.kraken.invoice.data.model.db.Cufd;
import bo.vulcan.kraken.invoice.data.model.db.DigitalSign;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SiatConfigurationResponse {

    @Expose
    @SerializedName("digital_sign")
    private DigitalSign digitalSign;

    @Expose
    @SerializedName("cufd")
    private Cufd cufd;

}
