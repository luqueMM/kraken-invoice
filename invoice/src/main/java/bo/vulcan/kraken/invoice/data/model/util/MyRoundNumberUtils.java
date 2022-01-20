package bo.vulcan.kraken.invoice.data.model.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MyRoundNumberUtils {

    public static BigDecimal scaleTwo(BigDecimal value) {
        if(value != null)
            return value.setScale(2);
        else
            return null;
    }

    public static BigDecimal scaleFive(BigDecimal value) {
        if(value != null)
            return value.setScale(5);
        else
            return null;
    }

    public static BigDecimal roundTwoDecimal(BigDecimal value) {
        if(value != null)
            return value.setScale(2, RoundingMode.HALF_UP);
        else
            return null;
    }

    public static BigDecimal roundFiveDecimal(BigDecimal value) {
        if (value != null)
            return value.setScale(5, RoundingMode.HALF_UP);
        else
            return null;
    }
}
