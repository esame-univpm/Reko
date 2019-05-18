package reko.model;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunction;

import java.util.List;

public interface LambdaInterface {

    @LambdaFunction
    String startRekognition(List<byte[]> byteLists);

}
