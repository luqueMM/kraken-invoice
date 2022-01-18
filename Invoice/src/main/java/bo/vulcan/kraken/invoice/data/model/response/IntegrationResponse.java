package bo.vulcan.kraken.invoice.data.model.response;

import lombok.Data;

@Data
public class IntegrationResponse {
    private ResponseType type;
    private String message;
    private String output;

    public IntegrationResponse(){
        this.type = ResponseType.SUCCESS;
        this.message = "Sussesfully";
        this.output = null;
    }

    public IntegrationResponse(String message){
        this.type = ResponseType.ERROR;
        this.message = message;
        this.output = null;
    }

    enum ResponseType {
        SUCCESS,
        ERROR
    }
}
