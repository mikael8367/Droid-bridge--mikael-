package ca.dnamobile.droidbridgelauncher.recording;

import android.content.Context;
import android.content.Intent;
import android.view.Surface;

/** Broadcast contract retained so the game screen can start without the optional encoder module. */
public final class RecordingFrameBridge {
    public static final String ACTION_ATTACH = "ca.dnamobile.droidbridgelauncher.recording.ATTACH";
    public static final String ACTION_DETACH = "ca.dnamobile.droidbridgelauncher.recording.DETACH";
    public static final String EXTRA_SESSION = "session";
    public static final String EXTRA_WIDTH = "width";
    public static final String EXTRA_HEIGHT = "height";
    public static final String EXTRA_FPS = "fps";
    public static final String EXTRA_SURFACE = "surface";
    private RecordingFrameBridge() {}
    public static Surface readSurface(Intent intent) { return null; }
    public static void sendAck(Context context, long sessionId, boolean success, String detail) {
        // No encoder is bundled in this source build.
    }
}