package com.esameUNIVPM.reko;

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

        //create and start a processing interface

        super.onCreate(savedInstanceState);
        FrameLayout frame = new FrameLayout(this);
        frame.setId(CompatUtils.getUniqueViewId());
        setContentView(frame, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        mainProcessing = new MainProcessing(this);
        PFragment fragment = new PFragment(mainProcessing);
        fragment.setView(frame, this);
    }
}
