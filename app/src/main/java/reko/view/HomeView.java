package reko.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import java.util.ArrayList;
import processing.core.PApplet;
import reko.controller.*;

//this class manage all the item which are contained in the application main view
public class HomeView implements AppView{

    //instance of processing object in use
    private PApplet p;
    //array of buttons
    private ArrayList<MainButton> buttons = new ArrayList<>();
    //instance of fragment in use
    private Fragment pFragment;


    public HomeView(PApplet p, Fragment pFragment){
        this.p=p;
        this.pFragment=pFragment;
        init();
    }

    //this method is used to initialize items present in the main view
    public void init(){

        //initialize buttons
        buttons.add(new GalleryButton(p));     //create a button for gallery
        buttons.add(new CameraButton(p));      //create a button for camera
    }

    //shows all the items
    public void show(){

        //recall draw method for each button
        for(MainButton mainButton : buttons){
            mainButton.draw();
        }
    }

    //if gallery button is pressed, this fragment is replaced with GalleryFragment
    // which allow the user to choose an image from the gallery
    public void pressGallery(){
        if(buttons.get(0).pressButton()){

            //create a new fragment
            GalleryFragment galleryFragment = new GalleryFragment();

            //replace fragment
            replaceFragment(galleryFragment);
        }
    }

    //if camera button is pressed, this fragment is replaced with CameraFragment
    // which allow the user to capture an image from the camera
    public void pressCamera(){
        if(buttons.get(1).pressButton()){

            //create a new fragment
            CameraFragment cameraFragment = new CameraFragment();

            //replace fragment
            replaceFragment(cameraFragment);
        }
    }

    private void replaceFragment(Fragment fragment){

        //instantiate a FragmentTransaction object that allows us an easy transaction between fragments
        FragmentTransaction fragmentTransaction = pFragment.getActivity().getSupportFragmentManager().beginTransaction();

        //replace the current fragment with the new fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);

        //allow the user to navigate backward through the fragment transaction
        fragmentTransaction.addToBackStack(null);

        //execute the transaction
        fragmentTransaction.commit();
    }

}
