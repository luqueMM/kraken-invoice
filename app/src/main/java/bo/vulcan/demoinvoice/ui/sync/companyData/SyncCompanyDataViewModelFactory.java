package bo.vulcan.demoinvoice.ui.sync.companyData;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import bo.vulcan.demoinvoice.ui.base.Navigator;
import bo.vulcan.kraken.invoice.MIntegration;

public class SyncCompanyDataViewModelFactory implements ViewModelProvider.Factory {

    private final MIntegration MIntegration;
    private final Navigator navigator;

    public SyncCompanyDataViewModelFactory(MIntegration MIntegration, Navigator navigator) {
        this.MIntegration = MIntegration;
        this.navigator = navigator;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SyncCompanyDataViewModel.class)) {
            return (T) new SyncCompanyDataViewModel(MIntegration, navigator);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
