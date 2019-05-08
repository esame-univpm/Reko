package com.esameUNIVPM.reko;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import processing.core.PApplet;
import processing.core.PImage;

public class CameraButton extends MainButton {

    private PApplet p;  //instance of processing object in use
    private PImage camerabutton;    //is used to load and show the image
    private AppCompatActivity activity; //instance of activity in use

    /**
     * Constructor of camera button
     * @param p PApplet object
     * @param activity activity used
     */

    CameraButton(PApplet p, AppCompatActivity activity) {
        this.p = p;
        camerabutton = p.loadImage("camera_button.png");    //load image in assets directory
        this.activity=activity;
    }

    @Override
    public void draw() {
        //show the image in the right bottom corner
        p.image(camerabutton, p.width - 300, p.height - 300, 200, 200);
        //show the animation
        animation();
    }

    @Override
    public void animation() {

        //show animation if the button is pressed

        if(pressButton()){
            //Draw only the stroke of various circles on the image,
            // circles are concentric and they are distanced each other of 4px.
            //This allows us to create a gray shadow on the image.
            for(int i=0; i<200; i+=4){
                p.noFill();
                p.stroke(50);   //50 is the color of circle stroke in a gray scale from 0 to 255.
                p.ellipse(p.width-200, p.height-200, i, i);
            }
        }
    }

    @Override
    public boolean pressButton() {
        //Checks if a touch press is inside the button's bound
        if (p.mousePressed) {
            if (p.mouseX > p.width - 300 && p.mouseX < p.width - 100) {
                if (p.mouseY > p.height - 300 && p.mouseY < p.height - 100) {


                    //start activity to capture image from the camera
                    Intent openCamera = new Intent(activity, CameraActivity.class);
                    activity.startActivity(openCamera);


                    return true;
                }
            }
        }
        return false;
    }
}