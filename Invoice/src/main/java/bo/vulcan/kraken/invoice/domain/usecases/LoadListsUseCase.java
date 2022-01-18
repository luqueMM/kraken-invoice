package bo.vulcan.kraken.invoice.domain.usecases;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import bo.vulcan.kraken.invoice.data.DataManager;
import bo.vulcan.kraken.invoice.data.model.db.BranchActivityLegend;
import bo.vulcan.kraken.invoice.data.model.db.Product;
import bo.vulcan.kraken.invoice.data.model.db.SiatCurrencyType;
import bo.vulcan.kraken.invoice.data.model.db.SiatDocumentType;
import bo.vulcan.kraken.invoice.data.model.db.SiatMeasurementUnit;
import bo.vulcan.kraken.invoice.data.model.db.SiatPaymentMethodType;
import bo.vulcan.kraken.invoice.data.model.db.SiatProduct;
import io.reactivex.Single;

@Singleton
public class LoadListsUseCase {

    private final DataManager mDataManager;

    @Inject
    public LoadListsUseCase(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    public Single<List<SiatCurrencyType>> getListSiatCurrencyType() {
        return mDataManager.siatCurrencyTypeGetList();
    }

    public Single<List<SiatDocumentType>> getListSiatDocumentType() {
        return mDataManager.siatDocumentTypeGetList();
    }

    public Single<List<SiatPaymentMethodType>> getListSiatPaymentMethodType() {
        return mDataManager.siatPaymentMethodTypeGetList();
    }

    public Single<List<Product>> getListProduct() {
        return mDataManager.productGetList();
    }

    public Single<List<SiatProduct>> getListSiatProduct() {
        return mDataManager.siatProductGetList();
    }

    public Single<List<BranchActivityLegend>> getListBranchActivityLegend() {
        return mDataManager.branchActivityLegendGetList();
    }

    public Single<List<SiatMeasurementUnit>> getListSiatMeasurementUnit() {
        return mDataManager.siatMeasurementUnitGetList();
    }
}
