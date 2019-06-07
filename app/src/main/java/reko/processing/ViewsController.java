package reko.processing;

import java.util.HashMap;

import processing.core.PApplet;
import reko.MainActivity;

/**
 * The ViewsController class manage the various views of Reko app.
 */
public class ViewsController extends PApplet {

    private MainActivity mainActivity;  //instance of the main activity

    private HashMap<Integer, MainView> viewsMap = new HashMap<>();  //map of the view

    private int numberView = 0; //number of the view in use

    public ViewsController(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    /**
     * This method is use to instantiate the view
     */
    @Override
    public void setup() {
        //create Home view
        viewsMap.put(0, new HomeView(this));
        //create LabelRequest
        viewsMap.put(1, new DetectView(this, "detect_labels_button.png", new int[] {254, 36, 0}));
        //create FaceRequest
        viewsMap.put(2, new DetectView(this, "detect_faces_button.png", new int[] {231,80,66}));
        //create FaceRequest
        viewsMap.put(3, new DetectView(this, "detect_text_button.png", new int[] {247, 134, 56}));
        //create transition Home-LabelRequest
        viewsMap.put(-1, new TransitionView(this, new int[] {254, 36, 0}, false, 150, height/2, 1, width/2, height));
        //create transition Home-FaceRequest
        viewsMap.put(-2, new TransitionView(this, new int[] {231,80,66}, false, 150, height/2, 2, width/2, height));
        //create transition Home-TextRequest
        viewsMap.put(-3, new TransitionView(this, new int[] {247, 134, 56},false, 150, height/2, 3, width/2, height));
        //create transition LabelRequest-Home
        viewsMap.put(-4, new TransitionView(this, new int[] {254, 36, 0}, true, height/2, 150, 0, width/2, height));
        //create transition FaceRequest-Home
        viewsMap.put(-5, new TransitionView(this, new int[] {231,80,66}, true, height/2, 150, 0, width/2, height));
        //create transition TextRequest-Home
        viewsMap.put(-6, new TransitionView(this, new int[] {247, 134, 56}, true, height/2, 150, 0, width/2, height));
        //create LabelResult
        viewsMap.put(4, new LabelResult(this));
        //create FaceResult
        viewsMap.put(5, new FaceResult(this));
        //create TextResult
        viewsMap.put(6, new TextResult(this));
    }

    /**
     * Draws the view
     */
    @Override
    public void draw() {
        viewsMap.get(numberView).draw();
    }

    /**
     * This method returns the number of the view in use.
     * @return
     */
    public int getNumberView() {
        return numberView;
    }

    /**
     * This method sets the number of the view that will be showed
     * @param numberView
     */
    public void setNumberView(int numberView) {
        this.numberView = numberView;
    }

    /**
     * Returns MainActivity instance.
     * @return
     */
    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public HashMap<Integer, MainView> getViewsMap() {
        return viewsMap;
    }
}
