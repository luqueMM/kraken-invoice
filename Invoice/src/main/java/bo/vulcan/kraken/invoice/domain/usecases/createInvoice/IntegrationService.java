package bo.vulcan.kraken.invoice.domain.usecases.createInvoice;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.xml.datatype.DatatypeConfigurationException;

import bo.vulcan.kraken.invoice.data.DataManager;
import bo.vulcan.kraken.invoice.data.model.db.Branch;
import bo.vulcan.kraken.invoice.data.model.db.Company;
import bo.vulcan.kraken.invoice.data.model.db.CompanyDefaultsDTO;
import bo.vulcan.kraken.invoice.data.model.db.Invoice;
import bo.vulcan.kraken.invoice.data.model.db.InvoiceDetail;
import bo.vulcan.kraken.invoice.data.model.db.SalePoint;
import bo.vulcan.kraken.invoice.data.model.db.SystemStatus;
import bo.vulcan.kraken.invoice.data.model.db.UserFilter;
import bo.vulcan.kraken.invoice.data.model.enumeration.DocumentSectorType;
import bo.vulcan.kraken.invoice.data.model.enumeration.EmissionType;
import bo.vulcan.kraken.invoice.data.model.enumeration.InvoiceType;
import bo.vulcan.kraken.invoice.data.model.enumeration.NetworkState;
import bo.vulcan.kraken.invoice.data.model.errors.BadRequestException;
import bo.vulcan.kraken.invoice.data.model.errors.BranchActivityLegendNotFoundException;
import bo.vulcan.kraken.invoice.data.model.errors.CompanyDefaultNotFoundException;
import bo.vulcan.kraken.invoice.data.model.errors.DigitalSignNotFoundException;
import bo.vulcan.kraken.invoice.data.model.errors.InvoiceDetailsNegativeException;
import bo.vulcan.kraken.invoice.data.model.errors.InvoiceDetailsSubtotalException;
import bo.vulcan.kraken.invoice.data.model.errors.InvoiceDocumentSectorNotAvailableException;
import bo.vulcan.kraken.invoice.data.model.errors.MeasurementUnitNotFoundException;
import bo.vulcan.kraken.invoice.data.model.errors.PaymentMethodParameterMissingException;
import bo.vulcan.kraken.invoice.data.model.errors.ProductNotFoundException;
import bo.vulcan.kraken.invoice.data.model.errors.SiatActivityNotFoundException;
import bo.vulcan.kraken.invoice.data.model.errors.SiatPaymentMethodTypeNotFoundException;
import bo.vulcan.kraken.invoice.data.model.errors.SiatProductNotFoundException;
import bo.vulcan.kraken.invoice.data.model.request.AbstractDetailsRequest;
import bo.vulcan.kraken.invoice.data.model.request.AbstractHeaderRequest;
import bo.vulcan.kraken.invoice.data.model.request.BuyAndSellDetailsRequest;
import bo.vulcan.kraken.invoice.data.model.request.BuyAndSellRequest;
import bo.vulcan.kraken.invoice.data.model.util.RandomUtil;
import bo.vulcan.kraken.invoice.utils.DateTimeHelper;

import static bo.vulcan.kraken.invoice.data.model.errors.ErrorConstants.ID_ALREADY_EXISTS;
import static bo.vulcan.kraken.invoice.data.model.util.CompanyDefaultConstant.BRANCH_ID;
import static bo.vulcan.kraken.invoice.data.model.util.CompanyDefaultConstant.ECONOMIC_ACTIVITY_ID;

@Singleton
public class IntegrationService {

    private final DataManager mDataManager;

    private final InvoiceService invoiceService;

    @Inject
    public IntegrationService(DataManager mDataManager, InvoiceService invoiceService) {
        this.mDataManager = mDataManager;
        this.invoiceService = invoiceService;
    }

    public Invoice createInvoice(AbstractHeaderRequest request, InvoiceType invoiceTypeSent, boolean nitCheck)
            throws CompanyDefaultNotFoundException, DigitalSignNotFoundException, BranchActivityLegendNotFoundException, PaymentMethodParameterMissingException, SiatPaymentMethodTypeNotFoundException, ProductNotFoundException, SiatProductNotFoundException, MeasurementUnitNotFoundException, SiatActivityNotFoundException, InvoiceDetailsNegativeException, InvoiceDetailsSubtotalException, BadRequestException, InvoiceDocumentSectorNotAvailableException, DatatypeConfigurationException
        /*throws CuisRequestException,
            DatatypeConfigurationException, ReceiptInvoiceException, GenerateXmlArchiveException,
            VerifyCommunicationException, CufdRequestException, CountryCodeError, JsonProcessingException, NandinaError */ {
        UserFilter user = mDataManager.getUser();

        Company company = mDataManager.getCompany();

        CompanyDefaultsDTO companyAllDefaults = mDataManager.getCompanyDefaults();

        if (request.getBranchNumber() == null && companyAllDefaults != null) {
            if (request.getBranchId() == null || request.getBranchId() == 0) {
                request.setBranchId(companyAllDefaults.getBranchId());
            }
            if (request.getBranchId() == null) {
                throw new CompanyDefaultNotFoundException(BRANCH_ID);
            }
        }

        if (request.getEconomicActivityId() == null || request.getEconomicActivityId() == 0) {
            request.setEconomicActivityId(companyAllDefaults.getEconomicActivityId());
        }
        if (request.getEconomicActivityId() == null)
            throw new CompanyDefaultNotFoundException(ECONOMIC_ACTIVITY_ID);

        Branch branch = mDataManager.getBranch();

        SalePoint salePoint = mDataManager.getSalePoint();

        DateTime invoiceDate = DateTimeHelper.getCurrentLocalTime();
        InvoiceType invoiceType = invoiceTypeSent != null ? invoiceTypeSent : InvoiceType.GENERIC;
        EmissionType emissionType = EmissionType.ONLINE;

        Invoice invoice = new Invoice();

        SystemStatus systemStatus = mDataManager.getSystemStatus();
        invoice.setEmissionTypeCode(emissionType);
        if (systemStatus.getNetworkState().equals(NetworkState.OFFLINE)) {
            invoice.setEmissionTypeCode(EmissionType.OFFLINE);
        }

        if (request.getId() == null) {
            invoice.setExternalId(RandomUtil.generateLowerUuid());
        } else {
            Invoice optionalInvoice = mDataManager.invoiceRepository().findOneByExternalIdAndInvoiceTypeAndCompany_Id(request.getId(), invoiceType, company.getId());
            if (optionalInvoice != null) {
                throw new BadRequestException(ID_ALREADY_EXISTS, String.format("ID already exists id: %s, invoiceTypeId: %s", request.getId(), invoiceType));
            }
            invoice.setExternalId(request.getId());
        }

        invoice.setInvoiceType(invoiceType);

        invoice.setBillingPeriod(0);

        invoice.setAdditionalDiscount(request.getAdditionalDiscount());

        /* Fields for SIAT */
        invoice.setPaymentMethodTypeCode(request.getPaymentMethodType() == null ? null : request.getPaymentMethodType().toString());
        if (request.getCurrencyIso() != null) {
            invoice.setCurrencyCode(request.getCurrencyIso().toString());
            invoice.setExchangeRate(request.getExchangeRate());
        }

        invoice.setEmailNotification(request.getEmailNotification());
        invoice.setCardNumber(request.getCardNumber());
        invoice.setUserCode(request.getUserCode());
        if (invoice.getUserCode() == null)
            invoice.setUserCode(user.getLogin());

        invoice.setExtraMesssage(request.getExtraMesssage());

        if (request.getInvoiceNumber() != null) {
            invoice.setInvoiceNumber(request.getInvoiceNumber());
        }

        //TODO QUESTION ¿que/cuando se guarda los datos de la tabla SignificantEvent? - verificar si envia el cafc
//        SignificantEvent significantEvent = significantEventService.getInitializedSignificantEventNotError(company.getId(), branch.getId(), salePoint != null ? salePoint.getId() : null);
//        if (request.getCafc() != null && !request.getCafc().isEmpty()) {
//            invoice.setCafc(request.getCafc());
//            invoice.setEmissionDate(DateTimeHelper.toDateTime(request.getEmissionDate()));

//            if (significantEvent == null)
//                throw new SignificantEventNotFoundException();
//            if (Integer.parseInt(significantEvent.getSiatSignificativeEvent().getClassifierCode()) < 4 )
//                throw new SignificantEventNotFoundException();
//
//            if (invoice.getEmissionDate() != null) {
//                log.debug("Emission date: {}", invoice.getEmissionDate());
//                log.debug("Significant Event init date: {}", significantEvent.getInitDate());
//                if(invoice.getEmissionDate().isAfter(significantEvent.getInitDate()))
//                    log.debug("Fecha de emision is after: {}", significantEvent.getInitDate());
//                if (invoice.getEmissionDate().isBefore(ZonedDateTime.now()))
//                    log.debug("Fecha de emision is before: {} ", ZonedDateTime.now());
//                if (!(invoice.getEmissionDate().isAfter(significantEvent.getInitDate())) || !(invoice.getEmissionDate().isBefore(ZonedDateTime.now())))
//                    throw new InvalidDateRangeExpressionException();
//            }
//        } else {
//            if (significantEvent != null) {
//                if (Integer.parseInt(significantEvent.getSiatSignificativeEvent().getClassifierCode()) > 4)
//                    throw new CafcRequiredException();
//            }
//
//        }

        DocumentSectorType documentSectorType = null;
        List<AbstractDetailsRequest> detailsRequests = request.getDetails();
        if (request instanceof BuyAndSellRequest) {
            invoice.setDocumentTypeCode(((BuyAndSellRequest) request).getDocumentTypeCode().toString());
            invoice.setDocumentNumber(((BuyAndSellRequest) request).getDocumentNumber());
            invoice.setInvoiceName(((BuyAndSellRequest) request).getName());
            invoice.setDocumentComplement(((BuyAndSellRequest) request).getDocumentComplement());
            if (((BuyAndSellRequest) request).getInvalidNit() != null) {
                if (((BuyAndSellRequest) request).getInvalidNit())
                    invoice.setExceptionCode(1);
                else
                    invoice.setExceptionCode(0);
            }
            invoice.setExtraCustomerAddress(((BuyAndSellRequest) request).getExtraCustomerAddress());
            invoice.setGiftCardAmount(((BuyAndSellRequest) request).getGiftCard());
            //TODO jaspersoft enum
            invoice.setInvoiceTemplate(JasperFileConstants.BUY_AND_SELL_INVOICE_TEMPLATE_VERSION);
            documentSectorType = DocumentSectorType.BUY_AND_SELL_INVOICE;
        } //else if (request instanceof ProductReachedByIceRequest) {
        //TODO ADD ICE
        //}

        if (request.getCustomerCode() != null) {
            invoice.setCustomerCode(request.getCustomerCode());
        } else {
            invoice.setCustomerCode(invoice.getDocumentNumber() + company.getId().toString());
        }

        if (documentSectorType != null)
            invoice.setDocumentSectorCode(documentSectorType.code.toString());

        Set<InvoiceDetail> details = new HashSet<>();

        for (AbstractDetailsRequest detail : detailsRequests) {
            InvoiceDetail item = new InvoiceDetail();
            item.setQuantity(detail.getQuantity());
            item.setConcept(detail.getConcept());
            item.setUnitPrice(detail.getUnitPrice());
            item.setSequence(detail.getSequence());
            item.setSubtotal(detail.getSubtotal());
            item.setDiscountAmount(detail.getDiscountAmount() != null ? detail.getDiscountAmount() : BigDecimal.ZERO);
            if (detail.getProductCode() != null) {
                item.setProductCode(detail.getProductCode());
            }
            if (detail instanceof BuyAndSellDetailsRequest) {
                item.setSeriesNumber(((BuyAndSellDetailsRequest) detail).getSeriesNumber());
                item.setImeiNumber(((BuyAndSellDetailsRequest) detail).getImeiNumber());
            } //TODO ADD ICE
             /*else if (detail instanceof ProductReachedByIceDetailsRequest) {
                item.setMarkIce(((ProductReachedByIceDetailsRequest) detail).getMarkIce());
                if (item.getMarkIce() == 1) {
                    item.setAliquotSpecify(((ProductReachedByIceDetailsRequest) detail).getAliquotSpecify());
                    item.setAliquotPercentage(((ProductReachedByIceDetailsRequest) detail).getAliquotPercentage());
                    item.setQuantityIce(((ProductReachedByIceDetailsRequest) detail).getQuantityIce());
                }
            } */
            details.add(item);
        }
        invoice.setDetails(details);

        invoice = invoiceService.createInvoice(
                invoice,
                company,
                branch,
                salePoint,
                invoiceDate,
                request.getEconomicActivityId(),
                companyAllDefaults,
                documentSectorType,
                nitCheck
        );

        return invoice;
    }
}
