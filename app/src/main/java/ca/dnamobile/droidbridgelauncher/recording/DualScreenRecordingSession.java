package ca.dnamobile.droidbridgelauncher.recording;

import android.content.Context;
import android.view.View;

/** Optional second-screen recorder fallback. */
public final class DualScreenRecordingSession {
    private DualScreenRecordingSession() {}
    public static final class StartResult {
        public final boolean success;
        public final String errorMessage;
        public StartResult(boolean success, String errorMessage) { this.success = success; this.errorMessage = errorMessage; }
    }
    public static StartResult start(Context context, View view) {
        return new StartResult(false, "O mecanismo de gravação não está incluído nesta versão do código.");
    }
    public static void stopAsync() {}
    public static boolean isRecording() { return false; }
}