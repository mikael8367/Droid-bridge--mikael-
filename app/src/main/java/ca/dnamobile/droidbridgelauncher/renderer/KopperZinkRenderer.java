package ca.dnamobile.droidbridgelauncher.renderer;

import androidx.annotation.NonNull;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Kopper/Zink renderer profile. The renderer libraries themselves must be
 * installed as a compatible driver package; this class only configures them.
 */
public final class KopperZinkRenderer implements RendererInterface {
    public static final String RENDERER_ID = "kopper_zink";
    public static final String MAIN_LIBRARY = "libglxshim.so";
    public static final String EGL_LIBRARY = "libEGL_mesa.so";
    private static final String UNIQUE_ID = "2a6f4f18-8a20-4f4d-b4f4-6f1f2c1a9d71";

    public static boolean isRenderer(RendererInterface renderer) {
        if (renderer == null) return false;
        String id = renderer.getRendererId();
        String unique = renderer.getUniqueIdentifier();
        return RENDERER_ID.equalsIgnoreCase(id)
                || "kopperzink".equalsIgnoreCase(id)
                || UNIQUE_ID.equalsIgnoreCase(unique);
    }

    @NonNull @Override public String getRendererId() { return RENDERER_ID; }
    @NonNull @Override public String getUniqueIdentifier() { return UNIQUE_ID; }
    @NonNull @Override public String getRendererName() { return "Kopper / Zink"; }
    @NonNull @Override public String getRendererDescription() {
        return "Mesa Zink Vulkan-backed OpenGL; requires a compatible driver plugin.";
    }
    @NonNull @Override public Map<String, String> getRendererEnv() {
        Map<String, String> env = new LinkedHashMap<>();
        env.put("DROIDBRIDGE_RENDERER", RENDERER_ID);
        env.put("POJAV_RENDERER", RENDERER_ID);
        env.put("DROIDBRIDGE_RENDERER_LIBRARY", MAIN_LIBRARY);
        env.put("POJAV_RENDERER_LIBRARY", MAIN_LIBRARY);
        env.put("DROIDBRIDGE_EGL", EGL_LIBRARY);
        env.put("DROIDBRIDGE_EGL_LIBRARY", EGL_LIBRARY);
        env.put("POJAVEXEC_EGL", EGL_LIBRARY);
        env.put("POJAVEXEC_EGL_LIBRARY", EGL_LIBRARY);
        return env;
    }
    @NonNull @Override public List<String> getDlopenLibrary() { return Collections.emptyList(); }
    @NonNull @Override public String getRendererLibrary() { return MAIN_LIBRARY; }
    @Override public String getRendererEGL() { return EGL_LIBRARY; }
}