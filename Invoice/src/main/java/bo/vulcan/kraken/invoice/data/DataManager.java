package bo.vulcan.kraken.invoice.data;

import java.util.List;

import bo.vulcan.kraken.invoice.data.local.db.DbHelper;
import bo.vulcan.kraken.invoice.data.local.prefs.PreferencesHelper;
import bo.vulcan.kraken.invoice.data.model.db.Product;
import bo.vulcan.kraken.invoice.data.model.db.SiatCurrencyType;
import bo.vulcan.kraken.invoice.data.model.db.SiatDocumentType;
import bo.vulcan.kraken.invoice.data.model.db.SiatMeasurementUnit;
import bo.vulcan.kraken.invoice.data.model.db.SiatPaymentMethodType;
import bo.vulcan.kraken.invoice.data.model.db.SiatProduct;
import bo.vulcan.kraken.invoice.data.model.db.SystemStatus;
import bo.vulcan.kraken.invoice.data.model.response.AccountResponse;
import bo.vulcan.kraken.invoice.data.model.response.AuthenticationResponse;
import bo.vulcan.kraken.invoice.data.model.response.BranchActivityLegendResponse;
import bo.vulcan.kraken.invoice.data.model.response.SiatConfigurationResponse;
import bo.vulcan.kraken.invoice.data.remote.ApiHelper;
import io.reactivex.Single;

public interface DataManager extends PreferencesHelper, ApiHelper, DbHelper {

    void updateApiHeader(String accessToken);

    Single<AccountResponse> syncUserInfo();

    Single<SiatConfigurationResponse> syncSiatConfiguration();

    Single<SystemStatus> syncSystemStatus();

    Single<Boolean> syncSiatCurrencyTypeList(List<SiatCurrencyType> siatCurrencyTypeList);

    Single<Boolean> syncSiatDocumentTypeList(List<SiatDocumentType> siatDocumentTypeList);

    Single<Boolean> syncSiatPaymentMethodTypeList(List<SiatPaymentMethodType> siatPaymentMethodTypeList);

    Single<Boolean> syncProductList(List<Product> productList);

    Single<Boolean> syncSiatProductList(List<SiatProduct> siatProductList);

    Single<Boolean> syncBranchActivityLegendList(List<BranchActivityLegendResponse> branchActivityLegendResponses);

    Single<Boolean> syncSiatMeasurementUnitList(List<SiatMeasurementUnit> siatMeasurementUnitList);
}
