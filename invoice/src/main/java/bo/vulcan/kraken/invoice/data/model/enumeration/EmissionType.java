package bo.vulcan.kraken.invoice.data.model.enumeration;

public enum EmissionType {
    ONLINE(1),
    OFFLINE(2),
    MASSIVE(3);

    public final Integer code;

    EmissionType(Integer code) {
        this.code = code;
    }

    public static EmissionType emissionTypeInterpretation(Integer code) {
        if (code == 1) {
            return MASSIVE;
        } else if (code == 2) {
            return ONLINE;
        }

        // for (EmissionType v : values()) {
        //     if (v.code.equals(code)) {
        //         return v;
        //     }
        // }
        throw new IllegalArgumentException("EMISSION_TYPE_NOT_VALID: EmissionType not valid, Massive(1) or Online(2)");
    }
}
