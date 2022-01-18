package bo.vulcan.kraken.invoice.data.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import bo.vulcan.kraken.invoice.utils.ErrorsManager;
import bo.vulcan.kraken.invoice.utils.Validations;
import lombok.Data;

public final class AuthenticationRequest {

    private AuthenticationRequest() { }

    @Data
    public static class ServerLoginRequest {

//        @NotNull
//        @Size(min = 2, max = 4, message = "user validatrios ")
        @Expose
        @SerializedName("username")
        private String username;

//        @NotNull
//        @Size(min = 1, max = 4)
        @Expose
        @SerializedName("password")
        private String password;

        public ServerLoginRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String validate(){
            ErrorsManager manager = new ErrorsManager();

            manager.check("username", Validations.Str.notNullAndNotEmpty(username));
            manager.check("username", Validations.Str.size(username, 2, 48));

            manager.check("password", Validations.Str.notNullAndNotEmpty(password));
            manager.check("password", Validations.Str.size(password, 2, 48));

            return manager.getErrors();
        }
    }
}