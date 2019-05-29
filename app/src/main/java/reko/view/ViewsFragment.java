package reko.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import processing.android.PFragment;

public class ViewsFragment extends PFragment {

    //instance of Processing sketch which runs on this fragment
    private ViewsProcessing viewsProcessing;

    //if true, this fragment is active
    private boolean active;

    //instance of activity in use
    private MainActivity mainActivity;

    //on create run StartView Processing sketch
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //runs startView, the Processing sketch
        viewsProcessing = new ViewsProcessing(mainActivity);
        setSketch(viewsProcessing);
    }

    //method to set active value
    public void setActive(boolean active) {
        this.active = active;
    }

    //method to return active value
    public boolean isActive() {
        return active;
    }

    //method to pass instance of the main activity
    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }
}
