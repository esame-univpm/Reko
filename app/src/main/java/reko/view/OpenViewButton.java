package reko.view;

import processing.core.PApplet;
import processing.core.PImage;

public class OpenViewButton extends MainButton {

    //instance of Processing's sketch in use
    private ViewsProcessing viewsProcessing;

    //cartesian coordinates of the centre of the button
    private int positionX;
    private int positionY;

    private int diameterAnimation = 150;   //diameter of the ellipse animation
    private int diameterBackAnimation;  //diameter of the ellipse during backward animation
    private boolean changeView = false;    //if true, it means that it's time to change view
    private boolean flagPressed = false;    //if true, it means that the button was pressed
    private PImage imageButton;    //is used to load and show the image
    private int[] colorRGB;

    OpenViewButton(ViewsProcessing viewsProcessing, int positionX, int positionY, String imageButton, int[] colorRGB){
        this.viewsProcessing = viewsProcessing;

        diameterBackAnimation = viewsProcessing.height*2;  //diameter of the ellipse during backward animation

        //set position of the button
        this.positionX=positionX;
        this.positionY=positionY;

        this.imageButton = viewsProcessing.loadImage(imageButton);    //load image from assets directory

        this.colorRGB = colorRGB;
    }

    public void show(){
        draw(viewsProcessing, imageButton, positionX, positionY, colorRGB);
    }

    public void animation() {
        if(pressButton(viewsProcessing, positionX-75, positionY-75, 150, 150)){
            flagPressed=true;
        }

        if(flagPressed){
            drawEllipse(viewsProcessing, positionX, positionY, colorRGB, diameterAnimation);
            if(diameterAnimation<viewsProcessing.height*2){
                diameterAnimation+=250;
                changeView = false;
            }
            else {
                changeView = true;
                diameterAnimation = 150;
            }
        }
    }

    public void backAnimation(){

        viewsProcessing.fill(colorRGB[0], colorRGB[1], colorRGB[2]);
        viewsProcessing.ellipse(positionX, positionY, diameterBackAnimation, diameterBackAnimation);
        diameterBackAnimation-=250;
        if(diameterBackAnimation<150){
            diameterBackAnimation = viewsProcessing.height*2; //re-set diameterBackAnimation to initial value
            viewsProcessing.setBackPressed(false);  //re-set to false the flag for back animation
            viewsProcessing.setNumberView(0);   //back to the home view
        }
    }

    public boolean isChangeView() {
        return changeView;
    }

    public void setChangeView(boolean changeView){
        this.changeView = changeView;
    }

    public boolean isFlagPressed() {
        return flagPressed;
    }

    public void setFlagPressed(boolean flagPressed) {
        this.flagPressed = flagPressed;
    }


    //this method draw an ellipse with an image in the centre
    public void draw(PApplet pApplet, PImage image, int positionX, int positionY, int[] colorRGB){
        animation();
        drawEllipse(pApplet, positionX, positionY, colorRGB, 150);
        pApplet.image(image, positionX-75, positionY-75, 150, 150);
    }

    //this method draw an ellipse filling it with a color provided as argument
    public void drawEllipse(PApplet pApplet, int positionX, int positionY, int[] colorRGB, int diameter){
        pApplet.noStroke();
        pApplet.fill(pApplet.color(colorRGB[0], colorRGB[1],colorRGB[2]));
        pApplet.ellipse(positionX, positionY, diameter, diameter);
    }

}
