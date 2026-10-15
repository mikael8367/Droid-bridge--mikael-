package ca.dnamobile.droidbridgelauncher.installation;

import androidx.annotation.NonNull;

/**
 * Process-wide snapshot of an installation operation so launcher activities
 * can restore progress after recreation or switching between screens.
 */
public final class InstallSessionState {
    private static long nextGeneration = 1L;
    private static Snapshot current = new Snapshot(false, 0L, "", "", "", 0);

    private InstallSessionState() {}

    public static synchronized long begin(@NonNull String displayName,
                                          @NonNull String title,
                                          @NonNull String message) {
        long generation = nextGeneration++;
        current = new Snapshot(true, generation, displayName, title, message, 0);
        return generation;
    }

    public static synchronized void update(long generation, int progress, @NonNull String message) {
        if (!current.active || current.generation != generation) return;
        current = new Snapshot(true, current.generation, current.displayName, current.title,
                message, Math.max(0, Math.min(100, progress)));
    }

    public static synchronized boolean finish(long generation) {
        if (!current.active || current.generation != generation) return false;
        current = new Snapshot(false, current.generation, current.displayName, current.title,
                current.message, current.progress);
        return true;
    }

    @NonNull
    public static synchronized Snapshot snapshot() {
        return current;
    }

    public static final class Snapshot {
        public final boolean active;
        public final long generation;
        @NonNull public final String displayName;
        @NonNull public final String title;
        @NonNull public final String message;
        public final int progress;

        private Snapshot(boolean active, long generation, String displayName, String title,
                         String message, int progress) {
            this.active = active;
            this.generation = generation;
            this.displayName = displayName;
            this.title = title;
            this.message = message;
            this.progress = progress;
        }
    }
}