package bo.vulcan.kraken.invoice.domain.usecases.createInvoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import bo.vulcan.kraken.invoice.BuildConfig;
import bo.vulcan.kraken.invoice.data.DataManager;
import bo.vulcan.kraken.invoice.data.model.db.Invoice;
import bo.vulcan.kraken.invoice.data.model.db.InvoiceDetail;
import bo.vulcan.kraken.invoice.data.model.db.SiatCurrencyType;
import bo.vulcan.kraken.invoice.data.model.db.SiatDocumentType;
import bo.vulcan.kraken.invoice.data.model.db.SiatPaymentMethodType;
import bo.vulcan.kraken.invoice.data.model.dto.RemoveNegativeDetailsResult;
import bo.vulcan.kraken.invoice.data.model.enumeration.DocumentSectorType;
import bo.vulcan.kraken.invoice.data.model.enumeration.Environment;
import bo.vulcan.kraken.invoice.data.model.enumeration.InvoiceSize;
import bo.vulcan.kraken.invoice.data.model.errors.InvoiceCurrencyFieldsException;
import bo.vulcan.kraken.invoice.data.model.errors.PaymentMethodParameterMissingException;
import bo.vulcan.kraken.invoice.data.model.errors.SiatCurrencyNotFoundException;
import bo.vulcan.kraken.invoice.data.model.errors.SiatDocumentTypeNotFoundException;
import bo.vulcan.kraken.invoice.data.model.errors.SiatPaymentMethodTypeNotFoundException;
import io.reactivex.Single;

@Singleton
public class InvoiceUtilService {

    private final DataManager mDataManager;

    @Inject
    public InvoiceUtilService(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    public String generateQrString(Environment environment, Long nit, String cuf, Long invoiceNumber, InvoiceSize size) {

        String qrPath;
        if (environment.equals(Environment.PRODUCTION)) {
            qrPath = BuildConfig.SIAT_API_PROD_qr;
        } else {
            qrPath = BuildConfig.SIAT_API_TEST_qr;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(qrPath)
                .append("?nit=").append(nit.toString())
                .append("&cuf=").append(cuf)
                .append("&numero=").append(invoiceNumber.toString())
                .append("&t=").append(size.getValue());

        return builder.toString();
    }


    // TODO Question solo para aclarar, ¿El currencyCode solo se envia cuando la moneda es diferente de Bs?
    // verifica si el existe en db el currencyCode
    public Single<SiatCurrencyType> validateCurrencyFields(String currencyCode, BigDecimal exchangeRate) {
        final Single<Boolean> single = Single.create(emitter -> {
            if (currencyCode != null && exchangeRate != null) {
                emitter.onSuccess(true);
            } else if (currencyCode != null || exchangeRate != null) {
                emitter.onError(new InvoiceCurrencyFieldsException());
            } else {
                emitter.onSuccess(false);
            }
        });

        return single.flatMap(response -> {
            if (response) {
                SiatCurrencyType siatCurrencyType = mDataManager.siatCurrencyTypeRepository().findFirstByClassifierCode(currencyCode);
                if (siatCurrencyType == null) {
                    return Single.just(siatCurrencyType);
                } else {
                    return Single.error(new SiatCurrencyNotFoundException());
                }
            } else {
                return Single.just(new SiatCurrencyType());
            }
        });
    }

    public Single<SiatDocumentType> validateDocumentTypeField(String documentTypeCode) {
        if (documentTypeCode != null) {
            return mDataManager.siatDocumentTypeGet(documentTypeCode)
                    .onErrorResumeNext((Throwable e) -> Single.error(new SiatDocumentTypeNotFoundException()));
        } else {
            return Single.error(new SiatDocumentTypeNotFoundException());
        }
    }

    public Single<SiatPaymentMethodType> validatePaymentMethodTypeField(String paymentMethodTypeCode) {
        if (paymentMethodTypeCode != null) {
            SiatPaymentMethodType siatPaymentMethod = mDataManager.siatPaymentMethodType().findFirstByClassifierCode(paymentMethodTypeCode);
            if (siatPaymentMethod != null) {
                return Single.just(siatPaymentMethod);
            } else {
                return Single.error(new SiatPaymentMethodTypeNotFoundException());
            }
        } else {
            return Single.just(new SiatPaymentMethodType());
        }
    }

    public void validatePaymentMethodParameters(Invoice invoice) throws PaymentMethodParameterMissingException, SiatPaymentMethodTypeNotFoundException {
        if (invoice.getPaymentMethodTypeCode().equals("2") && (invoice.getCardNumber() == null || invoice.getCardNumber().isEmpty())) {
            throw new PaymentMethodParameterMissingException("cardNumber");
        }

        SiatPaymentMethodType siatPaymentMethod = mDataManager.siatPaymentMethodType().findFirstByClassifierCode(invoice.getPaymentMethodTypeCode());

        if (siatPaymentMethod == null) {
            throw new SiatPaymentMethodTypeNotFoundException();
        }

        if (siatPaymentMethod.getDescription().toLowerCase().contains("gift-card")) {
            if ((invoice.getGiftCardAmount() == null || invoice.getGiftCardAmount().compareTo(BigDecimal.ZERO) == 0)) {
                throw new PaymentMethodParameterMissingException("giftCard");
            }
            DocumentSectorType documentSectorType = DocumentSectorType.getEnumFromInteger(Integer.parseInt(invoice.getDocumentSectorCode()));
            if (
                    !(
                            documentSectorType.equals(DocumentSectorType.BUY_AND_SELL_INVOICE) ||
                                    documentSectorType.equals(DocumentSectorType.EDUCATIONAL_SECTORS_INVOICE) ||
                                    documentSectorType.equals(DocumentSectorType.TELECOMMUNICATIONS_INVOICE)
                    )
            ) {
                throw new PaymentMethodParameterMissingException("giftCard");
            }
        }
    }

    public RemoveNegativeDetailsResult getNegativeAmounts(ArrayList<InvoiceDetail> detailsList) {
        RemoveNegativeDetailsResult result = new RemoveNegativeDetailsResult();
        ArrayList<InvoiceDetail> detailsListFiltered = new ArrayList<>();

        for (InvoiceDetail d : detailsList) {
            if (d.getSubtotal().compareTo(BigDecimal.ZERO) == -1) {
                detailsListFiltered.add(d);
            }
        }

        for (InvoiceDetail detail : detailsListFiltered) {
            BigDecimal calculated = detail.getQuantity().multiply(detail.getUnitPrice());
            if (detail.getDiscountAmount() != null) {
                calculated = calculated.subtract(detail.getDiscountAmount());
            }
            if (calculated.compareTo(detail.getSubtotal()) != 0) {
                result.setHasError(true);
            }
            result.setTotalNegative(result.getTotalNegative().add(detail.getSubtotal()));
        }

        List<InvoiceDetail> positive = new ArrayList<>();
        for (InvoiceDetail d : detailsList) {
            if (d.getSubtotal().compareTo(BigDecimal.ZERO) == 1) {
                positive.add(d);
            }
        }

        result.setList(positive);
        result.setTotalNegative(result.getTotalNegative().negate());
        return result;
    }
}
