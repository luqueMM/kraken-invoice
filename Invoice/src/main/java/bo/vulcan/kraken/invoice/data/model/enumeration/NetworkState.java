package bo.vulcan.kraken.invoice.data.model.enumeration;

/**
 * The NetworkState enumeration.
 */
public enum NetworkState {
    ONLINE(1),
    OFFLINE(0);

    public final Integer code;

    NetworkState(Integer code) {
        this.code = code;
    }
}
