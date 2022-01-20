package bo.vulcan.kraken.invoice.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import bo.vulcan.kraken.invoice.data.model.enumeration.EmissionType;
import bo.vulcan.kraken.invoice.data.model.enumeration.Environment;
import bo.vulcan.kraken.invoice.data.model.enumeration.InvoiceState;
import bo.vulcan.kraken.invoice.data.model.enumeration.InvoiceType;
import bo.vulcan.kraken.invoice.data.model.enumeration.Mode;
import bo.vulcan.kraken.invoice.data.model.enumeration.VoucherState;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity(tableName = "invoice")
@Getter @Setter @ToString
public class Invoice extends AbstractAuditingEntity{

    @androidx.annotation.NonNull
    @Expose
    @PrimaryKey
    @SerializedName("localId")
    @ColumnInfo(name = "local_id")
    private String localId;

    @NonNull
    @Expose
    @SerializedName("sync")
    @ColumnInfo(name = "sync")
    private boolean sync;

    @Expose
    @SerializedName("id")
    @ColumnInfo(name = "id")
    private Long id;

    //Invoice Data

    /**
     * Set request.invoiceNumber
     */
//    @Min(value = 0L)
//    @Max(value = 9999999999L)
    @NonNull
    @Expose
    @SerializedName("invoiceNumber")
    @ColumnInfo(name = "invoice_number")
    private Long invoiceNumber;

    //TODO add converter
    @NonNull
    @Expose
    @SerializedName("invoiceDate")
    @ColumnInfo(name = "invoice_date")
    private LocalDate invoiceDate;

    //TODO add converter
    @NonNull
    @Expose
    @SerializedName("emissionDate")
    @ColumnInfo(name = "emission_date")
    private DateTime emissionDate;

    @Expose
    @SerializedName("invoiceState")
    @ColumnInfo(name = "invoice_state")
    private InvoiceState invoiceState;

    //    @Size(max = 512)
    @Expose
    @SerializedName("cancellationReason")
    @ColumnInfo(name = "cancellation_reason")
    private String cancellationReason;

    //    @Size(min = 1, max = 100)
    @NonNull
    @Expose
    @SerializedName("cufdCode")
    @ColumnInfo(name = "cufd_code")
    private String cufdCode;

    @NonNull
//    @Size(min = 1, max = 100)
    @Expose
    @SerializedName("cuisCode")
    @ColumnInfo(name = "cuis_code")
    private String cuisCode;

    //    @Size(max = 512)
    @NonNull
    @Expose
    @SerializedName("legend")
    @ColumnInfo(name = "legend")
    private String legend;

    /**
     * Set documentSectorType.code
     */
    @NonNull
    @Expose
    @SerializedName("documentSectorCode")
    @ColumnInfo(name = "document_sector_code")
    private String documentSectorCode;

    /**
     * Set request.userCode
     * Si es nulo sera = al login del usuario logueado
     */
//    @Size(min = 1, max = 100)
    @NonNull
    @Expose
    @SerializedName("userCode")
    @ColumnInfo(name = "user_code")
    private String userCode;

    //Sale Data

    /**
     * Set request.paymentMethodType.toString
     */
    @NonNull
    @Expose
    @SerializedName("paymentMethodTypeCode")
    @ColumnInfo(name = "payment_method_type_code")
    private String paymentMethodTypeCode;

    /**
     * Set request.cardNumber
     */
//    @Size(max = 30)
    @Expose
    @SerializedName("cardNumber")
    @ColumnInfo(name = "card_number")
    private String cardNumber;

    //    @DecimalMin(value = "0.01")
//    @DecimalMax(value = "999999999"), precision = 20, scale = 2
    @NonNull
    @Expose
    @SerializedName("totalAmount")
    @ColumnInfo(name = "total_amount")
    private BigDecimal totalAmount;

    //    @DecimalMin(value = "0.00")
//    @DecimalMax(value = "999999999"), precision = 20, scale = 2
    @NonNull
    @Expose
    @SerializedName("totalAmountWithIva")
    @ColumnInfo(name = "total_amount_with_iva")
    private BigDecimal totalAmountWithIva;

    //    @DecimalMin(value = "0.01")
//    @DecimalMax(value = "999999999"), precision = 20, scale = 2
    @NonNull
    @Expose
    @SerializedName("totalAmountCurrency")
    @ColumnInfo(name = "total_amount_currency")
    private BigDecimal totalAmountCurrency;

    /**
     * Set request.currencyIso.tostring
     */
    @NonNull
    @Expose
    @SerializedName("currencyCode")
    @ColumnInfo(name = "currency_code")
    private String currencyCode;

    /**
     * Set request.exchangeRate
     */
    //, precision = 20, scale = 5
    @NonNull
    @Expose
    @SerializedName("exchangeRate")
    @ColumnInfo(name = "exchange_rate")
    private BigDecimal exchangeRate;

    //Company Data

    //    @Size(min = 1, max = 13)
    @NonNull
    @Expose
    @SerializedName("companyNit")
    @ColumnInfo(name = "company_nit")
    private String companyNit;

    //    @Size(max = 256)
    @NonNull
    @Expose
    @SerializedName("companyName")
    @ColumnInfo(name = "company_name")
    private String companyName;

    //    @Size(max = 256)
    @Expose
    @SerializedName("companyNameLogo")
    @ColumnInfo(name = "company_name_logo")
    private String companyNameLogo;

    //    @Size(max = 256)
    @Expose
    @SerializedName("qrcodeString")
    @ColumnInfo(name = "qrcode_string")
    private String qrcodeString;

    //Branch Data

    //    @Size(max = 256)
    @NonNull
    @Expose
    @SerializedName("branchName")
    @ColumnInfo(name = "branch_name")
    private String branchName;

    @NonNull
    @Expose
    @SerializedName("BranchNumber")
    @ColumnInfo(name = "branch_number")
    private Integer branchNumber;

    //    @Size(min = 1, max = 500)
    @NonNull
    @Expose
    @SerializedName("branchAddress")
    @ColumnInfo(name = "branch_address")
    private String branchAddress;

    //    @Size(min = 1, max = 25)
    @NonNull
    @Expose
    @SerializedName("branchPhone")
    @ColumnInfo(name = "branch_phone")
    private String branchPhone;

    //    @Size(max = 256)
    @NonNull
    @Expose
    @SerializedName("branchCountry")
    @ColumnInfo(name = "branch_country")
    private String branchCountry;

    //    @Size(max = 256)
    @NonNull
    @Expose
    @SerializedName("branchCity")
    @ColumnInfo(name = "branch_city")
    private String branchCity;

    //Sale Point Data

    @Expose
    @SerializedName("salePointCode")
    @ColumnInfo(name = "sale_point_code")
    private Integer salePointCode;

    //Customer Data
    /**
     * Set request.name
     */
//    @Size(min = 1, max = 500)
    @NonNull
    @Expose
    @SerializedName("invoiceName")
    @ColumnInfo(name = "invoice_name")
    private String invoiceName;

    /**
     * if es null ? Set invoice.documentNumber+company.id : Set request.customerCode
     */
//    @Size(min = 1, max = 100)
    @NonNull
    @Expose
    @SerializedName("customerCode")
    @ColumnInfo(name = "customer_code")
    private String customerCode;

    /**
     * Set request.documentTypeCode.toString
     */
    @Expose
    @SerializedName("documentTypeCode")
    @ColumnInfo(name = "document_type_code")
    private String documentTypeCode;

    /**
     * Set request.documentNumber
     */
//    @Size(min = 1, max = 20)
    @NonNull
    @Expose
    @SerializedName("documentNumber")
    @ColumnInfo(name = "document_number")
    private String documentNumber;

    /**
     * Set request.documentComplement
     */
//    @Size(min = 1, max = 5)
    @Expose
    @SerializedName("documentComplement")
    @ColumnInfo(name = "document_complement")
    private String documentComplement;

    //Critical Data

    /**
     * Set request.id
     * Si es nulo se genera UUID
     */
//    @Size(max = 256)
    @NonNull
    @Expose
    @SerializedName("externalId")
    @ColumnInfo(name = "external_id")
    private String externalId;

    /**
     * Set invoiceType
     * Si es nulo sera InvoiceType.GENERIC
     */
    @NonNull
    @Expose
    @SerializedName("invoiceType")
    @ColumnInfo(name = "invoice_type")
    private InvoiceType invoiceType;

    //Basic Data

    /**
     * Set 0
     */
    @NonNull
    @Expose
    @SerializedName("billingPeriod")
    @ColumnInfo(name = "billing_period")
    private Integer billingPeriod; //TODO QUESTION ¿que es?


    //    , length = 256
    @Expose
    @SerializedName("voucherState")
    @ColumnInfo(name = "voucher_state")
    private VoucherState voucherState;

    /**
     * Set request.emailNotification
     */
//    @Size(max = 256)
    @Expose
    @SerializedName("emailNotification")
    @ColumnInfo(name = "email_notification")
    private String emailNotification;

    //    @Size(max = 256)
    @Expose
    @SerializedName("phoneNumberNotification")
    @ColumnInfo(name = "phone_number_notification")
    private String phoneNumberNotification;

    //    @Size(max = 256)
    @Expose
    @SerializedName("personType")
    @ColumnInfo(name = "person_type")
    private String personType;

    //Extras Data

    /**
     * Set request.extraCustomerAddress
     */
//    @Size(min = 1, max = 500)
    @Expose
    @SerializedName("extraCustomerAddress")
    @ColumnInfo(name = "extra_customer_address")
    private String extraCustomerAddress;

    //Invoice Data
    /**
     * guarda jasperTemplate Ej: JasperFileConstants.BUY_AND_SELL_INVOICE_TEMPLATE_VERSION
     */
//    @Size(max = 256)
//    @NonNull
    @Expose
    @SerializedName("invoiceTemplate")
    @ColumnInfo(name = "invoice_template")
    private String invoiceTemplate;

    @Expose
    @SerializedName("cancellationDate")
    @ColumnInfo(name = "cancellation_date")
    private Instant cancellationDate;

    //Totals Data

    //, precision = 20, scale = 2
    @NonNull
    @Expose
    @SerializedName("subtotal")
    @ColumnInfo(name = "subtotal")
    private BigDecimal subtotal;

    //    @Size(max = 256)
    @NonNull
    @Expose
    @SerializedName("cuf")
    @ColumnInfo(name = "cuf")
    private String controlCode;

    //Another Data

    //    @Size(max = 512)
    @NonNull
    @Expose
    @SerializedName("totalLiteral")
    @ColumnInfo(name = "total_literal")
    private String totalLiteral;

    /**
     * Set request.extraMessage
     */
//    @Size(max = 256)
    @Expose
    @SerializedName("extraMesssage")
    @ColumnInfo(name = "extra_messsage")
    private String extraMesssage;

    @NonNull
    @Expose
    @SerializedName("emissionTypeCode")
    @ColumnInfo(name = "emission_type_code")
    private EmissionType emissionTypeCode;

    //    @Size(max = 30)
    @NonNull
    @Expose
    @SerializedName("invoiceTypeCode")
    @ColumnInfo(name = "invoice_type_code")
    private String invoiceTypeCode;

    //Cancellation
    @Expose
    @SerializedName("cancelReasonCode")
    @ColumnInfo(name = "cancel_reason_code")
    private String cancelReasonCode;

    @Expose
    @SerializedName("jhiLocked")
    @ColumnInfo(name = "jhi_locked")
    private Boolean locked = false;

    @Expose
    @SerializedName("environment")
    @ColumnInfo(name = "environment")
    private Environment environment;

    //    @Size(max = 256)
    @Expose
    @SerializedName("systemCode")
    @ColumnInfo(name = "system_code")
    private String systemCode;

    @Expose
    @SerializedName("jhiMode")
    @ColumnInfo(name = "jhi_mode")
    private Mode mode;

    @Expose
    @SerializedName("fileNumber")
    @ColumnInfo(name = "file_number")
    private Integer fileNumber;

    //, length = 512
    @Expose
    @SerializedName("receptionCode")
    @ColumnInfo(name = "reception_code")
    private String receptionCode;

    //, length = 1024
    @Expose
    @SerializedName("messageReception")
    @ColumnInfo(name = "message_reception")
    private String messageReception;

    //, length = 512
    @Expose
    @SerializedName("validationCode")
    @ColumnInfo(name = "validation_code")
    private String validationCode;

    //, length = 1024
    @Expose
    @SerializedName("messageValidation")
    @ColumnInfo(name = "message_validation")
    private String messageValidation;

    //    @Size(max = 512)
    @Expose
    @SerializedName("economicActivityDescription")
    @ColumnInfo(name = "economic_activity_description")
    private String economicActivityDescription;

    @Expose
    @SerializedName("significantEventId")
    @ColumnInfo(name = "significant_event_id")
    private Long significantEventId;

    //    @Size(max = 255)
    @Expose
    @SerializedName("segment")
    @ColumnInfo(name = "segment")
    private String segment;

    // new data sin
    /**
     * Set request.giftCard
     */
    //, precision = 20, scale = 2
    @Expose
    @SerializedName("giftCardAmount")
    @ColumnInfo(name = "gift_card_amount")
    private BigDecimal giftCardAmount;

    /**
     * Set request.additionalDiscount
     */
    //, precision = 20, scale = 2
    @Expose
    @SerializedName("additionalDiscount")
    @ColumnInfo(name = "additional_discount")
    private BigDecimal additionalDiscount;

    @Expose
    @SerializedName("serverOrigin")
    @ColumnInfo(name = "server_origin")
    private Integer serverOrigin;

    /**
     * if request.invalidNit? 1 : 0
     * Si no se envia se guarda como null
     */
//    @Min(value = 0)
//    @Max(value = 1)
    @NonNull
    @Expose
    @SerializedName("exceptionCode")
    @ColumnInfo(name = "exception_code")
    private Integer exceptionCode;

    //    @Size(min = 1, max = 50) , length = 255
    @Expose
    @SerializedName("cafc")
    @ColumnInfo(name = "cafc")
    private String cafc;


    // telecommunication
//    @Min(value = 0)
//    @Max(value = 9999999999999L)
    @Expose
    @SerializedName("jointNit")
    @ColumnInfo(name = "joint_nit")
    private Long jointNit;

    // financial entity
//    @DecimalMin(value = "0")
//    @DecimalMax(value = "999999999"), precision = 20, scale = 2
    @Expose
    @SerializedName("totalAmountFinancialLease")
    @ColumnInfo(name = "total_amount_financial_lease")
    private BigDecimal totalAmountFinancialLease;

    // factura electronica alcanzada ice
//    @DecimalMin(value = "0")
//    @DecimalMax(value = "999999999"), precision = 20, scale = 2
    @Expose
    @SerializedName("amountIceSpecific")
    @ColumnInfo(name = "amount_ice_specific")
    private BigDecimal amountIceSpecific; //se debe calcular

    //    @DecimalMin(value = "0")
//    @DecimalMax(value = "999999999"), precision = 20, scale = 2
    @Expose
    @SerializedName("amountIcePercentage")
    @ColumnInfo(name = "amount_ice_percentage")
    private BigDecimal amountIcePercentage;  // se debe calcular

    // factura sectores educativos
//    @Size(min = 1, max = 200)
    @Expose
    @SerializedName("studentName")
    @ColumnInfo(name = "student_name")
    private String studentName;

    //    @Size(min = 1, max = 50), length = 255
    @Expose
    @SerializedName("invoicedPeriod")
    @ColumnInfo(name = "invoiced_period")
    private String invoicedPeriod;

    // compra venta moneda extranjera
//    @Min(value = 1)
//    @Max(value = 2)
    @Expose
    @SerializedName("codeTypeOperation")
    @ColumnInfo(name = "code_type_operation")
    private Integer codeTypeOperation;

    //    @DecimalMin(value = "0")
//    @DecimalMax(value = "999999999"), precision = 20, scale = 2
    @Expose
    @SerializedName("incomeDifferenceChange")
    @ColumnInfo(name = "income_difference_change")
    private BigDecimal incomeDifferenceChange;

    //    @DecimalMin(value = "0")
//    @DecimalMax(value = "999999999"), precision = 20, scale = 5
    @Expose
    @SerializedName("officialExchangeRate")
    @ColumnInfo(name = "official_exchange_rate")
    private BigDecimal officialExchangeRate;

    // factura exportacion
//    @Size(min = 1, max = 100), length = 255
    @Expose
    @SerializedName("incoterm")
    @ColumnInfo(name = "incoterm")
    private String incoterm;

    //    @Size(min = 1, max = 100), length = 255
    @Expose
    @SerializedName("incotermDetail")
    @ColumnInfo(name = "incoterm_detail")
    private String incotermDetail;

    //    @Size(min = 1, max = 500)
    @Expose
    @SerializedName("destinationPort")
    @ColumnInfo(name = "destination_port")
    private String destinationPort;

    //    @Size(min = 1, max = 500), length = 500
    @Expose
    @SerializedName("destinationPlace")
    @ColumnInfo(name = "destination_place")
    private String destinationPlace;

    //    @Min(value = 1)
//    @Max(value = 300)
    @Expose
    @SerializedName("countryCode")
    @ColumnInfo(name = "country_code")
    private Integer countryCode;

    //    @Size(min = 1, max = 10000)
    @Expose
    @SerializedName("costsNationalExpenses")
    @ColumnInfo(name = "costs_national_expenses")
    private String costsNationalExpenses;

    //    @DecimalMin(value = "0")
//    @DecimalMax(value = "999999999"), precision = 20, scale = 2
    @Expose
    @SerializedName("totalNationalFobExpenses")
    @ColumnInfo(name = "total_national_fob_expenses")
    private BigDecimal totalNationalFobExpenses;

    //    @Size(min = 1, max = 10000), length = 10000
    @Expose
    @SerializedName("costsInternationalExpenses")
    @ColumnInfo(name = "costs_international_expenses")
    private String costsInternationalExpenses;

    //    @DecimalMin(value = "0")
//    @DecimalMax(value = "999999999"), precision = 20, scale = 2
    @Expose
    @SerializedName("totalInternationalExpenses")
    @ColumnInfo(name = "total_international_expenses")
    private BigDecimal totalInternationalExpenses;

    //    @DecimalMin(value = "0")
//    @DecimalMax(value = "999999999"), precision = 20, scale = 2
    @Expose
    @SerializedName("amountDetail")
    @ColumnInfo(name = "amount_detail")
    private BigDecimal amountDetail;

    //    @Size(min = 1, max = 10000)
    @Expose
    @SerializedName("numberDescriptionPackages")
    @ColumnInfo(name = "number_description_packages")
    private String numberDescriptionPackages;

    //    @Size(min = 1, max = 10000)
    @Expose
    @SerializedName("additionalInformation")
    @ColumnInfo(name = "additional_information")
    private String additionalInformation;

//    @NonNull
    @SerializedName("receptionId")
    @ColumnInfo(name = "reception_id")
    private Long receptionId;

    @NonNull
    @SerializedName("companyId")
    @ColumnInfo(name = "company_id")
    private Long companyId;

    @NonNull
    @SerializedName("branchId")
    @ColumnInfo(name = "branch_id")
    private Long branchId;

    @SerializedName("salePointId")
    @ColumnInfo(name = "sale_point_id")
    private Long salePointId;

    //    @OneToMany(mappedBy = "invoice")
//    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Ignore
    private Set<InvoiceDetail> details = new HashSet<>();
}
