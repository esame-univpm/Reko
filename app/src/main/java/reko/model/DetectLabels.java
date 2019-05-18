package reko.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunctionException;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.amazonaws.regions.Regions;
import java.util.List;

import static android.content.ContentValues.TAG;


public class DetectLabels {

    private Context context;
    private List<LabelResult> labelResultsList;
    private boolean flag;

    public DetectLabels(Context context){
        this.context=context;
        flag=false;
    }

    public void startDetectLabels(List<byte[]> imageBytes){
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(context, "eu-west-1:a5008d71-c74e-41c8-a90e-1f0ee904bb40", Regions.EU_WEST_1);
        LambdaInvokerFactory factory =  LambdaInvokerFactory.builder().context(context).region(Regions.EU_WEST_1).credentialsProvider(credentialsProvider).build();
        final LambdaInterface lambdaInterface = factory.build(LambdaInterface.class);

        new AsyncTask<List<byte[]>, Void, String>(){
            @Override
            protected String doInBackground(List<byte[]> ... byteLists) {
                try {
                    return lambdaInterface.startRekognition(byteLists[0]);
                } catch (LambdaFunctionException e){
                    Log.e(TAG, "Failed porcoddio", e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String str) {
                super.onPostExecute(str);
                //labelResultsList=labelResults;
                System.out.println("Stampo");

                System.out.println(str);

                System.out.println("finito");

                flag=true;

            }
        }.execute(imageBytes);


    }
}
