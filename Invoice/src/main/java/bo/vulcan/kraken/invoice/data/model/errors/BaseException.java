package bo.vulcan.kraken.invoice.data.model.errors;
import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @ToString
public class BaseException extends Exception {

    private final List<CodeMessage> codeMessageList;

    public BaseException(List<CodeMessage> codeMessageList) {
        super(codeMessageList.toString());
        this.codeMessageList = codeMessageList;
    }

    @Getter @Setter @ToString @AllArgsConstructor
    public static class CodeMessage {
        private Integer code;
        private String type;
        private String message;
    }

}
