import com.amazonaws.services.rekognition.model.DetectFacesRequest;
import com.amazonaws.services.rekognition.model.DetectFacesResult;
import com.amazonaws.services.rekognition.model.Face;
import com.amazonaws.services.rekognition.model.FaceDetail;
import com.amazonaws.services.rekognition.model.Image;

import java.nio.ByteBuffer;
import java.util.List;

public class DetectFaces {
    public class DetectFaceDetails extends AmazonService {
        private List<FaceDetail> face ;
        DetectFaceDetails (){}

        @Override
        public void startService(ByteBuffer bytebuffer){
            DetectFacesRequest request = new DetectFacesRequest().withImage(new Image().withBytes(bytebuffer)).withAttributes("ALL", "DEFAULT");
            DetectFacesResult result = rekognitionClient.detectFaces(request);
            face = result.getFaceDetails();
        }
    }
}
