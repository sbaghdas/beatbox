package com.example.suren.beatbox;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends SimpleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new BeatBoxFragment();
    }
}
