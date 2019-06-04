package reko.processing;

import processing.core.PImage;
import reko.R;

/**
 * The StartView class manage the conditions in which Reko must run.
 *
 * This class checks the app permissions and checks if there is an active internet connection.
 * All of this are displayed to the user thanks to a PApplet script.
 */
public class StartView extends MainProcessing {

    private PImage reload;  //reload icon
    private int loadingBarStep = 1; //steps of the loading bar, on create it starts from 1

    /**
     *Overrides the MainProcessing's setup method
     */
    @Override
    public void setup() {
        super.setup();
        reload = super.loadImage("reload.png"); //load image for reload button
    }

    /**
     * Overrides the MainProcessing's draw method
     */
    @Override
    public void draw() {
        super.draw();   //draws background's image
        super.frameRate(3); //sets frame rate at 3 fps

        //draws title
        super.textSize(200);    //sets size of title
        super.fill(super.color(50, 50, 50));    //sets color of title
        super.text("Reko", super.width/2-220, 350); //shows title

        switch(loadingBarStep){
            default:{
                showLoadingBar(loadingBarStep); //displays loading bar and the relative step
                break;
            }
            case 3:{
                //if there isn't internet connection, send a feedback to the user
                super.fill(255);
                super.textSize(50);
                super.text("Please check internet \nconnection and reload", super.width/2-250, super.height/2+200);
                super.fill(255);
                super.image(reload, super.width/2-75, super.height/2+275, 150, 150);   //display a button which allows the user to reload
                break;
            }
            case 4:{
                //switch to ViewsFragment
                mainActivity.getStartFragment().setActive(false);
                mainActivity.getViewsFragment().setActive(true);
                mainActivity.getStartFragment().getFragmentManager().beginTransaction().replace(R.id.frameLayout, mainActivity.getViewsFragment()).commit();
                break;
            }
        }
    }

    /**
     * Sets the value of loadingBarStep variable
     * @param loadingBarStep
     */
    public void setLoadingBarStep(int loadingBarStep){
        this.loadingBarStep = loadingBarStep;
    }

    /**
     * Displays the loading bar with relative feedback text
     * @param loadingBarStep
     */
    private void showLoadingBar(int loadingBarStep){
        String text = "";
        switch(loadingBarStep){
            case 1:{
                text = "Check permissions"; //sets feedback text
                super.mainActivity.getStartFragment().checkPermissions();   //starts method to checks permission
                break;
            }
            case 2:{
                text = "Check connection";  //sets feedback text
                super.mainActivity.getStartFragment().checkConnection();    //starts method to checks internet connection
                break;
            }
        }
        //displays loading bar and feedback text
        super.fill(255);
        super.textSize(50);
        super.text(text, super.width/2-200, super.height/2+270);
        super.noStroke();
        super.rect(super.width/2-180, super.height/2+350, loadingBarStep*120, 20);
    }

    /**
     * Overrides the PApplet's mousePressed method
     */
    public void mousePressed() {
        if(loadingBarStep == 3){    //if there isn't internet connection
            //if the touch on the screen is inside the bounds
            if(super.mouseX > super.width/2-75 && super.mouseX < super.width/2+125){
                if(super.mouseY > super.height/2+275 && super.mouseY < super.height/2+425){
                    //if the user presses the reload button, it re-checks the internet connection
                    loadingBarStep = 2;
                }
            }
        }
    }
}
