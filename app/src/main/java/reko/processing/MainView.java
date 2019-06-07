package reko.processing;


import processing.core.PImage;

/**
 * The MainView class contains the ViewsController object and the standard background
 */
public class MainView {

    protected ViewsController viewsController;
    private PImage background;  //image on background


    public MainView(ViewsController viewsController){
        this.viewsController = viewsController;
    }

    /**
     * This method sets the image which will be displayed on background.
     */
    public void init() {
        background = viewsController.loadImage("home_background.jpg");
    }

    /**
     * This method displays on fullscreen the background's image.
     */
    public void draw() {
        viewsController.frameRate(60);
        viewsController.image(background, 0, 0, viewsController.width, viewsController.height);
    }

}
