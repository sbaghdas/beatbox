package com.example.suren.beatbox;

/**
 * Created by Suren on 12/23/16.
 */

public class Sound {
    private String mName;
    private String mPath;
    private Integer mId;

    public Sound(String path) {
        mPath = path;
        String[] parts = path.split("/");
        String fileName = parts[parts.length - 1];
        mName = fileName.replace(".wav", "");
    }

    public String getName() {
        return mName;
    }

    public String getPath() {
        return mPath;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }
}
