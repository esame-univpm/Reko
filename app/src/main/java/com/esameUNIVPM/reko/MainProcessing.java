package com.esameUNIVPM.reko;

import java.util.ArrayList;

import processing.core.PApplet;

/**
 * This class shows the main view of the software using Processing
 */

public class MainProcessing extends PApplet {

    private MainActivity mainActivity;  //object of the activity in use
    private ArrayList<MainButton> mainButtons = new ArrayList<>();  //array of buttons

    MainProcessing (MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }

    public void setup(){
        mainButtons.add(new GalleryButton(this, mainActivity));     //create a button for gallery
        mainButtons.add(new CameraButton(this, mainActivity));      //create a button for camera
    }

    /**
     * Set the dimension of workspace, (width of screen in px, height of screen in px).
     * fullScreen() for a full screen workspace
     */
    public void settings(){
        //fullScreen();
        size(width,height);
    }

    /**
     * This method overrides PApplet method and starts a thread which draw all the object in the screen
     */
    public void draw (){
        background(255);    //set background of screen as white

        //show the buttons
        for(int i=0; i<mainButtons.size(); i++){
            mainButtons.get(i).draw();
        }
    }

}
