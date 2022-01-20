package bo.vulcan.kraken.invoice.domain.usecases.createInvoice;

public class MyStringUtils {

    public static String formatNit(String value) {
        return removeAllSpecialCharacters(value, "");
    }

    public static String formatName(String value) {
        return removeAllSpecialCharacters(value, " ");
    }

    /**
     * Format name: remove all spaces, line break, etc.
     *
     * @param value name
     * @return clean name
     */
    private static String removeAllSpecialCharacters(String value, String replace) {
        return value.trim().replaceAll("\\s+", replace);
    }
}
