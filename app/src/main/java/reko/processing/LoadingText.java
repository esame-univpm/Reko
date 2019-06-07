package reko.processing;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClient;
import com.amazonaws.services.rekognition.model.DetectTextRequest;
import com.amazonaws.services.rekognition.model.DetectTextResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.TextDetection;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import reko.model.DetectedText;
import reko.model.ManageImage;

/**
 * The LoadingText class is used to load the result of a detect text request.
 */
public class LoadingText extends MainView{

    private byte[] imageBytes;

    List<DetectedText> results = new ArrayList<>();

    private int setLoadingBar = 1;

    public LoadingText(ViewsController viewsController) {
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
        //displays loading bar and feedback text
        viewsController.fill(255);
        viewsController.textSize(50);
        viewsController.text("Loading...", viewsController.width/2-200, viewsController.height/2+270);
        viewsController.noStroke();
        viewsController.rect(viewsController.width/2-180, viewsController.height/2+350, setLoadingBar*120, 20);
    }

    /**
     * Starts a thread to send a request to AWS Rekognition and then get the results
     */
    public void startDetect(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(viewsController.getMainActivity().getViewsFragment().getContext(), "eu-west-1:267892c4-f5d4-499a-b326-47316cd1366b", Regions.EU_WEST_1);
                AmazonRekognition rekognitionClient = new AmazonRekognitionClient(credentialsProvider);
                setLoadingBar = 2;
                DetectTextRequest request = new DetectTextRequest().withImage(new Image().withBytes(ByteBuffer.wrap(imageBytes)));
                DetectTextResult result = rekognitionClient.detectText(request);
                List<TextDetection> textDetections = result.getTextDetections();
                setLoadingBar = 3;
                createResults(textDetections);  //create results list
                ((TextResult) viewsController.getViewsMap().get(9)).setResults(results);
                setLoadingBar = 4;
                viewsController.setNumberView(9);
            }
        });
        t.start();
    }

    private void createResults(List<TextDetection> textDetections){
        for(TextDetection textDetection : textDetections){
            if(textDetection.getType().equals("LINE")){
                DetectedText result = new DetectedText();
                result.setText(textDetection.getDetectedText());
                result.setConfidence(Math.round(textDetection.getConfidence()));
                result.setTopLeft(new int[] {Math.round(textDetection.getGeometry().getPolygon().get(0).getX()*1080), Math.round(textDetection.getGeometry().getPolygon().get(0).getY()*1920)});
                result.setTopRight(new int[] {Math.round(textDetection.getGeometry().getPolygon().get(1).getX()*1080), Math.round(textDetection.getGeometry().getPolygon().get(1).getY()*1920)});
                result.setBottomRight(new int[] {Math.round(textDetection.getGeometry().getPolygon().get(2).getX()*1080), Math.round(textDetection.getGeometry().getPolygon().get(2).getY()*1920)});
                result.setBottomLeft(new int[] {Math.round(textDetection.getGeometry().getPolygon().get(3).getX()*1080), Math.round(textDetection.getGeometry().getPolygon().get(3).getY()*1920)});
                results.add(result);
            }
        }
    }

    /**
     * This method sets the image that will be showed and the byte array that will be send to AWS Rekognition
     * @param pathSelectedImage
     */
    public void setImage(String pathSelectedImage) {
        if(pathSelectedImage!=null){
            this.imageBytes = ManageImage.imageToByteArray(pathSelectedImage);
            ((TextResult) viewsController.getViewsMap().get(9)).setImage(ManageImage.imageToPImage(pathSelectedImage));
            startDetect();
        }
    }

}
