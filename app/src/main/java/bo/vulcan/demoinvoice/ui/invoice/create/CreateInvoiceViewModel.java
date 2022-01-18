package bo.vulcan.demoinvoice.ui.invoice.create;

import androidx.lifecycle.ViewModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import bo.vulcan.demoinvoice.ui.base.Navigator;
import bo.vulcan.kraken.invoice.MIntegration;
import bo.vulcan.kraken.invoice.data.model.request.BuyAndSellDetailsRequest;
import bo.vulcan.kraken.invoice.data.model.request.BuyAndSellRequest;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CreateInvoiceViewModel extends ViewModel {

    private final MIntegration mIntegration;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final Navigator navigator;


    public CreateInvoiceViewModel(MIntegration mIntegration, Navigator authNavigator) {
        this.mIntegration = mIntegration;
        this.navigator = authNavigator;
    }

    public void createInvoiceBuynAndSell() {
        disposables.add(mIntegration.createInvoiceBuyAndSell(getBuyAndSellRequest())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> navigator.updateResponse("CREATE INVOICE SUCCESS: " + response),
                        error -> {
                            navigator.updateResponse("ERROR: " + error);
                            error.printStackTrace();
                        })
        );
    }

    BuyAndSellRequest getBuyAndSellRequest(){
        BuyAndSellRequest buynSell = new BuyAndSellRequest(
                "Arturo Herrera",
                "6839033",
                1,
                Arrays.asList(new BuyAndSellDetailsRequest(
                        new BigDecimal(1),
                        "Zapatos Amarillos",
                        new BigDecimal(50),
                        new BigDecimal(50)
                ))
        );

        buynSell.setId("0001-BuySell");
        buynSell.setCustomerCode("1234");
        buynSell.setPaymentMethodType(1);
        buynSell.setUserCode("herreraa");
        buynSell.setEmailNotification("aherrera@vulcan.bo");
        buynSell.setExtraCustomerAddress("La Paz, zona Miraflores");

        return buynSell;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }
}
