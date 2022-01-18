package bo.vulcan.kraken.invoice.data.model.util;

import java.util.UUID;

public class RandomUtil {

    public static String generateLowerUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
    }

    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
