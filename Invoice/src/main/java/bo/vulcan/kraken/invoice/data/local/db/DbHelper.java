package bo.vulcan.kraken.invoice.data.local.db;

import java.util.List;
import java.util.Set;

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

public interface DbHelper {

    Single<Boolean> siatCurrencyTypeInsertList(List<SiatCurrencyType> siatCurrencyTypeList);

    Completable siatCurrencyTypeDeleteAll();

    Single<List<SiatCurrencyType>> siatCurrencyTypeGetList();

    SiatCurrencyTypeDao siatCurrencyTypeRepository ();

    //------- Document Type ------------

    Single<Boolean> siatDocumentTypeInsertList(List<SiatDocumentType> siatDocumentTypeList);

    Completable siatDocumentTypeDeleteAll();

    Single<List<SiatDocumentType>> siatDocumentTypeGetList();

    Single<SiatDocumentType> siatDocumentTypeGet(String classifierCode);

    //------- Payment Method  Type ------------

    Single<Boolean> siatPaymentMethodTypeInsertList(List<SiatPaymentMethodType> siatPaymentMethodTypeList);

    Completable siatPaymentMethodTypeDeleteAll();

    Single<List<SiatPaymentMethodType>> siatPaymentMethodTypeGetList();

    SiatPaymentMethodTypeDao siatPaymentMethodType();

    //------- Products ------------

    Single<Boolean> productInsertList(List<Product> productList);

    Completable productDeleteAll();

    Single<List<Product>> productGetList();

    ProductDao product();

    //------- Siat Products ------------

    Single<Boolean> siatProductInsertList(List<SiatProduct> siatProductList);

    Completable siatProductDeleteAll();

    Single<List<SiatProduct>> siatProductGetList();

    SiatProductDao siatProductRepository();

    //------- Branch Activity Legends ------------

    Single<Boolean> branchActivityLegendInsertList(List<BranchActivityLegend> branchActivityLegendList);

    Completable branchActivityLegendDeleteAll();

    Single<List<BranchActivityLegend>> branchActivityLegendGetList();

    BranchActivityLegend branchActivityLegendGet(Long branchId, Long activityId);

    //------- Siat Activity ------------

    Single<Boolean> siatActivityInsertList(List<SiatActivity> siatActivityList);

    Completable siatActivityDeleteAll();

    //------- Invoices ------------

    Single<Boolean> invoiceInsertList(List<Invoice> invoiceList);

    Invoice invoiceSave(Invoice invoice);

    Completable invoiceDeleteAll();

    Single<List<Invoice>> invoiceGetList();

    InvoiceDao invoiceRepository();

    //------- Invoice Detail------------

    Single<Boolean> invoiceDetailInsertList(List<InvoiceDetail> invoiceDetailList);

    InvoiceDetail invoiceDetailSave(InvoiceDetail invoiceDetail);

    void invoiceDetailSaveAll(Set<InvoiceDetail> invoiceDetailList);

    Completable invoiceDetailDeleteAll();

    Single<List<InvoiceDetail>> invoiceDetailGetList();

    InvoiceDetailDao invoiceDetailRepository();

    //------- Siat Measurement Unit------------

    Single<Boolean> siatMeasurementUnitInsertList(List<SiatMeasurementUnit> siatMeasurementUnitList);

    Completable siatMeasurementUnitDeleteAll();

    Single<List<SiatMeasurementUnit>> siatMeasurementUnitGetList();

    SiatMeasurementUnitDao siatMeasurementUnitRepository();

}
