package bo.vulcan.kraken.invoice.data.model.db;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bo.vulcan.kraken.invoice.data.model.enumeration.InvoiceReturnType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class InvoicePdf {

    private String invoiceReturnType;
    private String invoiceTemplate;
    private String invoiceType;
    private String userCode;
    private Long id;
    private String externalId;
    private String companyPathLogo;
    private String companyNit;
    private String companyName;
    private String branchCountry;
    private String branchCity;
    private String branchName;
    private String branchAddress;
    private String branchPhone;
    private Integer branchNumber;

    private String invoiceState;
    private String controlCode;
    private Long invoiceNumber;
    private Date emissionDate;
    private String nitCustomer;
    private String socialReason;
    private String documentComplement;
    private String customerAddress;
    private String customerCode;


    private BigDecimal subtotal;
    private BigDecimal totalAmount;
    private BigDecimal totalAmountWithIva;
    private BigDecimal additionalDiscount;
    private BigDecimal giftCardAmount;
    private String totalLiteral;
    private String legend;
    private String legend2;
    private String extraMesssage;
    private String qrcodeString;

    private BigDecimal totalAmountFinancialLease;
    private BigDecimal amountIceSpecific;
    private BigDecimal amountIcePercentage;

    private String studentName;
    private String invoicedPeriod;

    private String currency;
    private String operation;

    private BigDecimal incomeDifferenceChange;
    private BigDecimal officialExchangeRate;

    private BigDecimal totalAmountCurrency;
    private BigDecimal exchangeRate;
    private String totalLiteralCurrency;

    private String incoterm;
    private String incotermDetail;
    private String destinationPort;
    private String destinationPlace;
    private String costsNationalExpenses;
    private BigDecimal totalNationalFobExpenses;
    private String costsInternationalExpenses;
    private BigDecimal totalInternationalExpenses;
    private BigDecimal amountDetail;
    private String numberDescriptionPackages;
    private String additionalInformation;

    private boolean syncPending;

    private List<InvoicePdfDetails> details;

    public InvoicePdf(Invoice invoice, String legend2, String pathImage, String currency, String totalLiteralCurrency, Company company) {
        this.invoiceReturnType = company.getInvoiceReturnType() != null ? company.getInvoiceReturnType().name() : InvoiceReturnType.INVOICE.name();
        this.invoiceTemplate = invoice.getInvoiceTemplate();
        this.invoiceType = invoice.getInvoiceType().name;
        this.userCode = invoice.getUserCode();
        this.id = invoice.getId();
        this.externalId = invoice.getExternalId();
        this.companyPathLogo = pathImage;
        this.companyNit = invoice.getCompanyNit();
        this.companyName = invoice.getCompanyName();
        this.branchCountry = invoice.getBranchCountry();
        this.branchCity = invoice.getBranchCity();
        this.branchName = invoice.getBranchName();
        this.branchAddress = invoice.getBranchAddress();
        this.branchPhone = invoice.getBranchPhone();
        this.branchNumber = invoice.getBranchNumber();
        this.invoiceState = String.valueOf(invoice.getInvoiceState().code);
        this.controlCode = invoice.getControlCode();
        this.invoiceNumber = invoice.getInvoiceNumber();
        this.emissionDate = invoice.getEmissionDate().toDate();
        this.nitCustomer = invoice.getDocumentNumber();
        this.socialReason = invoice.getInvoiceName();
        this.documentComplement = invoice.getDocumentComplement();
        this.customerAddress = invoice.getExtraCustomerAddress();
        this.customerCode = invoice.getCustomerCode();
        this.subtotal = invoice.getSubtotal() != null ? invoice.getSubtotal() : BigDecimal.ZERO;
        this.totalAmount = invoice.getTotalAmount() != null ? invoice.getTotalAmount() : BigDecimal.ZERO;
        this.totalAmountWithIva = invoice.getTotalAmountWithIva() != null ? invoice.getTotalAmountWithIva() : BigDecimal.ZERO;
        this.additionalDiscount = invoice.getAdditionalDiscount() != null ? invoice.getAdditionalDiscount() : BigDecimal.ZERO;
        this.giftCardAmount = invoice.getGiftCardAmount() != null ? invoice.getGiftCardAmount() : BigDecimal.ZERO;
        this.totalLiteral = invoice.getTotalLiteral();
        this.legend = invoice.getLegend();
        this.legend2 = legend2;
        this.extraMesssage = invoice.getExtraMesssage();
        this.qrcodeString = invoice.getQrcodeString();
        this.totalAmountFinancialLease = invoice.getTotalAmountFinancialLease() != null ? invoice.getTotalAmountFinancialLease() : BigDecimal.ZERO;
        this.amountIceSpecific = invoice.getAmountIceSpecific() != null ? invoice.getAmountIceSpecific() : BigDecimal.ZERO;
        this.amountIcePercentage = invoice.getAmountIcePercentage()!= null ? invoice.getAmountIcePercentage() : BigDecimal.ZERO;
        this.studentName = invoice.getStudentName();
        this.invoicedPeriod = invoice.getInvoicedPeriod();
        this.currency = currency;
        this.operation = invoice.getCodeTypeOperation() != null ? (invoice.getCodeTypeOperation() == 1 ? "VENTA" : "COMPRA" ) : "";
        this.incomeDifferenceChange = invoice.getIncomeDifferenceChange()!= null ? invoice.getIncomeDifferenceChange() : BigDecimal.ZERO;
        this.officialExchangeRate = invoice.getOfficialExchangeRate()!= null ? invoice.getOfficialExchangeRate() : BigDecimal.ZERO;
        this.totalAmountCurrency = invoice.getTotalAmountCurrency()!= null ? invoice.getTotalAmountCurrency() : BigDecimal.ZERO;
        this.exchangeRate = invoice.getExchangeRate()!= null ? invoice.getExchangeRate() : BigDecimal.ZERO;
        this.totalLiteralCurrency = totalLiteralCurrency;
        this.incoterm = invoice.getIncoterm();
        this.incotermDetail = invoice.getIncotermDetail();
        this.destinationPort = invoice.getDestinationPort();
        this.destinationPlace = invoice.getDestinationPlace();
        this.costsNationalExpenses = invoice.getCostsNationalExpenses();
        this.totalNationalFobExpenses = invoice.getTotalNationalFobExpenses()!= null ? invoice.getTotalNationalFobExpenses() : BigDecimal.ZERO;
        this.costsInternationalExpenses = invoice.getCostsInternationalExpenses();
        this.totalInternationalExpenses = invoice.getTotalInternationalExpenses()!= null ? invoice.getTotalInternationalExpenses() : BigDecimal.ZERO;
        this.amountDetail = invoice.getAmountDetail()!= null ? invoice.getAmountDetail() : BigDecimal.ZERO;
        this.numberDescriptionPackages = invoice.getNumberDescriptionPackages();
        this.additionalInformation = invoice.getAdditionalInformation();

        this.syncPending = !invoice.isSync();

        List<InvoicePdfDetails> _details = new ArrayList<>();

        for(InvoiceDetail item : invoice.getDetails()){
            _details.add(new InvoicePdfDetails(
                    item.getId(),
                    item.getQuantity(),
                    item.getConcept(),
                    item.getUnitPrice(),
                    item.getSubtotal(),
                    item.getSequence(),
                    item.getProductCode(),
                    item.getDiscountAmount(),
                    item.getMeasurementUnit(),
                    item.getAmountIceSpecific(),
                    item.getAmountIcePercentage(),
                    item.getNandinaCode()
            ));
        }
        this.details = _details;
    }

    public boolean isSyncPending() {
        return syncPending;
    }

}
