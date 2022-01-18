package bo.vulcan.kraken.invoice.utils;

import bo.vulcan.kraken.invoice.BuildConfig;
import timber.log.Timber;

public final class AppLogger {

    public static void init() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
