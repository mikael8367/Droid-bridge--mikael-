package ca.dnamobile.droidbridgelauncher.fancymenu;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/** Persists the pending OAuth PKCE transaction while the browser/auth activity is recreated. */
public final class MicrosoftAuthSessionStore {
    private static final String PREFS = "microsoft_auth_pending";
    private static final String KEY_VERIFIER = "code_verifier";
    private static final String KEY_URL = "auth_url";

    private MicrosoftAuthSessionStore() {}

    public static void save(@NonNull Context context, @NonNull String codeVerifier,
                            @NonNull String authUrl) {
        prefs(context).edit()
                .putString(KEY_VERIFIER, codeVerifier)
                .putString(KEY_URL, authUrl)
                .apply();
    }

    @Nullable
    public static Pending load(@NonNull Context context) {
        SharedPreferences p = prefs(context);
        String verifier = p.getString(KEY_VERIFIER, null);
        String url = p.getString(KEY_URL, null);
        if (isBlank(verifier) || isBlank(url)) return null;
        return new Pending(verifier, url);
    }

    public static void clear(@NonNull Context context) {
        prefs(context).edit().clear().apply();
    }

    private static SharedPreferences prefs(Context context) {
        return context.getApplicationContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static final class Pending {
        @NonNull public final String codeVerifier;
        @NonNull public final String authUrl;

        private Pending(String codeVerifier, String authUrl) {
            this.codeVerifier = codeVerifier;
            this.authUrl = authUrl;
        }
    }
}