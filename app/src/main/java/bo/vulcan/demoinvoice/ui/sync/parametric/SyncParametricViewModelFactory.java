package bo.vulcan.demoinvoice.ui.sync.parametric;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import bo.vulcan.demoinvoice.ui.base.Navigator;
import bo.vulcan.kraken.invoice.MIntegration;

public class SyncParametricViewModelFactory implements ViewModelProvider.Factory {

    private final MIntegration MIntegration;
    private final Navigator navigator;

    public SyncParametricViewModelFactory(MIntegration MIntegration, Navigator navigator) {
        this.MIntegration = MIntegration;
        this.navigator = navigator;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SyncParametricViewModel.class)) {
            return (T) new SyncParametricViewModel(MIntegration, navigator);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
