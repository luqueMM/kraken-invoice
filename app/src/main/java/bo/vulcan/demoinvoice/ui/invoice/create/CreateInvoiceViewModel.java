package bo.vulcan.demoinvoice.ui.invoice.create;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.math.BigDecimal;
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
                .subscribe((invoice) -> {
                            navigator.updateResponse("CREATE INVOICE SUCCESS: " + invoice);
                            Log.d("INVOICEPDF", invoice + "");
                        },
                        error -> {
                            navigator.updateResponse("ERROR: " + error);
                            error.printStackTrace();
                        })
        );
    }

    BuyAndSellRequest getBuyAndSellRequest() {

        BuyAndSellRequest buynSell = new BuyAndSellRequest(
                "Arturo Herrera",
                "6839033",
                1,
                Arrays.asList(new BuyAndSellDetailsRequest(
                        new BigDecimal(1),
                        "Zapatos Amarillos",
                        new BigDecimal(50),
                        new BigDecimal(50),
                        "KR123"
                ))
        );

        // Optionals
        buynSell.setId("0003-BuySell");
        buynSell.setCustomerCode("1234");
        buynSell.setPaymentMethodType(1);
        buynSell.setUserCode("herreraa");
        buynSell.setEmailNotification("aherrera@vulcan.bo");
        buynSell.setExtraCustomerAddress("La Paz, zona Miraflores");
        buynSell.setDocumentComplement("A1");

        // Others Optionals
        buynSell.setAdditionalDiscount(new BigDecimal("1.0"));
        buynSell.setCafc("170421");
        buynSell.setCardNumber("4111111111111111");
        buynSell.setCurrencyIso(1);
        buynSell.setExchangeRate(new BigDecimal("1.0"));
        buynSell.setEmailNotification("no-responder@kraken.bo");
        buynSell.setExtraMesssage("Gracias por su compra!");
        buynSell.setGiftCard(new BigDecimal("10.0"));
        buynSell.setInvalidNit(true);

        return buynSell;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }
}
