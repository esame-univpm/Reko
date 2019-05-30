package reko.view;

import processing.core.PApplet;
import processing.core.PImage;

public class TakeButton extends MainButton {

    private PApplet pApplet;  //instance of processing object in use
    private PImage image;    //is used to load and show the image

    private int positionX;
    private int positionY;
    private boolean isPressed;

    TakeButton(PApplet pApplet, int positionX, int positionY, String image){
        this.pApplet = pApplet;
        this.positionX = positionX;
        this.positionY = positionY;
        this.image = pApplet.loadImage(image);
    }

    public void draw() {
        //show the image in the right bottom corner
        pApplet.image(image, positionX, positionY, 200, 200);
        //show the animation
        animation(pApplet, positionX, positionY, 200, 200);
        isPressed = pressButton(pApplet, positionX, positionY, 200, 200);
    }

    //draws animation when the button is pressed
    public void animation(PApplet pApplet, int positionX, int positionY, int width, int height){
        if(pressButton(pApplet, positionX, positionY, width, height)){
            pApplet.noFill();
            pApplet.strokeWeight(10);
            pApplet.stroke(255);
            pApplet.ellipse(positionX+width/2, positionY+height/2, width, height);
        }
    }

    public boolean isPressed(){
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }
}
