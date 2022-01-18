package bo.vulcan.demoinvoice.ui.sync.parametric;

import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import bo.vulcan.demoinvoice.ui.base.Navigator;
import bo.vulcan.demoinvoice.ui.base.UIStatus;
import bo.vulcan.kraken.invoice.MIntegration;
import bo.vulcan.kraken.invoice.data.model.db.BranchActivityLegend;
import bo.vulcan.kraken.invoice.data.model.db.Product;
import bo.vulcan.kraken.invoice.data.model.db.SiatCurrencyType;
import bo.vulcan.kraken.invoice.data.model.db.SiatDocumentType;
import bo.vulcan.kraken.invoice.data.model.db.SiatMeasurementUnit;
import bo.vulcan.kraken.invoice.data.model.db.SiatPaymentMethodType;
import bo.vulcan.kraken.invoice.data.model.db.SiatProduct;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SyncParametricViewModel extends ViewModel {

    private final MIntegration mIntegration;
    private final Navigator navigator;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private Gson gson = new Gson();

    public SyncParametricViewModel(MIntegration mIntegration, Navigator navigator) {
        this.mIntegration = mIntegration;
        this.navigator = navigator;
    }

    void syncAllParametrics() {
        navigator.updateUI(UIStatus.IN_PROCESS);
        disposables.add(mIntegration.syncAllParametrics()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
                    navigator.updateResponse("SUCCESS - " + response);
                    navigator.updateUI(UIStatus.SUCCESS);
                }, this::onError)
        );
    }

    void syncSiatCurrencyType() {
        navigator.updateUI(UIStatus.IN_PROCESS);
        disposables.add(mIntegration.syncCurrencyType()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
                    navigator.updateResponse("SUCCESS - " + response);
                    navigator.updateUI(UIStatus.SUCCESS);
                }, this::onError)
        );
    }

    void getListSiatCurrencyType() {
        navigator.updateUI(UIStatus.IN_PROCESS);
        disposables.add(mIntegration.getCurrencyTypeList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(list -> {
                    List<Object> objList = new ArrayList<Object>();

                    for (SiatCurrencyType item : list) {
                        objList.add(((Object) item));
                    }

                    return objList;
                })
                .subscribe((response) -> {
                    navigator.updateResponse("GET LIST SUCCESS!: " + response.size());
                    navigator.updateUI(UIStatus.SUCCESS);
                    navigator.navigateListActivity(convertStringList(response), "List - Siat Currency Type");
                }, this::onError)
        );
    }

    void syncDocumentType() {
        navigator.updateUI(UIStatus.IN_PROCESS);
        disposables.add(mIntegration.syncDocumentType()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
                    navigator.updateResponse("SUCCESS - " + response);
                    navigator.updateUI(UIStatus.SUCCESS);
                }, this::onError)
        );
    }

    void getListDocumentType() {
        navigator.updateUI(UIStatus.IN_PROCESS);
        disposables.add(mIntegration.getDocumentTypeList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(list -> {
                    List<Object> objList = new ArrayList<Object>();

                    for (SiatDocumentType item : list) {
                        objList.add(((Object) item));
                    }

                    return objList;
                })
                .subscribe((response) -> {
                    navigator.updateResponse("GET LIST SUCCESS!: " + response.size());
                    navigator.updateUI(UIStatus.SUCCESS);
                    navigator.navigateListActivity(convertStringList(response), "List - Document Type");
                }, this::onError)
        );
    }

    void syncPaymentMethodType() {
        navigator.updateUI(UIStatus.IN_PROCESS);
        disposables.add(mIntegration.syncPaymentMethodType()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
                    navigator.updateResponse("SUCCESS - " + response);
                    navigator.updateUI(UIStatus.SUCCESS);
                }, this::onError)
        );
    }

    void getListPaymentMethodType() {
        navigator.updateUI(UIStatus.IN_PROCESS);
        disposables.add(mIntegration.getPaymentMethodTypeList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(list -> {
                    List<Object> objList = new ArrayList<Object>();

                    for (SiatPaymentMethodType item : list) {
                        objList.add(((Object) item));
                    }

                    return objList;
                })
                .subscribe((response) -> {
                    navigator.updateResponse("GET LIST SUCCESS!: " + response.size());
                    navigator.updateUI(UIStatus.SUCCESS);
                    navigator.navigateListActivity(convertStringList(response), "List - PaymentMethodType");
                }, this::onError)
        );
    }

    void syncProducts() {
        navigator.updateUI(UIStatus.IN_PROCESS);
        disposables.add(mIntegration.syncProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
                    navigator.updateResponse("SUCCESS - " + response);
                    navigator.updateUI(UIStatus.SUCCESS);
                }, this::onError)
        );
    }

    void getListProducts() {
        navigator.updateUI(UIStatus.IN_PROCESS);
        disposables.add(mIntegration.getProductList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(list -> {
                    List<Object> objList = new ArrayList<Object>();

                    for (Product item : list) {
                        objList.add(((Object) item));
                    }

                    return objList;
                })
                .subscribe((response) -> {
                    navigator.updateResponse("GET LIST SUCCESS!: " + response.size());
                    navigator.updateUI(UIStatus.SUCCESS);
                    navigator.navigateListActivity(convertStringList(response), "List - Products");
                }, this::onError)
        );
    }

    void syncSiatProducts() {
        navigator.updateUI(UIStatus.IN_PROCESS);
        disposables.add(mIntegration.syncSiatProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
                    navigator.updateResponse("SUCCESS - " + response);
                    navigator.updateUI(UIStatus.SUCCESS);
                }, this::onError)
        );
    }

    void getListSiatProducts() {
        navigator.updateUI(UIStatus.IN_PROCESS);
        disposables.add(mIntegration.getSiatProductList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(list -> {
                    List<Object> objList = new ArrayList<Object>();

                    for (SiatProduct item : list) {
                        objList.add(((Object) item));
                    }

                    return objList;
                })
                .subscribe((response) -> {
                    navigator.updateResponse("GET LIST SUCCESS!: " + response.size());
                    navigator.updateUI(UIStatus.SUCCESS);
                    navigator.navigateListActivity(convertStringList(response), "List - SiatProducts");
                }, this::onError)
        );
    }

    void syncBranchActivityLegend() {
        navigator.updateUI(UIStatus.IN_PROCESS);
        disposables.add(mIntegration.syncBranchActivityLegend()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
                    navigator.updateResponse("SUCCESS - " + response);
                    navigator.updateUI(UIStatus.SUCCESS);
                }, this::onError)
        );
    }

    void getListBranchActivityLegend() {
        navigator.updateUI(UIStatus.IN_PROCESS);
        disposables.add(mIntegration.getBranchActivityLegendList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(list -> {
                    List<Object> objList = new ArrayList<Object>();

                    for (BranchActivityLegend item : list) {
                        objList.add(((Object) item));
                    }

                    return objList;
                })
                .subscribe((response) -> {
                    navigator.updateResponse("GET LIST SUCCESS!: " + response.size());
                    navigator.updateUI(UIStatus.SUCCESS);
                    navigator.navigateListActivity(convertStringList(response), "List - BranchActivityLegend");
                }, this::onError)
        );
    }

    void syncSiatMeasurementUnit() {
        navigator.updateUI(UIStatus.IN_PROCESS);
        disposables.add(mIntegration.syncSiatMeasurementUnit()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
                    navigator.updateResponse("SUCCESS - " + response);
                    navigator.updateUI(UIStatus.SUCCESS);
                }, this::onError)
        );
    }

    void getListSiatMeasurementUnit() {
        navigator.updateUI(UIStatus.IN_PROCESS);
        disposables.add(mIntegration.getSiatMeasurementUnitList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(list -> {
                    List<Object> objList = new ArrayList<Object>();

                    for (SiatMeasurementUnit item : list) {
                        objList.add(((Object) item));
                    }

                    return objList;
                })
                .subscribe((response) -> {
                    navigator.updateResponse("GET LIST SUCCESS!: " + response.size());
                    navigator.updateUI(UIStatus.SUCCESS);
                    navigator.navigateListActivity(convertStringList(response), "List - SiatMeasurementUnits");
                }, this::onError)
        );
    }

    void onError(Throwable error){
        navigator.updateResponse("ERROR: " + error);
        navigator.updateUI(UIStatus.ERROR);
        error.printStackTrace();
    }

    List<String> convertStringList(List<Object> list) {
        List<String> strList = new ArrayList<String>();

        for (Object o : list) {
            String json = gson.toJson(o);
            strList.add(json);
        }

        return strList;
    }

    @Override
    protected void onCleared() {
        disposables.dispose();
        super.onCleared();
    }
}
