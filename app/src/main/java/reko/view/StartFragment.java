package reko.view;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;
import processing.android.PFragment;

public class StartFragment extends PFragment {

    //instance of Processing sketch which runs on this fragment
    private StartView startView;

    //if true, this fragment is active
    private boolean active;

    //instance of activity in use
    private MainActivity mainActivity;

    //on create run StartView Processing sketch
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        active = true;

        //runs startView, the Processing sketch
        startView = new StartView(mainActivity);
        setSketch(startView);
        setView(getActivity().findViewById(R.id.frameLayout), getActivity());
    }

    //method to set active value
    public void setActive(boolean active) {
        this.active = active;
    }

    //method to return active value
    public boolean isActive() {
        return active;
    }

    //method to pass instance of the main activity
    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    //checks permissions for use this app
    public void checkPermissions(){
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

    //checks if there is an internet connection
    public void checkConnection(){
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

    //this method define what happens when a permission is requested from the user
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

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
