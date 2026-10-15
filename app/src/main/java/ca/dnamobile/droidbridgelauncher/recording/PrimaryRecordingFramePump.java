package ca.dnamobile.droidbridgelauncher.recording;

import android.view.Surface;
import android.view.View;

/** Optional frame pump fallback. Recording is reported unavailable when the encoder is absent. */
public final class PrimaryRecordingFramePump {
    public PrimaryRecordingFramePump(View source, Surface destination, int width, int height, int fps) {}
    public boolean start() { return false; }
    public void stop() {}
}