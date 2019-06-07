package reko.processing;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClient;
import reko.model.ManageImage;

/**
 * The LoadingLabel class is used to load the result of a detect label request.
 */
public class LoadingLabel extends MainView {

    private byte[] imageBytes;

    private int setLoadingBar = 1;

    public LoadingLabel(ViewsController viewsController) {
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
        viewsController.text("Text", viewsController.width/2-200, viewsController.height/2+270);
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
                CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(viewsController.getMainActivity().getViewsFragment().getContext(), "eu-west-1:a5008d71-c74e-41c8-a90e-1f0ee904bb40", Regions.EU_WEST_1);
                AmazonRekognition rekognitionClient = new AmazonRekognitionClient(credentialsProvider);

            }
        });
        t.start();
    }

    /**
     * This method sets the image that will be showed and the byte array that will be send to AWS Rekognition
     * @param pathSelectedImage
     */
    public void setImage(String pathSelectedImage) {
        if(pathSelectedImage!=null){
            this.imageBytes = ManageImage.imageToByteArray(pathSelectedImage);
            ((LabelResult) viewsController.getViewsMap().get(7)).setImage(ManageImage.imageToPImage(pathSelectedImage));
            startDetect();
        }
    }
}
