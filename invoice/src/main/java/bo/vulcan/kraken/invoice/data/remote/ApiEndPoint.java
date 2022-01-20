package bo.vulcan.kraken.invoice.data.remote;

import bo.vulcan.kraken.invoice.BuildConfig;

public final class ApiEndPoint {

    public static final String ENDPOINT_AUTHENTICATE = BuildConfig.API_BASE_URL + "authenticate";

    public static final String ENDPOINT_CURRENCY_TYPES = BuildConfig.API_BASE_URL_MS + "currency-types/company/{companyID}";
    public static final String ENDPOINT_DOCUMENT_TYPES = BuildConfig.API_BASE_URL_APISFETST + "document-types/company/{companyID}";
    public static final String ENDPOINT_PAYMENT_METHOD_TYPES = BuildConfig.API_BASE_URL_APISFETST + "payment-method-types/company/{companyID}";
    public static final String ENDPOINT_PRODUCTS = BuildConfig.API_BASE_URL_APISFETST + "products/all";
    public static final String ENDPOINT_SIAT_PRODUCTS = BuildConfig.API_BASE_URL_APISFETST + "product-services/company/{companyID}";
    public static final String ENDPOINT_BRANCH_ACTIVITY_LEGENDS = BuildConfig.API_BASE_URL_APISFETST + "branch-activity-legends/branch/{branchID}";
    public static final String ENDPOINT_SIAT_MEASUREMENT_UNIT = BuildConfig.API_BASE_URL_APISFETST + "measurement-units/company/{companyID}";
}
