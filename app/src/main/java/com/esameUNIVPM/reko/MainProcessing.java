package com.esameUNIVPM.reko;

import processing.core.PApplet;

public class MainProcessing extends PApplet {
    private CameraButton cameraButton;
    MainProcessing (){}
    public void setup(){
        cameraButton = new CameraButton(this);
    }
    public void settings(){
        size(width,height);
    }
    public void draw (){
        background(255);
        cameraButton.draw();
    }

}
