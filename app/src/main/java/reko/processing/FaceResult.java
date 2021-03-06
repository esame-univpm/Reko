package reko.processing;

import android.widget.Toast;

import processing.core.PImage;

/**
 * The FaceResult class displays to the user the results of Detect Faces provided by AWS Rekognition
 */
public class FaceResult extends MainView{

    private PImage image = null;

    public FaceResult(ViewsController viewsController) {
        super(viewsController);
        init();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void draw() {
        super.draw();
        if(image!=null){
            viewsController.image(image, 0, 0, viewsController.width, viewsController.height);
        }
        else{
            Toast.makeText(viewsController.getMainActivity().getViewsFragment().getContext(), "Image not found", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Sets the image that will be showed
     * @param image
     */
    public void setImage(PImage image) {
        this.image = image;
    }
}
