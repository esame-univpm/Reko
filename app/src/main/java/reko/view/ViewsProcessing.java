package reko.view;

import processing.core.PApplet;

public class ViewsProcessing extends PApplet {

    //object of main activity
    private MainActivity mainActivity;

    //views
    private HomeView homeView;
    private DetectView labelView;
    private DetectView faceView;
    private DetectView textView;


    private int numberView;    //number of the view to display
    private boolean backPressed;    //true if user press the back button

    ViewsProcessing(MainActivity mainActivity){
        this.mainActivity = mainActivity;   //passes the instance of the main activity
    }

    public void setNumberView(int numberView){
        this.numberView = numberView;
    }

    public int getNumberView(){
        return numberView;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    @Override
    public void setup() {
        numberView = 0; //on start, display home view
        //create objects of various views
        homeView = new HomeView(this);
        labelView = new DetectView(this, "label_background.jpeg");
        faceView = new DetectView(this, "face_background.jpeg");
        textView = new DetectView(this, "text_background.jpeg");
    }

    @Override
    public void draw() {
        if(!backPressed){
            switch (numberView){
                case 0:{
                    homeView.draw();
                    break;
                }
                case 1:{
                    labelView.draw();
                    break;
                }
                case 2:{
                    faceView.draw();
                    break;
                }
                case 3:{
                    textView.draw();
                    break;
                }
            }
        }
        else{
            homeView.backAnimation(numberView);
        }
    }

    public void setBackPressed(boolean backPressed) {
        this.backPressed = backPressed;
    }
}
