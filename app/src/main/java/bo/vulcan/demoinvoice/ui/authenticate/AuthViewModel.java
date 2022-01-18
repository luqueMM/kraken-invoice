package bo.vulcan.demoinvoice.ui.authenticate;

import androidx.lifecycle.ViewModel;

import bo.vulcan.demoinvoice.ui.base.Navigator;
import bo.vulcan.kraken.invoice.MIntegration;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class AuthViewModel extends ViewModel {

    private final MIntegration mIntegration;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final Navigator navigator;

    public AuthViewModel(MIntegration mIntegration, Navigator authNavigator) {
        this.mIntegration = mIntegration;
        this.navigator = authNavigator;
    }

    /**
     * Authenticate
     */
    public void authenticate(String usr, String pw) {
        disposables.add(mIntegration.authenticate(usr, pw)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> navigator.updateResponse("auth succes: " + response),
                        error -> {
                            navigator.updateResponse("auth error " + error);
                            error.printStackTrace();
                        })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }
}