package bo.vulcan.kraken.invoice;


import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

import java.util.List;

import javax.inject.Inject;

import bo.vulcan.kraken.invoice.data.model.db.BranchActivityLegend;
import bo.vulcan.kraken.invoice.data.model.db.Invoice;
import bo.vulcan.kraken.invoice.data.model.db.InvoicePdf;
import bo.vulcan.kraken.invoice.data.model.db.Product;
import bo.vulcan.kraken.invoice.data.model.db.SiatCurrencyType;
import bo.vulcan.kraken.invoice.data.model.db.SiatDocumentType;
import bo.vulcan.kraken.invoice.data.model.db.SiatMeasurementUnit;
import bo.vulcan.kraken.invoice.data.model.db.SiatPaymentMethodType;
import bo.vulcan.kraken.invoice.data.model.db.SiatProduct;
import bo.vulcan.kraken.invoice.data.model.enumeration.InvoiceType;
import bo.vulcan.kraken.invoice.data.model.request.BuyAndSellRequest;
import bo.vulcan.kraken.invoice.data.model.response.AuthenticationResponse;
import bo.vulcan.kraken.invoice.data.model.response.IntegrationResponse;
import bo.vulcan.kraken.invoice.domain.usecases.AuthenticateUseCase;
import bo.vulcan.kraken.invoice.domain.usecases.InvoiceUseCase;
import bo.vulcan.kraken.invoice.domain.usecases.LoadListsUseCase;
import bo.vulcan.kraken.invoice.domain.usecases.SyncUseCase;
import bo.vulcan.kraken.invoice.utils.AppLogger;
import io.reactivex.Single;

public class MIntegrationImpl implements MIntegration {

    @Inject
    AuthenticateUseCase authenticateUseCase;

    @Inject
    SyncUseCase syncUseCase;

    @Inject
    InvoiceUseCase invoiceUseCase;

    @Inject
    LoadListsUseCase loadListsUseCase;

    public MIntegrationImpl(Application application){
        //injectDependencies
        DaggerWrapper.getComponent(application).inject(this);

        AppLogger.init();
        AndroidNetworking.initialize(application.getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }
    }

    @Override
    public Single<AuthenticationResponse> authenticate(String email, String password) {
        return authenticateUseCase.authenticate(email, password);
    }

    @Override
    public Single<IntegrationResponse> syncCompanyData() {
        return syncUseCase.syncCompanyData();
    }

    @Override
    public Single<IntegrationResponse> syncSiatConfiguration() {
        return syncUseCase.syncSiatConfiguration();
    }

    @Override
    public Single<IntegrationResponse> syncAllParametrics() {
        return syncUseCase.syncParametric();
    }


    //------- SiatCurrencyType ------------
    @Override
    public Single<IntegrationResponse> syncCurrencyType() {
        return syncUseCase.syncSiatCurrencyType();
    }

    @Override
    public Single<List<SiatCurrencyType>> getCurrencyTypeList() {
        return loadListsUseCase.getListSiatCurrencyType();
    }

    //------- Document Type ------------
    @Override
    public Single<IntegrationResponse> syncDocumentType() {
        return syncUseCase.syncSiatDocumentType();
    }

    @Override
    public Single<List<SiatDocumentType>> getDocumentTypeList() {
        return loadListsUseCase.getListSiatDocumentType();
    }

    @Override
    public Single<IntegrationResponse> syncPaymentMethodType() {
        return syncUseCase.syncSiatPaymentMethodType();
    }

    @Override
    public Single<List<SiatPaymentMethodType>> getPaymentMethodTypeList() {
        return loadListsUseCase.getListSiatPaymentMethodType();
    }

    @Override
    public Single<IntegrationResponse> syncProducts() {
        return syncUseCase.syncProducts();
    }

    @Override
    public Single<List<Product>> getProductList() {
        return loadListsUseCase.getListProduct();
    }

    @Override
    public Single<IntegrationResponse> syncSiatProducts() {
        return syncUseCase.syncSiatProducts();
    }

    @Override
    public Single<List<SiatProduct>> getSiatProductList() {
        return loadListsUseCase.getListSiatProduct();
    }

    @Override
    public Single<IntegrationResponse> syncBranchActivityLegend() {
        return syncUseCase.syncBranchActivityLegend();
    }

    @Override
    public Single<List<BranchActivityLegend>> getBranchActivityLegendList() {
        return loadListsUseCase.getListBranchActivityLegend();
    }

    @Override
    public Single<IntegrationResponse> syncSiatMeasurementUnit() {
        return syncUseCase.syncSiatMeasurementUnit();
    }

    @Override
    public Single<List<SiatMeasurementUnit>> getSiatMeasurementUnitList() {
        return loadListsUseCase.getListSiatMeasurementUnit();
    }

    //------- CREATE INVOICE ------------
    @Override
    public Single<InvoicePdf> createInvoiceBuyAndSell(BuyAndSellRequest buyAndSellRequest) {
        return invoiceUseCase.createBuyAndSell(buyAndSellRequest, InvoiceType.GENERIC);
    }

    @Override
    public Single<InvoicePdf> getInvoiceByLocalId(String id) {
        return invoiceUseCase.getInvoiceByLocalId(id);
    }

    @Override
    public Single<List<InvoicePdf>> getInvoices() {
        return invoiceUseCase.getInvoices();
    }
}
