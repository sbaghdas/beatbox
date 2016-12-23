package com.example.suren.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {
    private final static String LOG_TAG = BeatBox.class.getSimpleName();
    private final static String SOUNDS_DIRECTORY = "sample_sounds";

    private AssetManager mAssetManager;
    private ArrayList<Sound> mSounds;

    public BeatBox(Context context) {
        mAssetManager = context.getAssets();
    }

    public void loadSounds() {
        try {
            String[] sounds = mAssetManager.list(SOUNDS_DIRECTORY);
            Log.i(LOG_TAG, "Found " + sounds.length + " sounds");
            mSounds = new ArrayList<>(sounds.length);
            for (String path : sounds) {
                mSounds.add(new Sound(path));
            }
        } catch (IOException ex) {
            Log.e(LOG_TAG, "Failed to list assets", ex);
        }
    }

    public List<Sound> getSounds() {
        return mSounds;
    }
}
