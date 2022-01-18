package bo.vulcan.kraken.invoice.domain.usecases;

import com.androidnetworking.error.ANError;

import javax.inject.Inject;
import javax.inject.Singleton;

import bo.vulcan.kraken.invoice.data.DataManager;
import bo.vulcan.kraken.invoice.data.model.errors.BadRequestException;
import bo.vulcan.kraken.invoice.data.model.response.IntegrationResponse;
import bo.vulcan.kraken.invoice.utils.ErrorHelper;
import io.reactivex.Single;

@Singleton
public class SyncUseCase {

    private final DataManager mDataManager;

    @Inject
    public SyncUseCase(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    public Single<IntegrationResponse> syncCompanyData() {
        //TODO add sync service
        return mDataManager.syncUserInfo()
                .flatMap(response -> mDataManager.syncSystemStatus())
                .onErrorResumeNext(new ErrorHelper()::onError)
                .map(response -> {
                    //TODO Modify response
                    IntegrationResponse result = new IntegrationResponse();
                    result.setOutput(response.toString());
                    return result;
                });
    }

    public Single<IntegrationResponse> syncSiatConfiguration() {
        //TODO add sync service
        return mDataManager.syncSiatConfiguration()
                .onErrorResumeNext(new ErrorHelper()::onError)
                .map(response -> {
                    //TODO Modify response
                    IntegrationResponse result = new IntegrationResponse();
                    result.setOutput(response.toString());
                    return result;
                });
    }

    /* PARAMETRICAS */
    public Single<IntegrationResponse> syncParametric() {
        return this.syncSiatCurrencyType()
                .flatMap(response->syncSiatDocumentType())
                .flatMap(response->syncSiatPaymentMethodType())
                .flatMap(response->syncProducts())
                .flatMap(response->syncSiatProducts())
                .flatMap(response->syncBranchActivityLegend())
                .flatMap(response->syncSiatMeasurementUnit());
    }

    public Single<IntegrationResponse> syncSiatCurrencyType() {
        return this.mDataManager.getAllCurrencyTypes(mDataManager.getCompany().getId())
                .flatMap(response -> mDataManager.syncSiatCurrencyTypeList(response))
                .onErrorResumeNext(new ErrorHelper()::onError)
                .map(response -> {
                    //TODO Modify response
                    return new IntegrationResponse();
                });
    }

    public Single<IntegrationResponse> syncSiatDocumentType() {
        return this.mDataManager.getAllDocumentTypes(mDataManager.getCompany().getId())
                .flatMap(mDataManager::syncSiatDocumentTypeList)
                .onErrorResumeNext(new ErrorHelper()::onError)
                .map(response -> {
                    //TODO Modify response
                    return new IntegrationResponse();
                });
    }

    public Single<IntegrationResponse> syncSiatPaymentMethodType() {
        return this.mDataManager.getAllPaymentMethodTypes(mDataManager.getCompany().getId())
                .flatMap(mDataManager::syncSiatPaymentMethodTypeList)
                .onErrorResumeNext(new ErrorHelper()::onError)
                .map(response -> {
                    //TODO Modify response
                    return new IntegrationResponse();
                });
    }

    public Single<IntegrationResponse> syncProducts() {
        return this.mDataManager.getAllProducts(mDataManager.getCompany().getId())
                .flatMap(mDataManager::syncProductList)
                .onErrorResumeNext(new ErrorHelper()::onError)
                .map(response -> {
                    //TODO Modify response
                    return new IntegrationResponse();
                });
    }

    public Single<IntegrationResponse> syncSiatProducts() {
        return this.mDataManager.getAllSiatProducts(mDataManager.getCompany().getId())
                .flatMap(mDataManager::syncSiatProductList)
                .onErrorResumeNext(new ErrorHelper()::onError)
                .map(response -> {
                    //TODO Modify response
                    return new IntegrationResponse();
                });
    }

    public Single<IntegrationResponse> syncBranchActivityLegend() {
        return this.mDataManager.getAllBranchActivityLegends(mDataManager.getBranch().getId())
                .flatMap(mDataManager::syncBranchActivityLegendList)
                .onErrorResumeNext(new ErrorHelper()::onError)
                .map(response -> {
                    //TODO Modify response
                    return new IntegrationResponse();
                });
    }

    public Single<IntegrationResponse> syncSiatMeasurementUnit() {
        return this.mDataManager.getAllSiatMeasurementUnit(mDataManager.getCompany().getId())
                .flatMap(mDataManager::syncSiatMeasurementUnitList)
                .onErrorResumeNext(new ErrorHelper()::onError)
                .map(response -> {
                    //TODO Modify response
                    return new IntegrationResponse();
                });
    }
}
