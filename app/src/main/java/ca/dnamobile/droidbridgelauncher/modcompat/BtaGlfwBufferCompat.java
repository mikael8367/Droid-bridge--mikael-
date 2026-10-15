package ca.dnamobile.droidbridgelauncher.modcompat;

import java.io.File;

/** Best-effort compatibility hook for BTA GLFW output buffers. */
public final class BtaGlfwBufferCompat {
    private BtaGlfwBufferCompat() {}
    public static void prepare(File lwjglComponentDir) {
        // No bundled patcher is present in this source distribution.
        // Keep launch setup safe instead of modifying third-party jars blindly.
    }
}