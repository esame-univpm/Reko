package reko.processing;

import processing.core.PApplet;

public class MainButton {

    //returns true if the touch on the screen is inside the box defined by the position of the top left corner and the size
    public boolean pressButton(PApplet pApplet, int positionX, int positionY, int width, int height){
        if(pApplet.mousePressed){
            if(pApplet.mouseX>positionX && pApplet.mouseX<positionX+width){
                if(pApplet.mouseY>positionY && pApplet.mouseY<positionY+height){
                    return true;
                }
            }
        }
        return false;
    }
}
