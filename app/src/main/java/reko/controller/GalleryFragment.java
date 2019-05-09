package reko.controller;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import processing.android.PFragment;

public class GalleryFragment extends Fragment implements UploadImage{

    //Absolute path of selected image
    private String pathSelectedImage;

    //request code
    private static int READ_STORAGE = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //check permission for read image from gallery
        checkPermissions(READ_STORAGE);
    }

    //Check if app has permission to read storage
    @Override
    public void checkPermissions(int requestCode) {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //if is not granted, ask user for it
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, requestCode);
        }
        else{
            //if is granted, open gallery
            Intent openGallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(openGallery, READ_STORAGE);
        }
    }

    //This method define what happens when a permission is requested from the user
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //If user press "Allow"

                //Send feedback on the user
                Toast.makeText(getContext(), "Gallery permission granted", Toast.LENGTH_LONG).show();

                //Open gallery
                Intent openGallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGallery, READ_STORAGE);
            }
            else {
                //If user press "Deny", send feedback to the user and go back to the main view
                Toast.makeText(getContext(), "Gallery permission denied", Toast.LENGTH_LONG).show();
                backToHome();
            }
        }
    }


    //this method define the result of this fragment, ie the image which is selected from the gallery
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == READ_STORAGE && resultCode == Activity.RESULT_OK)
        {

            //set the path of selected image
            setPathSelectedImage(data);

            //feedback
            Toast.makeText(getContext(), "Load image", Toast.LENGTH_LONG).show();

            //start a new fragment to manage the selected image

            //Instantiate a PFragment object, a fragment of the home view
            MainProcessing mainProcessing = new MainProcessing();
            PFragment pFragment = new PFragment(mainProcessing);

            //pass pathSelectedImage to the new fragment
            Bundle bundle = new Bundle();
            bundle.putString("path",pathSelectedImage);
            pFragment.setArguments(bundle);

            //pass PFragment to MainProcessing
            mainProcessing.setFragmentInUse(pFragment);

            //instantiate a FragmentTransaction object that allows us an easy transaction between fragments
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

            //replace the current fragment with the new fragment
            fragmentTransaction.replace(R.id.frameLayout, pFragment);

            //execute the transaction
            fragmentTransaction.commit();

        }
    }

    //change this fragment with home fragment
    private void backToHome(){
        //Instantiate a PFragment object, a fragment of the home view
        MainProcessing mainProcessing = new MainProcessing();
        PFragment pFragment = new PFragment(mainProcessing);

        //pass PFragment to MainProcessing
        mainProcessing.setFragmentInUse(pFragment);

        //instantiate a FragmentTransaction object that allows us an easy transaction between fragments
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

        //replace the current fragment with the new fragment
        fragmentTransaction.replace(R.id.frameLayout, pFragment);

        //execute the transaction
        fragmentTransaction.commit();
    }

    @Override
    public void setPathSelectedImage(Intent data) {
        //capture image
        Uri selectedImage = data.getData();

        //find path of captured image
        Cursor cursor = getActivity().getContentResolver().query(selectedImage, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);

        //set the path of selected image
        pathSelectedImage = cursor.getString(idx);

    }

}
