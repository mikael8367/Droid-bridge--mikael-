package ca.dnamobile.droidbridgelauncher.modcompat;

import android.content.Context;
import java.io.File;

/** Reports the optional native SQLite compatibility preparation status. */
public final class DistantHorizonsSqliteCompat {
    private DistantHorizonsSqliteCompat() {}
    public static final class Result {
        public final boolean detected;
        public final String summary;
        public Result(boolean detected, String summary) { this.detected = detected; this.summary = summary; }
    }
    public static Result prepare(Context context, File gameDirectory) {
        return new Result(false, "optional compatibility helper is not bundled");
    }
}