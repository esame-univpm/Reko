package reko.processing;

import processing.core.PImage;

/**
 * The TransitionView class shows the animation when a view is changed with another view.
 */
public class TransitionView extends MainView {

    private int diameter;

    private int count;

    private int[] colorRGB;     //color of the button, in RGB format. It will be the color both button and animations (default color is white)

    private PImage buttonImage = null;     //image that will be displayed over the button

    //coordinates of the button
    private int positionX = 0;  //horizontal position of button in pixels
    private int positionY = 0;  //vertical position of the button in pixels

    private boolean back; //if true the animation is backwards

    private int numberView = 0; //number of the that will be showed when the transition will be finished

    private int dimension;  //dimension max or min of the ellipse

    public TransitionView(ViewsController viewsController, int[] colorRGB, boolean back, int diameter, int dimension, int numberView, int positionX, int positionY) {
        super(viewsController);
        this.colorRGB = colorRGB;
        this.back = back;
        this.diameter = diameter;
        this.dimension = dimension;
        this.count = diameter;
        this.numberView = numberView;
        this.positionX = positionX;
        this.positionY = positionY;
        init();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void draw() {
        super.draw();

        viewsController.fill(viewsController.color(colorRGB[0], colorRGB[1], colorRGB[2])); //sets the color

        viewsController.ellipse(positionX, positionY, count, count);
        if(buttonImage!=null){
            viewsController.image(buttonImage, positionX-75, positionY-75, 150, 150);
        }
        if(!back){
            count+=150;
            if(count>dimension){
                count = diameter;
                viewsController.setNumberView(numberView);
            }
        }
        else{
            count-=150;
            if(count<dimension){
                count = diameter;
                viewsController.setNumberView(numberView);
            }
        }

    }
}
