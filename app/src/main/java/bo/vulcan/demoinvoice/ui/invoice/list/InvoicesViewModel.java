package bo.vulcan.demoinvoice.ui.invoice.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import bo.vulcan.demoinvoice.ui.base.Navigator;
import bo.vulcan.kraken.invoice.MIntegration;
import bo.vulcan.kraken.invoice.data.model.db.InvoicePdf;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class InvoicesViewModel extends ViewModel {

    private final MIntegration mIntegration;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final Navigator navigator;

    private MutableLiveData<List<InvoicePdf>> commentsLiveData = new MutableLiveData<>();

    public InvoicesViewModel(MIntegration mIntegration, Navigator navigator) {
        this.mIntegration = mIntegration;
        this.navigator = navigator;

        loadInvoices();
    }

    /**
     * Exposes the latest comments so the UI can observe it
     */
    public LiveData<List<InvoicePdf>> invoices() {
        return commentsLiveData;
    }

    void loadInvoices(){
        disposables.add(mIntegration.getInvoices()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(commentsLiveData::setValue,
                        t -> Timber.e(t, "get comments error")));
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
