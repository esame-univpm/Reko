package reko.processing;

import android.widget.Toast;

import java.util.List;

import processing.core.PImage;
import reko.model.DetectedText;

/**
 * The TextResult class displays to the user the results of Detect Text provided by AWS Rekognition
 */
public class TextResult extends MainView {

    private PImage image = null;

    List<DetectedText> results;

    public TextResult(ViewsController viewsController) {
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
            for(DetectedText text : results){
                viewsController.fill(255);
                viewsController.textSize(24);
                viewsController.text(text.getText()+" "+text.getConfidence()+"%", text.getTopLeft()[0]+50, text.getTopLeft()[1]-50);
                viewsController.stroke(255);
                viewsController.strokeWeight(8);
                viewsController.line(text.getTopLeft()[0], text.getTopLeft()[1], text.getTopRight()[0], text.getTopRight()[1]); //over line
                viewsController.line(text.getTopLeft()[0], text.getTopLeft()[1], text.getBottomLeft()[0], text.getBottomLeft()[1]); //left line
                viewsController.line(text.getBottomLeft()[0], text.getBottomLeft()[1], text.getBottomRight()[0], text.getBottomRight()[1]); //under line
                viewsController.line(text.getTopRight()[0], text.getTopRight()[1], text.getBottomRight()[0], text.getBottomRight()[1]); //right line
            }
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

    /**
     * Sets the list of results
     * @param results
     */
    public void setResults(List<DetectedText> results) {
        this.results = results;
    }
}
