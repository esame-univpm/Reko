package reko.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import processing.android.PFragment;


//this class manage the main activity
public class MainActivity extends AppCompatActivity {

    MainProcessing mainProcessing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instance FrameLayout
        FrameLayout frame = findViewById(R.id.frameLayout);
        setContentView(frame);

        //instance object of Processing
        mainProcessing = new MainProcessing();

        //run Processing in a Fragment
        PFragment pFragment = new PFragment(mainProcessing);

        //pass PFragment to MainProcessing
        mainProcessing.setFragmentInUse(pFragment);

        //run processing fragment in the FrameLayout declared
        pFragment.setView(frame, this);
    }

}
