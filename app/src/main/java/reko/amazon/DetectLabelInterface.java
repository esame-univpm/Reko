package reko.amazon;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunction;

import java.util.List;

public interface DetectLabelInterface {

    @LambdaFunction
    List<String> detectLabels(List<byte[]> byteList);
}
