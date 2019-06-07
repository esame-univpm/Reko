package reko.processing;

import processing.core.PImage;

/**
 * The DetectView class draws and control the detect views.
 */
public class DetectView extends MainView {

    private PImage icon;    //image of detect icon

    private int[] colorRGB; //color of view

    private UserButton cameraButton;    //open camera button
    private UserButton galleryButton;   //open gallery button

    public DetectView(ViewsController viewsController, String str, int[] colorRGB) {
        super(viewsController);
        icon = viewsController.loadImage(str);
        this.colorRGB = colorRGB;
        init();
    }

    /**
     * Sets the things to show in this view.
     */
    @Override
    public void init() {
        super.init();
        cameraButton = new UserButton(viewsController){

            private boolean flagPressed = false;

            @Override
            public void onClick() {
                viewsController.noLoop();
                viewsController.getMainActivity().getViewsFragment().openCamera();
            }

            @Override
            public void drawButton() {
                super.pApplet.image(buttonImage, positionX, positionY, 200, 200);  //draws image over the button
                if(isPressed()){
                    flagPressed = true;
                }
                drawAnimation();
            }

            private void drawAnimation(){
                if(flagPressed){
                    viewsController.stroke(255);
                    viewsController.strokeWeight(12);
                    viewsController.noFill();
                    viewsController.ellipse(positionX+100, positionY+100, 200, 200);
                    flagPressed = false;
                    onClick();
                }
            }
        };
        cameraButton.setButtonImage(viewsController.loadImage("camera_button.png"));
        cameraButton.setSize(200, 200);
        cameraButton.setPosition(viewsController.width-300, viewsController.height-300);

        galleryButton = new UserButton(viewsController){

            private boolean flagPressed = false;

            @Override
            public void onClick() {
                viewsController.noLoop();
                viewsController.getMainActivity().getViewsFragment().openGallery();
            }
            @Override
            public void drawButton() {
                super.pApplet.image(buttonImage, positionX, positionY, 200, 200);  //draws image over the button
                if(isPressed()){
                    flagPressed = true;
                }
                drawAnimation();
            }

            private void drawAnimation(){
                if(flagPressed){
                    viewsController.stroke(255);
                    viewsController.strokeWeight(12);
                    viewsController.noFill();
                    viewsController.ellipse(positionX+100, positionY+100, 200, 200);
                    flagPressed = false;
                    onClick();
                }
            }
        };
        galleryButton.setButtonImage(viewsController.loadImage("gallery_button.png"));
        galleryButton.setSize(200, 200);
        galleryButton.setPosition(100, viewsController.height-300);
    }

    /**
     * Draws this view
     */
    @Override
    public void draw() {
        super.draw();   //draws background
        //draws color bar with icon
        viewsController.noStroke();
        viewsController.fill(viewsController.color(colorRGB[0], colorRGB[1], colorRGB[2]));
        viewsController.rect(0, viewsController.height/2-200, viewsController.width, 300);
        viewsController.image(icon, viewsController.width/2-150, viewsController.height/2-200, 300, 300);
        viewsController.fill(viewsController.color(50, 50, 50));
        viewsController.rect(0, viewsController.height/2+100, viewsController.width, viewsController.height);

        viewsController.fill(255);
        viewsController.textSize(70);
        viewsController.text("Select image", viewsController.width/2-200, viewsController.height-600);

        cameraButton.drawButton();
        galleryButton.drawButton();
    }
}
