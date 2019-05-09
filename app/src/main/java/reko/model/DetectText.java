package reko.model;

import com.amazonaws.services.rekognition.model.DetectTextRequest;
import com.amazonaws.services.rekognition.model.DetectTextResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.TextDetection;

import org.w3c.dom.Text;

import java.nio.ByteBuffer;
import java.util.List;

public class DetectText extends AmazonService {
            private List<TextDetection> textDetections ;
            DetectText (){}

            @Override
            public void startService(ByteBuffer bytebuffer){
                DetectTextRequest request = new DetectTextRequest().withImage(new Image().withBytes(bytebuffer));
                DetectTextResult result = rekognitionClient.detectText(request);
                textDetections = result.getTextDetections();
            }
        }


