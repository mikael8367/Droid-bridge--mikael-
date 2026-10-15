package ca.dnamobile.droidbridgelauncher.recording;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

/**
 * Safe fallback for builds where the private media-encoding engine is absent.
 * It reports recording as unavailable instead of falsely claiming a recording is being saved.
 */
public final class GameRecordingService extends Service {
    public static final String ACTION_STATE = "ca.dnamobile.droidbridgelauncher.recording.STATE";
    public static final String EXTRA_STATE = "state";
    public static final String EXTRA_MESSAGE = "message";
    public static final String STATE_STARTED = "started";
    public static final String STATE_STOPPED = "stopped";
    public static final String STATE_ERROR = "error";
    public static final String EXTRA_RESULT_CODE = "result_code";
    public static final String EXTRA_PROJECTION_DATA = "projection_data";
    public static final String EXTRA_WIDTH = "width";
    public static final String EXTRA_HEIGHT = "height";
    public static final String EXTRA_DENSITY_DPI = "density_dpi";
    private static volatile boolean active;
    private static void notify(Context c, String state, String message) {
        Intent i = new Intent(ACTION_STATE).setPackage(c.getPackageName());
        i.putExtra(EXTRA_STATE, state);
        i.putExtra(EXTRA_MESSAGE, message);
        c.sendBroadcast(i);
    }
    public static void start(Context c, int resultCode, Intent data, int width, int height, int densityDpi) {
        active = false;
        notify(c, STATE_ERROR, "Gravação indisponível: o mecanismo de gravação não veio no código-fonte fornecido.");
    }
    public static void stop(Context c) { active = false; notify(c, STATE_STOPPED, "Gravação não está ativa."); }
    public static boolean isRecording() { return active; }
    public static boolean isRecordingOrStarting() { return active; }
    public static boolean isFinalizing() { return false; }
    @Override public IBinder onBind(Intent intent) { return null; }
}