package ca.dnamobile.droidbridgelauncher.recording;

import android.content.Context;

/** Recovery hook when the optional recording engine is not present. */
public final class RecordingRecovery {
    private RecordingRecovery() {}
    public static void recoverAsync(Context context) {
        // Nothing to recover when the recording engine is not bundled.
    }
}