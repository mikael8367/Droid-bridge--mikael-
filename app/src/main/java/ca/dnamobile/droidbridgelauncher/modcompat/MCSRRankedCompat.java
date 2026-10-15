package ca.dnamobile.droidbridgelauncher.modcompat;

import android.content.Context;
import java.io.File;
import java.util.ArrayList;

/** Optional MCSR Ranked/Snappy compatibility hook. */
public final class MCSRRankedCompat {
    private MCSRRankedCompat() {}
    public static void applySnappyCompatibility(Context context, File gameDirectory, ArrayList<String> args) {
        // No private patch implementation is included in this source archive.
    }
}