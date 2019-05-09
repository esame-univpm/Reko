package reko.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import processing.core.PApplet;
import reko.view.*;

//this class manage the view of the app using Processing
public class MainProcessing extends PApplet {

    //instance of the fragment in use
    private Fragment pFragement;
    //home view
    private HomeView homeView;
    //image view
    private ImageView imageView = null;
    //flag used to choose which view will be show
    private int flagView;

    MainProcessing (){
    }

    public void setFragmentInUse(Fragment fragment){
        this.pFragement=fragment;
        flagView=0;
    }

    private void setPathImageSelected(){
        Bundle bundle = pFragement.getArguments();
        if (bundle != null)
        {
            //Absolute path of selected image
            String pathSelectedImage = bundle.getString("path");
            imageView = new ImageView(this, pFragement);
            imageView.setPathSelectedImage(pathSelectedImage);
            flagView=1;
        }
    }

    public void setup(){
        //create HomeView object
        homeView = new HomeView(this, pFragement);
        //if image has been selected, start ImageView
        setPathImageSelected();
    }

    //set the dimension of workspace, (width of screen in px, height of screen in px).
    //fullScreen() for a full screen workspace
    public void settings(){
        //fullScreen();
        size(width,height);
    }

    //his method overrides PApplet method and starts a thread which draw all the object in the screen
    public void draw (){
        background(255);    //set background of screen as white

        switch (flagView){
            case 0:{
                //show home view
                homeView.show();
                break;
            }
            case 1:{
                //show image view
                imageView.show();
                break;
            }
        }
    }

    public void mousePressed(){
        if(flagView==0){
            //start GalleryFragment if its conditions are satisfied
            homeView.pressGallery();

            //start CameraFragment if its conditions are satisfied
            homeView.pressCamera();
        }
    }

}
