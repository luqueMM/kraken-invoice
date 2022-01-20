package bo.vulcan.kraken.invoice.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import bo.vulcan.kraken.invoice.data.model.enumeration.ReceptionGroup;
import bo.vulcan.kraken.invoice.data.model.enumeration.ReceptionState;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(tableName = "reception")
public class Reception {

    @Expose
    @PrimaryKey
    private Long id;

    //Size(max = 256)
    @Expose
    @SerializedName("descriptionCode")
    @ColumnInfo(name = "description_code")
    private String descriptionCode;

    @Expose
    @SerializedName("stateCode")
    @ColumnInfo(name = "state_code")
    private Integer stateCode;

    //Size(max = 256)
    @Expose
    @SerializedName("code")
    @ColumnInfo(name = "code")
    private String code;

    @NonNull
    @Expose
    @SerializedName("state")
    @ColumnInfo(name = "state")
    private ReceptionState state;

    @Expose
    @SerializedName("receptionGroup")
    @ColumnInfo(name = "reception_group")
    private ReceptionGroup receptionGroup;

    @Expose
    @SerializedName("invoiceQuantity")
    @ColumnInfo(name = "invoice_quantity")
    private Integer invoiceQuantity;

    @Expose
    @SerializedName("tar")
    @ColumnInfo(name = "tar")
    private String tar;

    @NonNull
    @Expose
    @SerializedName("companyId")
    @ColumnInfo(name = "company_id")
    private Long companyId;
}
