package ca.dnamobile.droidbridgelauncher.modmanager;

import androidx.annotation.NonNull;

/**
 * Conservative dependency compatibility policy. Required dependencies are never
 * silently discarded; the installer performs its normal version/loader checks.
 */
public final class ModDependencyCompatibility {
    private ModDependencyCompatibility() {}

    public static boolean shouldSkipRequiredDependency(
            @NonNull ModrinthProject parentProject,
            @NonNull ModrinthProject dependencyProject,
            @NonNull String minecraftVersion) {
        // Required dependencies must be installed or reported as incompatible by
        // the regular resolver; silently skipping them can leave a broken modpack.
        return false;
    }

    @NonNull
    public static String skippedDependencyStatus(
            @NonNull ModrinthProject project,
            @NonNull String minecraftVersion) {
        return "Dependency not skipped: " + safe(project.title)
                + " (Minecraft " + minecraftVersion + ")";
    }

    private static String safe(String value) {
        return value == null || value.trim().isEmpty() ? "unknown project" : value.trim();
    }
}