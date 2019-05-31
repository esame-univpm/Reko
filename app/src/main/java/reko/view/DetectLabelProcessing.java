package reko.view;

import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;
import processing.core.PImage;
import reko.model.*;

public class DetectLabelProcessing extends PApplet {

    //object of main activity
    private MainActivity mainActivity;

    //path of the image selected
    private String pathSelectedImage;

    //image selected
    private PImage image;

    //true if results are ready
    private boolean flagReady;

    //stateResult
    private int status = 0;

    //recognition result
    List<DetectLabelResult> results = new ArrayList<>();

    //bounding boxes
    List<LabelBox> boxes = new ArrayList<>();

    DetectLabelProcessing(MainActivity mainActivity){
        this.mainActivity = mainActivity;   //passes the instance of the main activity
    }

    @Override
    public void setup() {
        pathSelectedImage = mainActivity.getDetectLabelFragment().getPathSelectedImage();
        image = new ManageImage().setImage(pathSelectedImage);
        flagReady = false;
        startDetectLabel();
    }

    @Override
    public void draw() {

        if(!flagReady){
            image(image, 0, 0, width, height);
            switch (status){
                case 0:{
                    fill(255);
                    rect(0, 0, 20, 200);
                    break;
                }
                case 1:{
                    fill(255);
                    rect(0, 0, 600, 200);
                    break;
                }
            }
        }
        else{
            image(image, 0, 0, width, height);
            for(LabelBox box : boxes){
                box.draw();
            }
        }


    }

    //starts a thread that returns the result of detect labels
    private void startDetectLabel(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //creates byte array from the image and add it in a list
                List<byte[]> images = new ArrayList<>();
                images.add(new ManageImage().imageToByteArray(pathSelectedImage));
                //send the byte array list to Amazon lambda service
                DetectLabelLambda detectLabelLambda = new DetectLabelLambda(mainActivity.getDetectLabelFragment().getContext());
                detectLabelLambda.startDetectLabels(images);
                while (detectLabelLambda.getResults().isEmpty()){}  //wait until the result are ready
                //return the results
                List<String> result = detectLabelLambda.getResults();
                status = 1;
                for(String str : result){
                    String[] strings = str.split(":");
                    String name = strings[0];
                    boolean background;
                    Float height;
                    Float width;
                    Float top;
                    Float left;
                    float confidence;
                    if(strings[1].equals("true")){
                        background = true;
                        height = null;
                        width = null;
                        top = null;
                        left = null;
                        confidence = Float.parseFloat(strings[6]);
                    }
                    else{
                        background = false;
                        height = Float.parseFloat(strings[2]);
                        width = Float.parseFloat(strings[3]);
                        top = Float.parseFloat(strings[4]);
                        left = Float.parseFloat(strings[5]);
                        confidence = Float.parseFloat(strings[6]);
                        boxes.add(new LabelBox(mainActivity.getDetectLabelFragment().getDetectLabelProcessing(), new DetectLabelResult(name, background, height, width, top, left, confidence)));
                    }
                    results.add(new DetectLabelResult(name, background, height, width, top, left, confidence));
                }
                status=1;
                flagReady = true;
            }
        });
        //start thread
        thread.start();
    }

}
