package com.esameUNIVPM.reko;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import processing.android.CompatUtils;
import processing.android.PFragment;

/**
 * Activity showed on start
 */

public class MainActivity extends AppCompatActivity {

    MainProcessing mainProcessing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkPermission();


        //create and start a processing interface
        FrameLayout frame = new FrameLayout(this);
        frame.setId(CompatUtils.getUniqueViewId());
        setContentView(frame, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        mainProcessing = new MainProcessing(this);
        PFragment fragment = new PFragment(mainProcessing);
        fragment.setView(frame, this);
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //if is not granted, ask user for it
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }
}
