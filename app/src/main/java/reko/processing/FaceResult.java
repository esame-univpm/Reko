package reko.processing;

import android.widget.Toast;

import processing.core.PImage;
import reko.model.ManageImage;

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

    public void setImage(String pathSelectedImage) {
        if(pathSelectedImage!=null){
            this.image = ManageImage.imageToPImage(pathSelectedImage);
        }
    }
}
