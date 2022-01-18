package bo.vulcan.kraken.invoice.data.model.enumeration;

public enum Environment {

    PRODUCTION(1),
    TEST(2);

    public final Integer code;

    Environment(Integer code) {
        this.code = code;
    }

}
