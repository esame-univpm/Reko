package com.esameUNIVPM.reko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import processing.android.CompatUtils;
import processing.android.PFragment;

public class ImageActivity extends AppCompatActivity {

    ImageProcessing imageProcessing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get instance of the old activity
        Intent data = getIntent();
        byte[] image = data.getExtras().getByteArray("byteArray");


        FrameLayout frame = new FrameLayout(this);
        frame.setId(CompatUtils.getUniqueViewId());
        setContentView(frame, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        //get the byte array passed through intent.putExtra()
        imageProcessing = new ImageProcessing(image, this);
        PFragment fragment = new PFragment(imageProcessing);
        fragment.setView(frame, this);
    }
}
