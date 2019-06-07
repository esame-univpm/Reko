package reko.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import reko.MainActivity;
import reko.R;
import reko.processing.StartView;

/**
 * The StartFragment class is used to load conditions in which Reko can run, all of this is showed to the user using StartView sketch.
 */
public class StartFragment extends MainFragment {

    private StartView startView;     //instance of Processing sketch which runs on this fragment

    public StartFragment(){
        super();
    }

    /**
     * When this fragment is created, it runs StartView sketch
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //runs startView, the Processing sketch
        startView = new StartView(this);
        setSketch(startView);
        setView(getActivity().findViewById(R.id.frameLayout), getActivity());
    }

    /**
     * Checks if the permissions which is necessary to use this app are already granted. If they are not granted, send an user request to granted them.
     */
    public void checksPermissions(){
        //check status of the permissions
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            //if permissions are not already granted, send to user a request for do it

            //stops Processing's draw loop to request the permissions
            startView.noLoop();
            //if permissions are not granted, request they
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        else{
            //if permission are already granted

            //the loading bar advances
            startView.setLoadingBarStep(2);
        }
    }

    /**
     * Checks if there is an available Internet connection.
     */
    public void checksConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected()){
            //the loading bar advances
            startView.setLoadingBarStep(4);
        }
        else{
            //display button to try connecting again
            startView.setLoadingBarStep(3);
        }
    }

    /**
     * This method define what happens when a permission is requested to the user
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if(grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED || grantResults[2] != PackageManager.PERMISSION_GRANTED){
            //this app must have the permissions so, if they are not granted, force close the app
            Toast.makeText(getContext(), "Permissions must be granted to use this app", Toast.LENGTH_LONG).show();
            getActivity().finish();
        }
        else{
            //if permission are granted, the loading bar advances
            startView.loop();
            startView.setLoadingBarStep(2);
        }
    }

}
