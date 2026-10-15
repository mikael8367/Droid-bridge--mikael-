package ca.dnamobile.droidbridgelauncher.modmanager;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/** Local, per-provider persistence for starred projects and featured developer picks. */
public final class ModpackFavouritesStore {
    public static final String KEY_DEVELOPER_OPTIMOBILE = "developer:optimobile";
    public static final String KEY_DEVELOPER_VULKAN_DROID = "developer:vulkan-droid";
    private static final String PREFS = "modpack_favourites_v1";
    private static final String KEY_PREFIX = "favourites_";

    private ModpackFavouritesStore() {}

    public static final class FavouriteRecord {
        @NonNull public final String key;
        @NonNull public final String projectId;
        @NonNull public final String slug;
        @NonNull public final String title;

        public FavouriteRecord(@NonNull String key, @NonNull String projectId,
                              @NonNull String slug, @NonNull String title) {
            this.key = key;
            this.projectId = projectId;
            this.slug = slug;
            this.title = title;
        }
    }

    @NonNull
    public static synchronized List<FavouriteRecord> list(@NonNull Context context,
                                                           @NonNull ModManagerSource source) {
        ArrayList<FavouriteRecord> result = new ArrayList<>();
        String raw = prefs(context).getString(KEY_PREFIX + source.name(), "[]");
        try {
            JSONArray array = new JSONArray(raw == null ? "[]" : raw);
            for (int i = 0; i < array.length(); i++) {
                JSONObject item = array.optJSONObject(i);
                if (item == null) continue;
                String key = item.optString("key", "");
                String id = item.optString("projectId", "");
                String slug = item.optString("slug", "");
                String title = item.optString("title", "");
                if (!key.isEmpty() && (!id.isEmpty() || !slug.isEmpty())) {
                    result.add(new FavouriteRecord(key, id, slug, title));
                }
            }
        } catch (Exception ignored) { }
        return result;
    }

    public static synchronized boolean isFavourite(@NonNull Context context,
                                                     @NonNull ModrinthProject project) {
        String key = keyFor(project);
        for (FavouriteRecord record : list(context, project.source)) {
            if (key.equals(record.key)) return true;
        }
        return false;
    }

    public static synchronized void setFavourite(@NonNull Context context,
                                                  @NonNull ModrinthProject project,
                                                  boolean favourite) {
        ArrayList<FavouriteRecord> records = new ArrayList<>(list(context, project.source));
        String key = keyFor(project);
        records.removeIf(record -> key.equals(record.key));
        if (favourite) {
            records.add(new FavouriteRecord(key, safe(project.projectId),
                    safe(project.slug), safe(project.title)));
        }
        JSONArray array = new JSONArray();
        for (FavouriteRecord record : records) {
            JSONObject item = new JSONObject();
            try {
                item.put("key", record.key);
                item.put("projectId", record.projectId);
                item.put("slug", record.slug);
                item.put("title", record.title);
                array.put(item);
            } catch (Exception ignored) { }
        }
        prefs(context).edit().putString(KEY_PREFIX + project.source.name(), array.toString()).apply();
    }

    public static boolean isDeveloperPick(@Nullable ModrinthProject project) {
        if (project == null) return false;
        String id = safe(project.projectId).toLowerCase(java.util.Locale.ROOT);
        String slug = safe(project.slug).toLowerCase(java.util.Locale.ROOT);
        return KEY_DEVELOPER_OPTIMOBILE.equals(keyFor(project))
                || KEY_DEVELOPER_VULKAN_DROID.equals(keyFor(project))
                || id.contains("optimobile") || slug.contains("optimobile")
                || id.contains("vulkan-droid") || slug.contains("vulkan-droid");
    }

    @NonNull
    private static String keyFor(@NonNull ModrinthProject project) {
        String id = safe(project.projectId).trim();
        if (!id.isEmpty()) return project.source.name() + ":" + id;
        String slug = safe(project.slug).trim();
        return project.source.name() + ":" + slug;
    }

    @NonNull
    private static SharedPreferences prefs(@NonNull Context context) {
        return context.getApplicationContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    @NonNull
    private static String safe(@Nullable String value) {
        return value == null ? "" : value;
    }
}
