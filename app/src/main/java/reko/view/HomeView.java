package reko.view;

import processing.core.PImage;

public class HomeView {

    //instance of Processing's sketch in use
    private ViewsProcessing viewsProcessing;

    //buttons
    private OpenViewButton labelButton;
    private OpenViewButton faceButton;
    private OpenViewButton textButton;

    //image in background
    private PImage background;

    HomeView(ViewsProcessing viewsProcessing){
        this.viewsProcessing = viewsProcessing;
        init();
    }

    //this method contains all the things that must be instantiate before draw they
    private void init(){
        background = viewsProcessing.loadImage("home_background.jpeg"); //set background image
        //create buttons
        labelButton = new OpenViewButton(viewsProcessing, viewsProcessing.width/2, viewsProcessing.height/2+150, "detect_labels_button.png", new int[] {82,150,200});
        faceButton = new OpenViewButton(viewsProcessing, viewsProcessing.width/2-303, viewsProcessing.height/2+675, "detect_faces_button.png", new int[] {231,80,66});
        textButton = new OpenViewButton(viewsProcessing, viewsProcessing.width/2+303, viewsProcessing.height/2+675, "detect_text_button.png", new int[] {247, 134, 56});
    }

    public void draw(){
        viewsProcessing.image(background, 0, 0, viewsProcessing.width, viewsProcessing.height);

        //draw ellipse in background
        viewsProcessing.noFill();
        viewsProcessing.strokeWeight(1);
        viewsProcessing.stroke(0);
        viewsProcessing.ellipse(viewsProcessing.width/2, viewsProcessing.height/2+500, 700, 700);

        //draw button
        if(!faceButton.isFlagPressed() && !textButton.isFlagPressed()){
            labelButton.show();
        }
        if(!labelButton.isFlagPressed() && !textButton.isFlagPressed()){
            faceButton.show();
        }
        if(!labelButton.isFlagPressed() && !faceButton.isFlagPressed()){
            textButton.show();
        }

        if(labelButton.isChangeView()){
            labelButton.setFlagPressed(false);
            labelButton.setChangeView(false);
            viewsProcessing.setNumberView(1);
        } else if(faceButton.isChangeView()){
            faceButton.setFlagPressed(false);
            faceButton.setChangeView(false);
            viewsProcessing.setNumberView(2);
        } else if(textButton.isChangeView()){
            textButton.setFlagPressed(false);
            textButton.setChangeView(false);
            viewsProcessing.setNumberView(3);
        }


    }

    //draws back animations
    public void backAnimation(int numberView){
        switch (numberView){
            case 1:{
                draw();
                labelButton.backAnimation();
                break;
            }
            case 2:{
                draw();
                faceButton.backAnimation();
                break;
            }
            case 3:{
                draw();
                textButton.backAnimation();
                break;
            }
        }
    }
}
