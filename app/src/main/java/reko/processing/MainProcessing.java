package reko.processing;

import processing.core.PApplet;
import processing.core.PImage;
import reko.MainActivity;

/**
 * The MainProcessing class defines the standard background of the various Reko's views.
 * Moreover contains the method to set the object of MainActivity which is indispensable
 * in all the views.
 */
public class MainProcessing extends PApplet {

    /**
     * Object of the MainActivity class.
     */
    protected MainActivity mainActivity;

    private PImage background;  //image on background

    /**
     * Sets the instance of the MainActivity's object
     * @param mainActivity
     */
    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    /**
     * This method overrides the PApplet's setup method. It sets the image which will be displayed
     * on background.
     */
    @Override
    public void setup() {
        background = loadImage("home_background.jpg");
    }

    /**
     * This method overrides the PApplet's draw method. It displays on fullscreen the
     * background's image.
     */
    @Override
    public void draw() {
        image(background, 0, 0, width, height);
    }
}
