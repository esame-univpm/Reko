package reko.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import processing.android.PFragment;

public class DetectLabelFragment extends PFragment {

    //instance of activity in use
    private MainActivity mainActivity;

    //if true, this fragment is active
    private boolean active;

    //instance of Processing sketch which runs on this fragment
    private DetectLabelProcessing detectLabelProcessing;

    //path of the image selected
    private String pathSelectedImage = null;

    @Override
    public void onResume() {
        super.onResume();
        pathSelectedImage = getArguments().getString("path");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        detectLabelProcessing = new DetectLabelProcessing(mainActivity);
        setSketch(detectLabelProcessing);
    }

    //method to set active value
    public void setActive(boolean active) {
        this.active = active;
    }

    //method to return active value
    public boolean isActive() {
        return active;
    }

    //return the path
    public String getPathSelectedImage() {
        return pathSelectedImage;
    }

    //method to pass instance of the main activity
    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    public DetectLabelProcessing getDetectLabelProcessing() {
        return detectLabelProcessing;
    }
}
