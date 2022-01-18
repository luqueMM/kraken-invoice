package bo.vulcan.kraken.invoice.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import bo.vulcan.kraken.invoice.data.local.db.dao.BranchActivityLegendDao;
import bo.vulcan.kraken.invoice.data.local.db.dao.InvoiceDao;
import bo.vulcan.kraken.invoice.data.local.db.dao.InvoiceDetailDao;
import bo.vulcan.kraken.invoice.data.local.db.dao.ProductDao;
import bo.vulcan.kraken.invoice.data.local.db.dao.SiatActivityDao;
import bo.vulcan.kraken.invoice.data.local.db.dao.SiatCurrencyTypeDao;
import bo.vulcan.kraken.invoice.data.local.db.dao.SiatDocumentTypeDao;
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

@Database(entities = {
        SiatCurrencyType.class,
        SiatDocumentType.class,
        SiatPaymentMethodType.class,
        Product.class,
        SiatProduct.class,
        BranchActivityLegend.class,
        SiatActivity.class,
        Invoice.class,
        InvoiceDetail.class,
        SiatMeasurementUnit.class
}, version = 10, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract SiatCurrencyTypeDao siatCurrencyTypeDao();

    public abstract SiatDocumentTypeDao siatDocumentTypeDao();

    public abstract SiatPaymentMethodTypeDao siatPaymentMethodTypeDao();

    public abstract ProductDao productDao();

    public abstract SiatProductDao siatProductDao();

    public abstract BranchActivityLegendDao branchActivityLegendDao();

    public abstract SiatActivityDao siatActivityDao();

    public abstract InvoiceDao invoiceDao();

    public abstract InvoiceDetailDao invoiceDetailDao();

    public abstract SiatMeasurementUnitDao siatMeasurementUnitDao();
}