package reko.processing;

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
        background = viewsProcessing.loadImage("home_background.jpg"); //set background image
        //create buttons
        labelButton = new OpenViewButton(viewsProcessing, 250, viewsProcessing.height/2-50, "detect_labels_button.png", new int[] {254, 36, 0});
        faceButton = new OpenViewButton(viewsProcessing, 250, viewsProcessing.height/2+250, "detect_faces_button.png", new int[] {231,80,66});
        textButton = new OpenViewButton(viewsProcessing, 250, viewsProcessing.height/2+550, "detect_text_button.png", new int[] {247, 134, 56});
    }

    public void draw(){
        //draws title
        viewsProcessing.textSize(200);    //sets size of title
        viewsProcessing.fill(viewsProcessing.color(50, 50, 50));    //sets color of title
        viewsProcessing.text("Home", viewsProcessing.width/2-270, 350); //shows title

        //draw texts into black boxes
        textBox("Detect Labels", viewsProcessing.height/2-100);
        textBox("Detect Faces", viewsProcessing.height/2+200);
        textBox("Detect Texts", viewsProcessing.height/2+500);

        //draw button
        labelButton.show();
        faceButton.show();
        textButton.show();

        //change view
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

    private void textBox(String str, int positionY){
        viewsProcessing.fill(viewsProcessing.color(50, 50, 50));
        viewsProcessing.rect(400, positionY, 500, 100, 100);
        viewsProcessing.textSize(60);
        viewsProcessing.fill(255);
        viewsProcessing.text(str, 470, positionY+70);
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
