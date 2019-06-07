package reko.processing;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The UserButton class define a general user button. Moreover it contains methods which manage the behaviour of the button, like animations and appearance.
 */
public class UserButton {

    protected PApplet pApplet;    //PApplet instance that must be the same PApplet instance in use in all classes

    protected int[] colorRGB = {255, 255, 255};     //color of the button, in RGB format. It will be the color both button and animations (default color is white)

    protected PImage buttonImage = null;     //image that will be displayed over the button

    //coordinates of the button
    protected int positionX = 0;  //horizontal position of button in pixels
    protected int positionY = 0;  //vertical position of the button in pixels
    //size of the button
    protected int width = 100;    //width of the button
    protected int height = 100;   //height of the button

    /**
     * Constructor of this class, as argument requires a PApplet instance.
     * @param pApplet
     */
    public UserButton(PApplet pApplet){
        this.pApplet = pApplet;
    }

    /**
     * Sets the image of the button
     * @param buttonImage
     */
    public void setButtonImage(PImage buttonImage) {
        this.buttonImage = buttonImage;
    }

    /**
     * Sets the color of the button
     * @param colorRGB
     */
    public void setColorRGB(int[] colorRGB) {
        this.colorRGB = colorRGB;
    }

    /**
     * Sets the position on screen of the button
     * @param positionX
     * @param positionY
     */
    public void setPosition(int positionX, int positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     * Sets the dimensions of the button
     * @param width
     * @param height
     */
    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
    }

    /**
     * Draws the button
     */
    public void drawButton(){
        pApplet.noStroke(); //remove stroke
        pApplet.fill(pApplet.color(colorRGB[0], colorRGB[1], colorRGB[2])); //sets the color
        pApplet.ellipse(positionX, positionY, width, height);   //draws circular button
        pApplet.image(buttonImage, positionX-75, positionY-75, width, height);  //draws image over the button
        if(isPressed()){
            onClick();
        }
    }

    /**
     * This method return true only if the button is pressed
     * @return
     */
    protected boolean isPressed(){
        if(pApplet.mousePressed){   //if there is a touch on the screen
            if(pApplet.mouseX>positionX && pApplet.mouseX<positionX+width){ //if this touch is inside the width of the button
                if(pApplet.mouseY>positionY && pApplet.mouseY<positionY+height){    //if this touch is inside the height of the button
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * In this method will be defined what the button doing when is pressed
     */
    public void onClick(){}
}
