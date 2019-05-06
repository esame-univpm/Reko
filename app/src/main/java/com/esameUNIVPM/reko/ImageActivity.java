package com.esameUNIVPM.reko;

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

        Bundle data = getIntent().getExtras();
        PickedImage pickedImage = (PickedImage) data.getParcelable("pickedImage");

        FrameLayout frame = new FrameLayout(this);
        frame.setId(CompatUtils.getUniqueViewId());
        setContentView(frame, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        imageProcessing = new ImageProcessing(pickedImage.getImage(), this);
        PFragment fragment = new PFragment(imageProcessing);
        fragment.setView(frame, this);
    }
}
