package reko.view;

import processing.core.PApplet;

public class ViewsProcessing extends PApplet {

    private MainActivity mainActivity;

    ViewsProcessing(MainActivity mainActivity){
        this.mainActivity = mainActivity;   //passes the instance of the main activity
    }

    @Override
    public void draw() {
        background(255);
    }
}
