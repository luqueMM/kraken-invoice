package bo.vulcan.demoinvoice.ui.sync.companyData;


import androidx.lifecycle.ViewModel;

import bo.vulcan.demoinvoice.ui.base.Navigator;
import bo.vulcan.demoinvoice.ui.base.UIStatus;
import bo.vulcan.kraken.invoice.MIntegration;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SyncCompanyDataViewModel extends ViewModel {

    private final MIntegration mIntegration;
    private final Navigator navigator;
    private final CompositeDisposable disposables = new CompositeDisposable();

    public SyncCompanyDataViewModel(MIntegration mIntegration, Navigator navigator) {
        this.mIntegration = mIntegration;
        this.navigator = navigator;
    }

    void syncCompany() {
        disposables.add(mIntegration.syncCompanyData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
                    navigator.updateResponse("SUCCESS - " + response);
                    navigator.updateUI(UIStatus.SUCCESS);
                }, this::onError)
        );
    }

    void syncSiatConfig() {
        disposables.add(mIntegration.syncSiatConfiguration()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
                    navigator.updateResponse("SUCCESS - " + response);
                    navigator.updateUI(UIStatus.SUCCESS);
                }, this::onError)
        );
    }

    void onError(Throwable error){
        navigator.updateResponse("ERROR: " + error);
        navigator.updateUI(UIStatus.ERROR);
        error.printStackTrace();
    }

    @Override
    protected void onCleared() {
        disposables.dispose();
        super.onCleared();
    }
}
