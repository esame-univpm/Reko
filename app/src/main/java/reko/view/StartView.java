package reko.view;

import processing.core.PApplet;
import processing.core.PImage;

public class StartView extends PApplet {

    private MainActivity mainActivity;    //instance of fragment in which this sketch runs
    private PImage background;  //image on background
    private PImage reload;  //reload icon
    private int loadingBarStep = 1; //steps of the loading bar, on create it starts from 1

    StartView(MainActivity mainActivity){
        this.mainActivity = mainActivity;    //passes the instance of the main activity
    }

    @Override
    public void setup() {
        background = super.loadImage("start_background.png"); //load background image from assets directory
        reload = super.loadImage("reload.png");
    }

    @Override
    public void draw() {
        super.frameRate(3); //set frame rate at 10 fps
        super.image(background, 0, 0, super.width, super.height);   //show background image

        switch(loadingBarStep){
            case 1:{

                //shows a text feedback to the user
                super.textSize(50); //size of text
                super.text("Check permissions", super.width/2-200, super.height/2+270); //string and position of the text

                //draws loading bar
                super.noStroke();   //remove strokes of the bar
                super.fill(255);    //color the loading bar
                super.rect(super.width/2-200, super.height/2+350, 150, 20); //shows the bar
                mainActivity.getStartFragment().checkPermissions();   //check permissions

                break;
            }
            case 2:{
                super.textSize(50);
                super.text("Check connection", super.width/2-200, super.height/2+270);
                super.noStroke();
                super.fill(255);
                super.rect(super.width/2-200, super.height/2+350, 350, 20);
                mainActivity.getStartFragment().checkConnection();    //check internet connection
                break;
            }
            case 3:{
                //if there isn't internet connection, send a feedback to the user
                super.textSize(50);
                super.text("Please check internet \nconnection and reload", super.width/2-250, super.height/2+200);
                super.fill(255);
                super.image(reload, super.width/2-75, super.height/2+275, 150, 150);   //display a button which allows the user to reload
                break;
            }
            case 4:{
                super.noStroke();
                super.fill(255);
                super.rect(super.width/2-200, super.height/2+350, 400, 20);

                //switch to ViewsFragment
                mainActivity.getStartFragment().setActive(false);
                mainActivity.getViewsFragment().setActive(true);
                mainActivity.getStartFragment().getFragmentManager().beginTransaction().replace(R.id.frameLayout, mainActivity.getViewsFragment()).commit();
                break;
            }
        }
    }

    //set loadingBarStep value
    public void setLoadingBarStep(int loadingBarStep){
        this.loadingBarStep = loadingBarStep;
    }

    //this method manage touches on the screen
    public void mousePressed() {
        if(loadingBarStep == 3){
            if(super.mouseX > super.width/2-75 && super.mouseX < super.width/2+125){
                if(super.mouseY > super.height/2+275 && super.mouseY < super.height/2+425){
                    //if the user presses the reload button, it re-checks the internet connection
                    loadingBarStep = 2;
                }
            }
        }
    }

}
