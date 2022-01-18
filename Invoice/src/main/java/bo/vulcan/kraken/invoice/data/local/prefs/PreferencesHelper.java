package bo.vulcan.kraken.invoice.data.local.prefs;

import bo.vulcan.kraken.invoice.data.model.db.Branch;
import bo.vulcan.kraken.invoice.data.model.db.BranchActivityLegend;
import bo.vulcan.kraken.invoice.data.model.db.Company;
import bo.vulcan.kraken.invoice.data.model.db.CompanyDefaultsDTO;
import bo.vulcan.kraken.invoice.data.model.db.Cufd;
import bo.vulcan.kraken.invoice.data.model.db.Cuis;
import bo.vulcan.kraken.invoice.data.model.db.DigitalSign;
import bo.vulcan.kraken.invoice.data.model.db.SalePoint;
import bo.vulcan.kraken.invoice.data.model.db.SystemStatus;
import bo.vulcan.kraken.invoice.data.model.db.UserFilter;

public interface PreferencesHelper {

    String getAccessToken();

    void setAccessToken(String accessToken);

    UserFilter getUser();

    void setUser(UserFilter value);

    Company getCompany();

    void setCompany(Company value);

    Branch getBranch();

    void setBranch(Branch value);

    SalePoint getSalePoint();

    void setSalePoint(SalePoint value);

    CompanyDefaultsDTO getCompanyDefaults();

    void setCompanyDefaults(CompanyDefaultsDTO value);


    DigitalSign getDigitalSign();

    void setDigitalSign(DigitalSign value);

    Cufd getCufd();

    void setCufd(Cufd value);

    Cuis getCuis();

    void setCuis(Cuis value);


    SystemStatus getSystemStatus();

    void setSystemStatus(SystemStatus value);

}
