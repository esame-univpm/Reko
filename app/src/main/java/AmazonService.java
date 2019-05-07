import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClient;

import java.nio.ByteBuffer;

public class AmazonService {
    AmazonRekognition rekognitionClient;
    public void init(){
        AWSCredentials credentials = new BasicAWSCredentials("AKIA4SJKCQLZ622NL5O5","2t79k+kUJBTwGOfyhrBpRYjD+cNRzIXOOPah2tHZ");
        rekognitionClient = new AmazonRekognitionClient(credentials);
        rekognitionClient.setRegion(Region.getRegion(Regions.EU_WEST_1));
    }
    public void startService(ByteBuffer bytebuffer){}


}
