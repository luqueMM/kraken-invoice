package bo.vulcan.kraken.invoice.data.remote;

import java.util.List;

import bo.vulcan.kraken.invoice.data.model.db.Product;
import bo.vulcan.kraken.invoice.data.model.db.SiatCurrencyType;
import bo.vulcan.kraken.invoice.data.model.db.SiatDocumentType;
import bo.vulcan.kraken.invoice.data.model.db.SiatMeasurementUnit;
import bo.vulcan.kraken.invoice.data.model.db.SiatPaymentMethodType;
import bo.vulcan.kraken.invoice.data.model.db.SiatProduct;
import bo.vulcan.kraken.invoice.data.model.request.AuthenticationRequest;
import bo.vulcan.kraken.invoice.data.model.response.AccountResponse;
import bo.vulcan.kraken.invoice.data.model.response.AuthenticationResponse;
import bo.vulcan.kraken.invoice.data.model.response.BranchActivityLegendResponse;
import bo.vulcan.kraken.invoice.data.model.response.SiatConfigurationResponse;
import io.reactivex.Single;

public interface ApiHelper {

    ApiHeader getApiHeader();

    Single<AuthenticationResponse> authentication(AuthenticationRequest.ServerLoginRequest request);

    Single<AccountResponse> account();

    Single<SiatConfigurationResponse> getSiatConfiguration();

    Single<List<SiatCurrencyType>> getAllCurrencyTypes(Long companyID);

    Single<List<SiatDocumentType>> getAllDocumentTypes(Long companyID);

    Single<List<SiatPaymentMethodType>> getAllPaymentMethodTypes(Long companyID);

    Single<List<Product>> getAllProducts(Long companyID);

    Single<List<SiatProduct>> getAllSiatProducts(Long companyID);

    Single<List<BranchActivityLegendResponse>> getAllBranchActivityLegends(Long branchID);

    Single<List<SiatMeasurementUnit>> getAllSiatMeasurementUnit(Long companyID);
}
