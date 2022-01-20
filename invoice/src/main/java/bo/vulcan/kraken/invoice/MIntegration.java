package bo.vulcan.kraken.invoice;

import android.app.Application;

import java.util.List;

import bo.vulcan.kraken.invoice.data.model.db.BranchActivityLegend;
import bo.vulcan.kraken.invoice.data.model.db.Invoice;
import bo.vulcan.kraken.invoice.data.model.db.InvoicePdf;
import bo.vulcan.kraken.invoice.data.model.db.Product;
import bo.vulcan.kraken.invoice.data.model.db.SiatCurrencyType;
import bo.vulcan.kraken.invoice.data.model.db.SiatDocumentType;
import bo.vulcan.kraken.invoice.data.model.db.SiatMeasurementUnit;
import bo.vulcan.kraken.invoice.data.model.db.SiatPaymentMethodType;
import bo.vulcan.kraken.invoice.data.model.db.SiatProduct;
import bo.vulcan.kraken.invoice.data.model.request.BuyAndSellRequest;
import bo.vulcan.kraken.invoice.data.model.response.AuthenticationResponse;
import bo.vulcan.kraken.invoice.data.model.response.IntegrationResponse;
import io.reactivex.Completable;
import io.reactivex.Single;

public interface MIntegration {

    Single<AuthenticationResponse> authenticate(String email, String password);
    Single<IntegrationResponse> syncCompanyData();
    Single<IntegrationResponse> syncSiatConfiguration();

    Single<IntegrationResponse> syncAllParametrics();

    Single<IntegrationResponse> syncCurrencyType();
    Single<List<SiatCurrencyType>> getCurrencyTypeList();

    Single<IntegrationResponse> syncDocumentType();
    Single<List<SiatDocumentType>> getDocumentTypeList();

    Single<IntegrationResponse> syncPaymentMethodType();
    Single<List<SiatPaymentMethodType>> getPaymentMethodTypeList();

    Single<IntegrationResponse> syncProducts();
    Single<List<Product>> getProductList();

    Single<IntegrationResponse> syncSiatProducts();
    Single<List<SiatProduct>> getSiatProductList();

    Single<IntegrationResponse> syncBranchActivityLegend();
    Single<List<BranchActivityLegend>> getBranchActivityLegendList();

    Single<IntegrationResponse> syncSiatMeasurementUnit();
    Single<List<SiatMeasurementUnit>> getSiatMeasurementUnitList();

    Single<InvoicePdf> getInvoiceByLocalId(String id);
    Single<List<InvoicePdf>> getInvoices();
    Single<InvoicePdf> createInvoiceBuyAndSell(BuyAndSellRequest buyAndSellRequest);

    public static final class MIntegrationObject {
        private static MIntegration value;

        private MIntegrationObject() {}

        public static MIntegration getInstance(Application application) {
            if (value == null) {
                value = new MIntegrationImpl(application);
                System.out.println("--------CREATE NEW MIntegration-----------");
            }
            return value;
        }
    }
}