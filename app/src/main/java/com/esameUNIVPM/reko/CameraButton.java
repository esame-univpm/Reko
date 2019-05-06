package com.esameUNIVPM.reko;

import processing.core.PApplet;
import processing.core.PImage;

public class CameraButton extends MainButton {
    private PApplet p;
    private PImage camerabutton;


    CameraButton (PApplet p ){
        this.p=p;
        camerabutton=p.loadImage("camerabutton.png");
        

    }
    @Override
    public void draw(){

    }
    @Override
    public void animation(){

    }
    @Override
    public void pressButton(){

    }
}
