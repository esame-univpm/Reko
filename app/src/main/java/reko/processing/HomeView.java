package reko.processing;

/**
 * The HomeView class manage the home view of Reko app.
 * The home view contain the buttons that allow the user to navigate through the other views.
 */
public class HomeView extends MainView {

    //instantiates the buttons
    private UserButton labelButton;   //this button corresponds to label view
    private UserButton faceButton = new UserButton(viewsController);    //this button corresponds to face view
    private UserButton textButton = new UserButton(viewsController);    //this button corresponds to text view

    /**
     * Constructor of this class, as argument requires a ViewsController instance.
     * @param viewsController
     */
    public HomeView(ViewsController viewsController){
        super(viewsController);
        init();
    }

    @Override
    public void init() {
        super.init();
        //sets label button
        labelButton = new UserButton(viewsController) {
            @Override
            public void onClick(){
                viewsController.setNumberView(-1);
            }

            @Override
            protected boolean isPressed() {
                if(pApplet.mousePressed){   //if there is a touch on the screen
                    if(pApplet.mouseX>positionX-75 && pApplet.mouseX<positionX-75+width){ //if this touch is inside the width of the button
                        if(pApplet.mouseY>positionY-75 && pApplet.mouseY<positionY-75+height){    //if this touch is inside the height of the button
                            return true;
                        }
                    }
                }
                return false;
            }
        };
        labelButton.setButtonImage(viewsController.loadImage("detect_labels_button.png"));  //sets image of the button
        labelButton.setColorRGB(new int[] {254, 36, 0});    //sets the color of the button
        labelButton.setPosition(250, viewsController.height/2-50);  //sets the position
        labelButton.setSize(150, 150);  //sets the size
        //sets faces button
        faceButton = new UserButton(viewsController) {
            @Override
            public void onClick(){
                viewsController.setNumberView(-2);
            }
            @Override
            protected boolean isPressed() {
                if(pApplet.mousePressed){   //if there is a touch on the screen
                    if(pApplet.mouseX>positionX-75 && pApplet.mouseX<positionX-75+width){ //if this touch is inside the width of the button
                        if(pApplet.mouseY>positionY-75 && pApplet.mouseY<positionY-75+height){    //if this touch is inside the height of the button
                            return true;
                        }
                    }
                }
                return false;
            }
        };
        faceButton.setButtonImage(viewsController.loadImage("detect_faces_button.png"));  //sets image of the button
        faceButton.setColorRGB(new int[] {231,80,66});    //sets the color of the button
        faceButton.setPosition(250, viewsController.height/2+250);  //sets the position
        faceButton.setSize(150, 150);  //sets the size

        //sets text button
        textButton = new UserButton(viewsController) {
            @Override
            public void onClick(){
                viewsController.setNumberView(-3);
            }
            @Override
            protected boolean isPressed() {
                if(pApplet.mousePressed){   //if there is a touch on the screen
                    if(pApplet.mouseX>positionX-75 && pApplet.mouseX<positionX-75+width){ //if this touch is inside the width of the button
                        if(pApplet.mouseY>positionY-75 && pApplet.mouseY<positionY-75+height){    //if this touch is inside the height of the button
                            return true;
                        }
                    }
                }
                return false;
            }
        };
        textButton.setButtonImage(viewsController.loadImage("detect_text_button.png"));  //sets image of the button
        textButton.setColorRGB(new int[] {247, 134, 56});    //sets the color of the button
        textButton.setPosition(250, viewsController.height/2+550);  //sets the position
        textButton.setSize(150, 150);  //sets the size
    }

    @Override
    public void draw() {
        super.draw();
        //draws title
        viewsController.textSize(200);    //sets size of title
        viewsController.fill(viewsController.color(50, 50, 50));    //sets color of title
        viewsController.text("Home", viewsController.width/2-270, 350); //shows title

        //draw texts into black boxes
        textBox("Detect Labels", viewsController.height/2-100);
        textBox("Detect Faces", viewsController.height/2+200);
        textBox("Detect Texts", viewsController.height/2+500);


        //draw button
        labelButton.drawButton();
        faceButton.drawButton();
        textButton.drawButton();
    }

    /**
     * Draws a box next the button in which there is an explain text
     * @param str
     * @param positionY
     */
    private void textBox(String str, int positionY){
        viewsController.fill(viewsController.color(50, 50, 50));
        viewsController.rect(400, positionY, 500, 100, 100);
        viewsController.textSize(60);
        viewsController.fill(255);
        viewsController.text(str, 470, positionY+70);
    }
}
