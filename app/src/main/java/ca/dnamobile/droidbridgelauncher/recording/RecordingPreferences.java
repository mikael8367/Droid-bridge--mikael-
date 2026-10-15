package ca.dnamobile.droidbridgelauncher.recording;

import android.content.Context;
import android.content.SharedPreferences;

/** Preferences for recording options; actual recording engine is not included in this source package. */
public final class RecordingPreferences {
    private static final String PREFS = "recording_preferences";
    private RecordingPreferences() {}
    private static SharedPreferences prefs(Context c) { return c.getSharedPreferences(PREFS, Context.MODE_PRIVATE); }
    public static boolean isRecordBothScreensEnabled(Context c) { return prefs(c).getBoolean("both_screens", false); }
    public static boolean isHideTouchControlsEnabled(Context c) { return prefs(c).getBoolean("hide_touch_controls", false); }
    public static String getAudioModeLabel(Context c) { return "Unavailable in this source build"; }
}