package ca.dnamobile.droidbridgelauncher.logs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.json.JSONObject;
import java.io.File;
import java.util.Collections;
import java.util.List;

/** Optional Forge/NeoForge log summary. Returns no summary when metadata scanning is unavailable. */
public final class ForgeNeoForgeModList {
    private ForgeNeoForgeModList() {}
    public static final class Loader {
        public final String label;
        public Loader(String label) { this.label = label; }
    }
    public static final class Entry {
        private final String line;
        public Entry(String line) { this.line = line; }
        public String toLogLine() { return line; }
    }
    public static final class Summary {
        public final Loader loader;
        public final List<Entry> entries;
        public final int jarCount;
        public final int jarsWithoutReadableMetadata;
        public Summary(Loader loader, List<Entry> entries, int jarCount, int unreadable) {
            this.loader = loader; this.entries = entries; this.jarCount = jarCount;
            this.jarsWithoutReadableMetadata = unreadable;
        }
    }
    @Nullable
    public static Summary scan(@NonNull File gameDirectory, @NonNull String launchVersionId, @NonNull JSONObject versionJson) {
        return null;
    }
}