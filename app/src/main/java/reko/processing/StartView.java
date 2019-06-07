package reko.processing;

import processing.core.PApplet;
import processing.core.PImage;
import reko.MainActivity;
import reko.R;
import reko.fragment.StartFragment;

public class StartView extends PApplet {

    private StartFragment startFragment;    //instance of fragment in which this sketch runs

    private PImage background;  //image on background

    private PImage reload;  //reload icon

    private int loadingBarStep = 1; //steps of the loading bar, on create it starts from 1

    /**
     * Constructor of this class, as argument receive a MainActivity object.
     * @param startFragment
     */
    public StartView(StartFragment startFragment){
        this.startFragment = startFragment;   //sets mainActivity object
    }

    /**
     * This method sets the image that will be used in this view
     */
    @Override
    public void setup() {
        background = loadImage("home_background.jpg");  //sets background image
        reload = loadImage("reload.png"); //load image for reload button
    }

    /**
     * Draws this view and control if the permissions are granted and if there is an internet connection.
     */
    @Override
    public void draw() {
        image(background, 0, 0, width, height); //display the image on background

        super.frameRate(3); //sets frame rate at 3 fps

        //draws title
        super.textSize(200);    //sets size of title
        super.fill(super.color(50, 50, 50));    //sets color of title
        super.text("Reko", super.width/2-220, 350); //shows title

        //draws the loading bar with relative feedback text
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
                startFragment.getFragmentManager().beginTransaction().replace(R.id.frameLayout, startFragment.getMainActivity().getViewsFragment()).commit();
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
                startFragment.checksPermissions();   //starts method to checks permission
                break;
            }
            case 2:{
                text = "Check connection";  //sets feedback text
                startFragment.checksConnection();    //starts method to checks internet connection
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
