package bo.vulcan.demoinvoice.ui.invoice.list;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import bo.vulcan.demoinvoice.ui.base.Navigator;
import bo.vulcan.kraken.invoice.MIntegration;

public class InvoicesViewModelFactory implements ViewModelProvider.Factory{

    private final MIntegration MIntegration;
    private final Navigator navigator;

    public InvoicesViewModelFactory(bo.vulcan.kraken.invoice.MIntegration mIntegration, Navigator navigator) {
        MIntegration = mIntegration;
        this.navigator = navigator;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(InvoicesViewModel.class)) {
            return (T) new InvoicesViewModel(MIntegration, navigator);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
