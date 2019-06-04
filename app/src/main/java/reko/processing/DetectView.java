package reko.processing;

import processing.core.PImage;

public class DetectView {

    //instance of Processing's sketch in use
    private ViewsProcessing viewsProcessing;

    //image in background
    private PImage background;

    //color
    private int[] colorRGB;

    //buttons
    private TakeButton cameraButton;
    private TakeButton galleryButton;

    DetectView(ViewsProcessing viewsProcessing, String background, int[] colorRGB){
        this.viewsProcessing = viewsProcessing;
        this.background = viewsProcessing.loadImage(background);
        this.colorRGB = colorRGB;
        init();
    }

    private void init(){
        cameraButton = new TakeButton(viewsProcessing, viewsProcessing.width-300, viewsProcessing.height-300, "camera_button.png");
        galleryButton = new TakeButton(viewsProcessing, 100, viewsProcessing.height-300, "gallery_button.png");
    }

    public void draw(){
        viewsProcessing.fill(viewsProcessing.color(colorRGB[0], colorRGB[1], colorRGB[2]));
        viewsProcessing.rect(0, viewsProcessing.height/2-200, viewsProcessing.width, 300);
        viewsProcessing.image(background, viewsProcessing.width/2-150, viewsProcessing.height/2-200, 300, 300);
        viewsProcessing.fill(viewsProcessing.color(50, 50, 50));
        viewsProcessing.rect(0, viewsProcessing.height/2+100, viewsProcessing.width, viewsProcessing.height);

        viewsProcessing.fill(255);
        viewsProcessing.textSize(70);
        viewsProcessing.text("Select image", viewsProcessing.width/2-200, viewsProcessing.height-600);

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
