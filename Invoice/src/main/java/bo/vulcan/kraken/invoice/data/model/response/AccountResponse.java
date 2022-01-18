package bo.vulcan.kraken.invoice.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import bo.vulcan.kraken.invoice.data.model.db.Branch;
import bo.vulcan.kraken.invoice.data.model.db.Company;
import bo.vulcan.kraken.invoice.data.model.db.CompanyDefaultsDTO;
import bo.vulcan.kraken.invoice.data.model.db.SalePoint;
import bo.vulcan.kraken.invoice.data.model.db.UserFilter;
import lombok.Data;

@Data
public class AccountResponse {

    @Expose
    @SerializedName("user")
    private UserFilter userFilter;

    @Expose
    @SerializedName("company")
    private Company company;

    @Expose
    @SerializedName("branch")
    private Branch branch;

    @Expose
    @SerializedName("salePoint")
    private SalePoint salePoint;

    @Expose
    @SerializedName("company_default_field")
    private CompanyDefaultsDTO companyDefaults;
}
