package reko.model;

import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;

import java.nio.ByteBuffer;
import java.util.List;

public class DetectLabels extends AmazonService {
    private List<Label> labels ;
    DetectLabels (){}


    @Override
    public void startService(ByteBuffer bytebuffer){
        DetectLabelsRequest request = new DetectLabelsRequest().withImage(new Image().withBytes(bytebuffer)).withMaxLabels(10).withMinConfidence(77F);
        DetectLabelsResult result = rekognitionClient.detectLabels(request);
        labels = result.getLabels();
    }
}

