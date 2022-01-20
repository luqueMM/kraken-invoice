package bo.vulcan.kraken.invoice.data.model.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import bo.vulcan.kraken.invoice.data.model.enumeration.DocumentSectorType;
import bo.vulcan.kraken.invoice.data.model.enumeration.Environment;
import bo.vulcan.kraken.invoice.data.model.enumeration.InvoiceReturnType;
import bo.vulcan.kraken.invoice.data.model.enumeration.Mode;
import lombok.Data;
import lombok.NonNull;

@Data
public class Company {

    @Expose
    @SerializedName("id")
    private Long id;

    @NonNull
////    @Size(max = 15), length = 15, nullable = false
    @Expose
    @SerializedName("nit")
    private String nit;

    @NonNull
////    @Size(max = 256), length = 256, nullable = false
    @Expose
    @SerializedName("name")
    private String name;

    @NonNull
////    @Size(max = 100), length = 100, nullable = false
    @Expose
    @SerializedName("shortName")
    private String shortName;

    @NonNull
//    @Size(max = 256), length = 256, nullable = false
    @Expose
    @SerializedName("legalAddress")
    private String legalAddress;

//    @Size(max = 256), length = 256
    @Expose
    @SerializedName("webAddress")
    private String webAddress;

//    @Size(max = 256), length = 256
    @Expose
    @SerializedName("nameEmergencyContact")
    private String nameEmergencyContact;

    @NonNull
//    @Size(max = 256), length = 256, nullable = false
    @Expose
    @SerializedName("phoneEmergencyContact")
    private String phoneEmergencyContact;

    @NonNull
//    @Size(max = 256), length = 256, nullable = false
    @Expose
    @SerializedName("emailEmergencyContact")
    private String emailEmergencyContact;

//    @Size(max = 256), length = 256
    @Expose
    @SerializedName("templateInvoice")
    private String templateInvoice;

//    @Size(max = 256), length = 256
    @Expose
    @SerializedName("invoiceMessage")
    private String invoiceMessage;

//    @Enumerated(EnumType.STRING)
    @Expose
    @SerializedName("mode")
    private Mode mode;

//    @Enumerated(EnumType.STRING)
    @Expose
    @SerializedName("environment")
    private Environment environment;

//    @Size(max = 512), length = 512
    @Expose
    @SerializedName("tokenSin")
    private String tokenSin;

//    @Expose
//    @SerializedName("token_expiration_date_sin")
//    private Instant tokenExpirationDateSin;

//    @Size(max = 256), length = 256
    @Expose
    @SerializedName("systemCode")
    private String systemCode;

//    @Enumerated(EnumType.STRING)
    @Expose
    @SerializedName("documentSectorType")
    private DocumentSectorType documentSectorType;

//    @Enumerated(EnumType.STRING)
    @Expose
    @SerializedName("invoiceReturnType")
    private InvoiceReturnType invoiceReturnType;

    @Expose
    @SerializedName("imageName")
    private String imageName;
}
