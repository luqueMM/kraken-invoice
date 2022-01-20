package bo.vulcan.kraken.invoice.data.local.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import bo.vulcan.kraken.invoice.data.local.db.dao.InvoiceDao;
import bo.vulcan.kraken.invoice.data.local.db.dao.InvoiceDetailDao;
import bo.vulcan.kraken.invoice.data.local.db.dao.ProductDao;
import bo.vulcan.kraken.invoice.data.local.db.dao.SiatCurrencyTypeDao;
import bo.vulcan.kraken.invoice.data.local.db.dao.SiatMeasurementUnitDao;
import bo.vulcan.kraken.invoice.data.local.db.dao.SiatPaymentMethodTypeDao;
import bo.vulcan.kraken.invoice.data.local.db.dao.SiatProductDao;
import bo.vulcan.kraken.invoice.data.model.db.BranchActivityLegend;
import bo.vulcan.kraken.invoice.data.model.db.Invoice;
import bo.vulcan.kraken.invoice.data.model.db.InvoiceDetail;
import bo.vulcan.kraken.invoice.data.model.db.Product;
import bo.vulcan.kraken.invoice.data.model.db.SiatActivity;
import bo.vulcan.kraken.invoice.data.model.db.SiatCurrencyType;
import bo.vulcan.kraken.invoice.data.model.db.SiatDocumentType;
import bo.vulcan.kraken.invoice.data.model.db.SiatMeasurementUnit;
import bo.vulcan.kraken.invoice.data.model.db.SiatPaymentMethodType;
import bo.vulcan.kraken.invoice.data.model.db.SiatProduct;
import bo.vulcan.kraken.invoice.data.model.enumeration.InvoiceType;
import io.reactivex.Completable;
import io.reactivex.Single;

@Singleton
public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Single<Boolean> siatCurrencyTypeInsertList(List<SiatCurrencyType> siatCurrencyTypeList) {
        return Single.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mAppDatabase.siatCurrencyTypeDao().insertAll(siatCurrencyTypeList);
                return true;
            }
        });
    }

    @Override
    public Completable siatCurrencyTypeDeleteAll() {
        return mAppDatabase.siatCurrencyTypeDao().deleteAll();
    }

    @Override
    public Single<List<SiatCurrencyType>> siatCurrencyTypeGetList() {
        return mAppDatabase.siatCurrencyTypeDao().loadAll();
    }

    @Override
    public SiatCurrencyTypeDao siatCurrencyTypeRepository() {
        return mAppDatabase.siatCurrencyTypeDao();
    }

    //------- Document Type ------------

    @Override
    public Single<Boolean> siatDocumentTypeInsertList(List<SiatDocumentType> siatDocumentTypeList) {
        return Single.fromCallable(() -> {
            mAppDatabase.siatDocumentTypeDao().insertAll(siatDocumentTypeList);
            return true;
        });
    }

    @Override
    public Completable siatDocumentTypeDeleteAll() {
        return mAppDatabase.siatDocumentTypeDao().deleteAll();
    }

    @Override
    public Single<List<SiatDocumentType>> siatDocumentTypeGetList() {
        return mAppDatabase.siatDocumentTypeDao().loadAll();
    }

    @Override
    public Single<SiatDocumentType> siatDocumentTypeGet(String classifierCode) {
        return mAppDatabase.siatDocumentTypeDao().findFirstByClassifierCode(classifierCode);
    }

    @Override
    public SiatPaymentMethodTypeDao siatPaymentMethodType() {
        return mAppDatabase.siatPaymentMethodTypeDao();
    }

    //------- Payment Method  Type ------------

    @Override
    public Completable siatPaymentMethodTypeDeleteAll() {
        return mAppDatabase.siatPaymentMethodTypeDao().deleteAll();
    }

    @Override
    public Single<List<SiatPaymentMethodType>> siatPaymentMethodTypeGetList() {
        return mAppDatabase.siatPaymentMethodTypeDao().loadAll();
    }

    @Override
    public Single<Boolean> siatPaymentMethodTypeInsertList(List<SiatPaymentMethodType> siatPaymentMethodTypeList) {
        return Single.fromCallable(() -> {
            mAppDatabase.siatPaymentMethodTypeDao().insertAll(siatPaymentMethodTypeList);
            return true;
        });
    }

    //------- Products ------------

    @Override
    public Single<Boolean> productInsertList(List<Product> productList) {
        return Single.fromCallable(() -> {
            mAppDatabase.productDao().insertAll(productList);
            return true;
        });
    }

    @Override
    public Completable productDeleteAll() {
        return mAppDatabase.productDao().deleteAll();
    }

    @Override
    public Single<List<Product>> productGetList() {
        return mAppDatabase.productDao().loadAll();
    }

    @Override
    public ProductDao product() {
        return mAppDatabase.productDao();
    }

    //------- Siat Products ------------

    @Override
    public Single<Boolean> siatProductInsertList(List<SiatProduct> siatProductList) {
        return Single.fromCallable(() -> {
            mAppDatabase.siatProductDao().insertAll(siatProductList);
            return true;
        });
    }

    @Override
    public Completable siatProductDeleteAll() {
        return mAppDatabase.siatProductDao().deleteAll();
    }

    @Override
    public Single<List<SiatProduct>> siatProductGetList() {
        return mAppDatabase.siatProductDao().loadAll();
    }

    @Override
    public SiatProductDao siatProductRepository() {
        return mAppDatabase.siatProductDao();
    }

    //------- Branch Activity Legends ------------

    @Override
    public Single<Boolean> branchActivityLegendInsertList(List<BranchActivityLegend> branchActivityLegendList) {
        return Single.fromCallable(() -> {
            mAppDatabase.branchActivityLegendDao().insertAll(branchActivityLegendList);
            return true;
        });
    }

    @Override
    public Completable branchActivityLegendDeleteAll() {
        return mAppDatabase.branchActivityLegendDao().deleteAll();
    }

    @Override
    public Single<List<BranchActivityLegend>> branchActivityLegendGetList() {
        return mAppDatabase.branchActivityLegendDao().loadAll();
    }

    @Override
    public BranchActivityLegend branchActivityLegendGet(Long branchId, Long activityId) {
        return mAppDatabase.branchActivityLegendDao().findByBranchIdAndActivityId(branchId, activityId);
    }

    //------- Siat Activity ------------

    @Override
    public Single<Boolean> siatActivityInsertList(List<SiatActivity> siatActivityList) {
        return Single.fromCallable(() -> {
            mAppDatabase.siatActivityDao().insertAll(siatActivityList);
            return true;
        });
    }

    @Override
    public Completable siatActivityDeleteAll() {
        return mAppDatabase.siatActivityDao().deleteAll();
    }

    //------- Invoice ------------

    @Override
    public Single<Boolean> invoiceInsertList(List<Invoice> invoiceList) {
        return Single.fromCallable(() -> {
            mAppDatabase.invoiceDao().insertAll(invoiceList);
            return true;
        });
    }

    @Override
    public Invoice invoiceSave(Invoice invoice) {
        mAppDatabase.invoiceDao().insert(invoice);
        return invoice;
    }

    @Override
    public Completable invoiceDeleteAll() {
        return mAppDatabase.invoiceDao().deleteAll();
    }

    @Override
    public Single<List<Invoice>> invoiceGetList() {
        return mAppDatabase.invoiceDao().loadAll();
    }

    @Override
    public InvoiceDao invoiceRepository() {
        return mAppDatabase.invoiceDao();
    }

    //------- Invoice Detail------------

    @Override
    public Single<Boolean> invoiceDetailInsertList(List<InvoiceDetail> invoiceDetailList) {
        return Single.fromCallable(() -> {
            mAppDatabase.invoiceDetailDao().insertAll(invoiceDetailList);
            return true;
        });
    }

    @Override
    public InvoiceDetail invoiceDetailSave(InvoiceDetail invoiceDetail) {
        mAppDatabase.invoiceDetailDao().insert(invoiceDetail);
        return invoiceDetail;
    }

    @Override
    public void invoiceDetailSaveAll(Set<InvoiceDetail> invoiceDetailList) {
        mAppDatabase.invoiceDetailDao().insertAll(new ArrayList<>(invoiceDetailList));
    }

    @Override
    public Completable invoiceDetailDeleteAll() {
        return mAppDatabase.invoiceDetailDao().deleteAll();
    }

    @Override
    public Single<List<InvoiceDetail>> invoiceDetailGetList() {
        return mAppDatabase.invoiceDetailDao().loadAll();
    }

    @Override
    public InvoiceDetailDao invoiceDetailRepository() {
        return mAppDatabase.invoiceDetailDao();
    }

    //------- Siat Measurement Unit------------

    @Override
    public Single<Boolean> siatMeasurementUnitInsertList(List<SiatMeasurementUnit> siatMeasurementUnitList) {
        return Single.fromCallable(() -> {
            mAppDatabase.siatMeasurementUnitDao().insertAll(siatMeasurementUnitList);
            return true;
        });
    }

    @Override
    public Completable siatMeasurementUnitDeleteAll() {
        return mAppDatabase.siatMeasurementUnitDao().deleteAll();
    }

    @Override
    public Single<List<SiatMeasurementUnit>> siatMeasurementUnitGetList() {
        return mAppDatabase.siatMeasurementUnitDao().loadAll();
    }

    @Override
    public SiatMeasurementUnitDao siatMeasurementUnitRepository() {
        return mAppDatabase.siatMeasurementUnitDao();
    }
}
