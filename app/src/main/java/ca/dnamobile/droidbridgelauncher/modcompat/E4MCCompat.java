package ca.dnamobile.droidbridgelauncher.modcompat;

import android.content.Context;
import java.io.File;
import java.util.ArrayList;

/** Optional e4mc native compatibility hook. */
public final class E4MCCompat {
    private E4MCCompat() {}
    public static void applyNativeCompatibility(Context context, File gameDirectory, ArrayList<String> args) {
        // Optional hook unavailable in the public source archive; leave JVM arguments unchanged.
    }
}