package reko.view;

import processing.core.PApplet;
import processing.core.PImage;

public class GalleryButton extends MainButton {

    private PApplet p;  //instance of processing object in use
    private PImage gallerybutton;   //is used to load and show the image

    public GalleryButton(PApplet p) {
        this.p = p;
        gallerybutton = p.loadImage("gallery_button.png");  //load image in assets directory
    }

    @Override
    public void draw() {
        //show the image in the left bottom corner
        p.image(gallerybutton, 100, p.height - 300, 200, 200);
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
                p.ellipse(200, p.height-200, i, i);
            }
        }
    }

    @Override
    public boolean pressButton() {
        //Checks if a touch press is inside the button's bound
        if (p.mousePressed) {
            if (p.mouseX > 100 && p.mouseX < 300) {
                if (p.mouseY > p.height - 300 && p.mouseY < p.height - 100) {
                    return true;
                }
            }
        }
        return false;
    }
}
