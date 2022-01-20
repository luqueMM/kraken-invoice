package bo.vulcan.kraken.invoice.data.model.errors;

public final class ErrorConstants {

    public static final String SYSTEM_PARAMETER_NOT_EXIST = "SYSTEM_PARAMETER_NOT_EXIST";
    public static final String ENTITY_NOT_EXISTS = "ENTITY_NOT_EXISTS";
    public static final String SYSTEM_PARAMETER_INVALID_VALUE = "SYSTEM_PARAMETER_INVALID_VALUE";
    public static final String ID_ALREADY_EXISTS = "ID_ALREADY_EXISTS";
    public static final String CUIS_ERROR = "CUIS_ERROR";
    public static final String CUFD_ERROR = "CUFD_ERROR";
    public static final String VERIFY_COMMUNICATION_ERROR = "VERIFY_COMMUNICATION_ERROR";//VerifyCommunication
    public static final String GENERATE_XML_ERROR = "GENERATE_XML_ERROR";
    public static final String RECEIPT_INVOICE_ERROR = "RECEIPT_INVOICE_ERROR";
    public static final String INVOICE_NOT_FOUND = "INVOICE_NOT_FOUND";
    public static final String COUNTRY_CODE_NOT_FOUND = "COUNTRY_CODE_NOT_FOUND";
    public static final String ERROR_EXPORT_DETAIL = "ERROR_EXPORT_DETAIL";
    public static final String PRODUCT_REQUIRES_NANDINA = "PRODUCT_REQUIRES_NANDINA";
    public static final String PRODUCT_DOEST_NOT_REQUIRES_NANDINA = "PRODUCT_DOEST_NOT_REQUIRES_NANDINA";
    public static final String SIGNIFICANT_EVENT_IN_PROGRESS = "SIGNIFICANT_EVENT_IN_PROGRESS";
    public static final String INVOICE_PENDING_FOR_SALE_POINT = "INVOICE_PENDING_FOR_SALE_POINT";

    private ErrorConstants() {
    }
}
