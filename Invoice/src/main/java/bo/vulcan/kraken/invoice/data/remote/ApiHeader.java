package bo.vulcan.kraken.invoice.data.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.Data;

@Singleton
public class ApiHeader {

    private ProtectedApiHeader mProtectedApiHeader;

    private PublicApiHeader mPublicApiHeader;

    @Inject
    public ApiHeader(PublicApiHeader publicApiHeader, ProtectedApiHeader protectedApiHeader) {
        mPublicApiHeader = publicApiHeader;
        mProtectedApiHeader = protectedApiHeader;
    }

    public ProtectedApiHeader getProtectedApiHeader() {
        return mProtectedApiHeader;
    }

    public PublicApiHeader getPublicApiHeader() {
        return mPublicApiHeader;
    }

    public static final class ProtectedApiHeader {

        @Expose
        @SerializedName("Authorization")
        private String mAccessToken;

        public ProtectedApiHeader(String mAccessToken) {
            this.mAccessToken = mAccessToken;
        }

        public String getAccessToken() {
            return mAccessToken;
        }

        public void setAccessToken(String accessToken) {
            mAccessToken = accessToken;
        }
    }

    @Data
    public static final class PublicApiHeader {

        @Expose
        @SerializedName("Content-Type")
        private String type = "application/json";

        @Inject
        public PublicApiHeader() {

        }
    }

    /*private static class BaseApiHeader {

        @Expose
        @SerializedName("App-Version")
        private String appVersion = "7";

        @Expose
        @SerializedName("Accept")
        private String accept = "application/json";

        @Expose
        @SerializedName("Device-Type")
        private String deviceType = "a";

        @Expose
        @SerializedName("Content-Type")
        private String type = "application/json";

        *//*@Expose
        @SerializedName("player-id")
        private String playerId = "";

        @Expose
        @SerializedName("locale")
        private String locale = LanguageSetting.getLanguage(BaseApplication.getInstance()).language;

        @Expose
        @SerializedName("Freshchat-Restore-Id")
        private String restoreId = "";

        @Expose
        @SerializedName("Country")
        private String country = BaseApplication.getInstance().getCountryRegionFromPhone();

        @Expose
        @SerializedName("Currency-Country")
        private String curency = "US";

        @Expose
        @SerializedName("apn-token")
        private String apnToken = "";*//*
    }*/
}
