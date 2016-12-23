package com.example.suren.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.media.AudioManager.STREAM_MUSIC;

public class BeatBox {
    private final static String LOG_TAG = BeatBox.class.getSimpleName();
    private final static String SOUNDS_DIRECTORY = "sample_sounds";
    private final static int MAX_SOUND_STREAMS = 5;

    private AssetManager mAssetManager;
    private ArrayList<Sound> mSounds;
    private SoundPool mSoundPool;

    public BeatBox(Context context) {
        mAssetManager = context.getAssets();
        mSoundPool = new SoundPool(MAX_SOUND_STREAMS, STREAM_MUSIC, 0);
    }

    public void loadSounds() {
        String[] fileNames;
        mSounds = null;
        try {
            fileNames = mAssetManager.list(SOUNDS_DIRECTORY);
            Log.i(LOG_TAG, "Found " + fileNames.length + " sounds");
        } catch (IOException ex) {
            Log.e(LOG_TAG, "Failed to list assets", ex);
            return;
        }
        mSounds = new ArrayList<>(fileNames.length);
        for (String fileName : fileNames) {
            Sound sound = new Sound(SOUNDS_DIRECTORY + "/" + fileName);
            if (load(sound)) {
                mSounds.add(sound);
            }
        }
    }

    public List<Sound> getSounds() {
        return mSounds;
    }

    public void releaseSounds() {
        mSoundPool.release();
    }

    private boolean load(Sound sound) {
        try {
            AssetFileDescriptor fd = mAssetManager.openFd(sound.getPath());
            int id = mSoundPool.load(fd, 0);
            sound.setId(id);
        } catch (IOException ex) {
            Log.e(LOG_TAG, "Failed to open asset " + sound.getName(), ex);
            return false;
        }
        return true;
    }

    public void play(Sound sound) {
        Integer id = sound.getId();
        if (id != null) {
            mSoundPool.play(id, 1.0f, 1.0f, 1, 0, 1.0f);
        }
    }
}
