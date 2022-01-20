package bo.vulcan.kraken.invoice.data.remote;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

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
import bo.vulcan.kraken.invoice.utils.CommonUtils;
import bo.vulcan.kraken.invoice.utils.TestConstants;
import io.reactivex.Single;

@Singleton
public class AppApiHelper implements ApiHelper {

    private final ApiHeader mApiHeader;
    private final Gson mGson;
    private final Context mContext;

    @Inject
    public AppApiHelper(ApiHeader apiHeader, Context context, Gson gson) {
        mApiHeader = apiHeader;
        mGson = gson;
        mContext = context;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Single<AuthenticationResponse> authentication(AuthenticationRequest.ServerLoginRequest request) {

        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_AUTHENTICATE)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .setContentType("application/json")
                .addApplicationJsonBody(request)
                .build()
                .getObjectSingle(AuthenticationResponse.class);
    }

    @Override
    public Single<AccountResponse> account() {
        //TODO add service
        try {
            Type type = new TypeToken<AccountResponse>() {}.getType();
            AccountResponse account = mGson.fromJson(CommonUtils.loadJSONFromAsset(mContext, TestConstants.KRAKEN_SERVICE_ACCOUNT), type);
            return Single.just(account);
        } catch (IOException e) {
            return Single.error(e);
        }
    }

    @Override
    public Single<SiatConfigurationResponse> getSiatConfiguration() {
        //TODO add service
        try {
            Type type = new TypeToken<SiatConfigurationResponse>() {}.getType();
            SiatConfigurationResponse account = mGson.fromJson(CommonUtils.loadJSONFromAsset(mContext, TestConstants.KRAKEN_SERVICE_SIAT_CONFIGURATION), type);
            return Single.just(account);
        } catch (IOException e) {
            return Single.error(e);
        }
    }

    @Override
    public Single<List<SiatCurrencyType>> getAllCurrencyTypes(Long companyID) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_CURRENCY_TYPES)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addPathParameter("companyID", companyID.toString())
                .build()
                .getObjectListSingle(SiatCurrencyType.class);
    }

    @Override
    public Single<List<SiatDocumentType>> getAllDocumentTypes(Long companyID) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_DOCUMENT_TYPES)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addPathParameter("companyID", companyID.toString())
                .build()
                .getObjectListSingle(SiatDocumentType.class);
    }

    @Override
    public Single<List<SiatPaymentMethodType>> getAllPaymentMethodTypes(Long companyID) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_PAYMENT_METHOD_TYPES)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addPathParameter("companyID", companyID.toString())
                .build()
                .getObjectListSingle(SiatPaymentMethodType.class);
    }

    @Override
    public Single<List<Product>> getAllProducts(Long companyID) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_PRODUCTS)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addQueryParameter("companyId.equals",companyID.toString())
                .build()
                .getObjectListSingle(Product.class);
    }

    @Override
    public Single<List<SiatProduct>> getAllSiatProducts(Long companyID) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SIAT_PRODUCTS)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addPathParameter("companyID", companyID.toString())
                .build()
                .getObjectListSingle(SiatProduct.class);
    }

    @Override
    public Single<List<BranchActivityLegendResponse>> getAllBranchActivityLegends(Long branchID) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_BRANCH_ACTIVITY_LEGENDS)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addPathParameter("branchID", branchID.toString())
                .build()
                .getObjectListSingle(BranchActivityLegendResponse.class);
    }

    @Override
    public Single<List<SiatMeasurementUnit>> getAllSiatMeasurementUnit(Long companyID) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SIAT_MEASUREMENT_UNIT)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addPathParameter("companyID", companyID.toString())
                .build()
                .getObjectListSingle(SiatMeasurementUnit.class);
    }
}
