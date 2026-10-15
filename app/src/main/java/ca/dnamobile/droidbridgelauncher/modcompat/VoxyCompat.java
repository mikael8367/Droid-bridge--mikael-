package ca.dnamobile.droidbridgelauncher.modcompat;

import android.content.Context;
import ca.dnamobile.droidbridgelauncher.renderer.RendererInterface;
import ca.dnamobile.droidbridgelauncher.launcher.LaunchPlan;

/** Optional Voxy renderer integration. This public source package does not include its patch implementation. */
public final class VoxyCompat {
    private VoxyCompat() {}
    public static RendererInterface selectRendererForLaunch(Context context, RendererInterface renderer, PreLaunchModScan scan) {
        return renderer;
    }
    public static boolean isUsingRequiredRenderer(RendererInterface renderer) {
        return false;
    }
    public static LaunchPlan applyLaunchPlanCompatibility(LaunchPlan plan, PreLaunchModScan scan) {
        return plan;
    }
}