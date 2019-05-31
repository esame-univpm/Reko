package reko.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunctionException;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.amazonaws.regions.Regions;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class DetectLabelLambda {

    private Context context;
    private List<String> results = new ArrayList<>();

    public DetectLabelLambda(Context context){
        this.context = context;
    }

    public List<String> getResults() {
        return results;
    }

    public void startDetectLabels(List<byte[]> imageBytes){
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(context, "eu-west-1:a5008d71-c74e-41c8-a90e-1f0ee904bb40", Regions.EU_WEST_1);
        LambdaInvokerFactory factory =  LambdaInvokerFactory.builder().context(context).region(Regions.EU_WEST_1).credentialsProvider(credentialsProvider).build();
        final DetectLabelInterface lambdaInterface = factory.build(DetectLabelInterface.class);

        new AsyncTask<List<byte[]>, Void, List<String>>(){

            @Override
            protected List<String> doInBackground(List<byte[]> ... parameters) {
                try {
                    return lambdaInterface.detectLabels(parameters[0]);
                } catch (LambdaFunctionException e){
                    Log.e(TAG, "Failed porcoddio", e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List<String> str) {
                super.onPostExecute(str);
                results = str;
            }
        }.execute(imageBytes);
    }

}
