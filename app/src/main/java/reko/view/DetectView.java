package reko.view;

import processing.core.PImage;

public class DetectView {

    //instance of Processing's sketch in use
    private ViewsProcessing viewsProcessing;

    //image in background
    private PImage background;

    //buttons
    private TakeButton cameraButton;
    private TakeButton galleryButton;

    DetectView(ViewsProcessing viewsProcessing, String background){
        this.viewsProcessing = viewsProcessing;
        this.background = viewsProcessing.loadImage(background);
        init();
    }

    private void init(){
        cameraButton = new TakeButton(viewsProcessing, viewsProcessing.width-300, viewsProcessing.height-300, "camera_button.png");
        galleryButton = new TakeButton(viewsProcessing, 100, viewsProcessing.height-300, "gallery_button.png");
    }

    public void draw(){
        //draws image in background
        viewsProcessing.image(background, 0, 0, viewsProcessing.width, viewsProcessing.height);
        //draws buttons
        cameraButton.draw();
        galleryButton.draw();

        //if one button was pressed, start an intent to pick an image
        if(cameraButton.isPressed()){
            cameraButton.setPressed(false);
            viewsProcessing.noLoop();
            viewsProcessing.getMainActivity().getViewsFragment().openCamera();
        }
        if(galleryButton.isPressed()){
            galleryButton.setPressed(false);
            viewsProcessing.noLoop();
            viewsProcessing.getMainActivity().getViewsFragment().openGallery();
        }
    }

}
