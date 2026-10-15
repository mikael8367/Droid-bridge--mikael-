package ca.dnamobile.droidbridgelauncher.modmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/** Lets the user choose an existing world folder when installing a datapack. */
public final class DatapackWorldTargetPicker {
    private DatapackWorldTargetPicker() {}

    public interface Callback {
        void onTargetSelected(@NonNull File targetDirectory);
    }

    public static void choose(@NonNull Context context, @NonNull File gameDirectory,
                              @NonNull Callback callback) {
        File saves = new File(gameDirectory, "saves");
        File[] worlds = saves.listFiles(File::isDirectory);
        ArrayList<File> choices = new ArrayList<>();
        if (worlds != null) {
            Collections.addAll(choices, worlds);
            Collections.sort(choices, Comparator.comparing(File::getName, String.CASE_INSENSITIVE_ORDER));
        }
        if (choices.isEmpty()) {
            Toast.makeText(context, "Nenhum mundo encontrado em " + saves.getAbsolutePath(),
                    Toast.LENGTH_LONG).show();
            return;
        }
        String[] labels = new String[choices.size()];
        for (int i = 0; i < choices.size(); i++) labels[i] = choices.get(i).getName();
        new AlertDialog.Builder(context)
                .setTitle("Escolher mundo para o datapack")
                .setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, labels),
                        (dialog, which) -> {
                            if (which >= 0 && which < choices.size()) callback.onTargetSelected(choices.get(which));
                        })
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }
}
