package bo.vulcan.kraken.invoice.domain.usecases;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import bo.vulcan.kraken.invoice.data.DataManager;
import bo.vulcan.kraken.invoice.data.model.db.Company;
import bo.vulcan.kraken.invoice.data.model.db.Invoice;
import bo.vulcan.kraken.invoice.data.model.db.InvoiceDetail;
import bo.vulcan.kraken.invoice.data.model.db.InvoicePdf;
import bo.vulcan.kraken.invoice.data.model.db.SiatCurrencyType;
import bo.vulcan.kraken.invoice.data.model.db.relations.InvoiceAndInvoiceDetail;
import bo.vulcan.kraken.invoice.data.model.enumeration.EmissionType;
import bo.vulcan.kraken.invoice.data.model.enumeration.InvoiceType;
import bo.vulcan.kraken.invoice.data.model.request.BuyAndSellRequest;
import bo.vulcan.kraken.invoice.data.model.response.IntegrationResponse;
import bo.vulcan.kraken.invoice.data.model.util.NumberToLetterService;
import bo.vulcan.kraken.invoice.domain.usecases.createInvoice.IntegrationService;
import bo.vulcan.kraken.invoice.domain.usecases.createInvoice.InvoiceUtilService;
import bo.vulcan.kraken.invoice.utils.AppConstants;
import bo.vulcan.kraken.invoice.utils.ErrorsManager;
import io.reactivex.Single;

@Singleton
public class InvoiceUseCase {


    private final DataManager mDataManager;

    private final InvoiceUtilService invoiceUtilService;

    private final IntegrationService integrationService;

    @Inject
    public InvoiceUseCase(DataManager mDataManager, InvoiceUtilService invoiceUtilService, IntegrationService integrationService) {
        this.mDataManager = mDataManager;
        this.invoiceUtilService = invoiceUtilService;
        this.integrationService = integrationService;
    }

    public Single<Invoice> createBuyAndSell(BuyAndSellRequest request, InvoiceType invoiceType){
        return ErrorsManager.startValidate(request.validate())
                .flatMap(response -> invoiceUtilService.validateCurrencyFields(request.getCurrencyIso() != null ? request.getCurrencyIso().toString() : null, request.getExchangeRate()))
                .flatMap(response -> invoiceUtilService.validateDocumentTypeField(request.getDocumentTypeCode().toString()))
                .flatMap(response -> invoiceUtilService.validatePaymentMethodTypeField(request.getPaymentMethodType().toString()))
                //TODO validateDocumentTypeCode
                .map(response -> {
                    //TODO Modify response
                    Invoice invoice = integrationService.createInvoice(request, invoiceType, false);

                    return invoice;
                });
    }

    public Single<IntegrationResponse> createProductReachedByIce(){
        //TODO add sync service
        return null;
        //return this.mDataManager.create();
    }

    public Single<List<InvoicePdf>> getInvoices() {
        return mDataManager.invoiceRepository().getInvoiceWithInvoiceDetailList()
                .map(invoices -> {
                    Company company = mDataManager.getCompany();
                    List<InvoicePdf> _invoices = new ArrayList<>();

                    for(InvoiceAndInvoiceDetail invoice: invoices){
                        _invoices.add(toInvoicePDF(invoice, company));
                    }
                    return _invoices;
                });
    }

    public Single<InvoicePdf> getInvoiceById(Long id) {
        return mDataManager.invoiceRepository().getInvoiceWithInvoiceDetail(id)
                .map(invoice -> {
                    Company company = mDataManager.getCompany();
                    return toInvoicePDF(invoice, company);
                });
    }

    private InvoicePdf toInvoicePDF(InvoiceAndInvoiceDetail invoice, Company company){

        String legend2 = invoice.getInvoice().getEmissionDate().equals(EmissionType.OFFLINE) ? AppConstants.siatLegendOffline : AppConstants.siatLegendOnline;
        SiatCurrencyType currencyType = mDataManager.siatCurrencyTypeRepository().findFirstByClassifierCode(invoice.getInvoice().getCurrencyCode());
        String currency = currencyType == null ? "" : (currencyType.description == null ? "" : currencyType.description);
        if(invoice.getInvoice().getDetails() == null || invoice.getInvoice().getDetails().isEmpty()){
            invoice.getInvoice().setDetails(new HashSet<>(invoice.getInvoiceDetails()));
        }

        return new InvoicePdf(invoice.getInvoice(), legend2, company.getImageName(), currency,
                NumberToLetterService.convert(invoice.getInvoice().getTotalAmountCurrency(), currency, true), company);
    }
}
