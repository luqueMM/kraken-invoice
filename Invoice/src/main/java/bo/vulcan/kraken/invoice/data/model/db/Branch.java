package bo.vulcan.kraken.invoice.data.model.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Branch {

    @Expose
    @SerializedName("id")
    private Long id;

//    @Size(max = 256), length = 256, nullable = false
    @NonNull
    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("bccsId")
    private Integer bccsId;

    @NonNull
    @Expose
    @SerializedName("number")
    private Integer number;

//    @Size(max = 256), length = 256, nullable = false
    @NonNull
    @Expose
    @SerializedName("address")
    private String address;

//    @Size(max = 256), length = 256, nullable = false
    @NonNull
    @Expose
    @SerializedName("phone")
    private String phone;

//    @Size(max = 256), length = 256, nullable = false
    @NonNull
    @Expose
    @SerializedName("country")
    private String country;

//    @Size(max = 256), length = 256, nullable = false
    @NonNull
    @Expose
    @SerializedName("city")
    private String city;

    @NonNull
    @Expose
    @SerializedName("enabled")
    private Boolean enabled;

    @NonNull
    @Expose
    @SerializedName("company")
    private Company company;
}
