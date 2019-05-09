package reko.controller;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import processing.android.PFragment;

public class CameraFragment extends Fragment implements UploadImage {

    //Absolute path of selected image
    private String pathSelectedImage;

    //image in Uri format
    private Uri imageUri;

    //request codes
    private final int CAMERA_PERMISSION = 2;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //check permission for capture an image
        checkPermissions(CAMERA_PERMISSION);
    }

    //Check if app has permission to capture and store image from camera
    @Override
    public void checkPermissions(int requestCode) {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            //if is not granted, ask user for it
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, CAMERA_PERMISSION);
        }
        else{
            Toast.makeText(getContext(), "Camera permissions are already granted", Toast.LENGTH_LONG).show();
            //if is granted open camera

            openCamera();
        }
    }

    //this method define what happens when a permission is requested from the user
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == CAMERA_PERMISSION){
            //manage write permission
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED){

                //Send feedback on the user
                Toast.makeText(getContext(), "Write permission is not granted", Toast.LENGTH_LONG).show();

                //if write permission is not granted, image cannot be capture from the camera so close this fragment and return to home
                backToHome();
            }
            else{
                //Send feedback on the user
                Toast.makeText(getContext(), "Write permission granted", Toast.LENGTH_LONG).show();
            }

            if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                //If user press "Allow"

                //Send feedback on the user
                Toast.makeText(getContext(), "Camera permission granted", Toast.LENGTH_LONG).show();

                //Open camera
                openCamera();
            }
            else {
                //If user press "Deny", send feedback to the user and go back to the main view
                Toast.makeText(getContext(), "Camera permission denied", Toast.LENGTH_LONG).show();
                backToHome();
            }
        }
    }


    //this method define the result of this activity, ie the image which is captured from the camera
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if (requestCode == CAMERA_PERMISSION && resultCode == Activity.RESULT_OK)
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

    //this method calls an Intent to open camera
    private void openCamera(){

        //set format of image that will be captured
        setImageUri();

        //open camera through an intent
        Intent openCamera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        //pass the Uri image in the new intent
        openCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

        //start intent
        startActivityForResult(openCamera, CAMERA_PERMISSION);
    }

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

    //create new Uri image
    public void setImageUri(){

        //set the values of the new image using ContentValues
        ContentValues values = new ContentValues();

        //create new Uri image
        imageUri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }

    @Override
    public void setPathSelectedImage(Intent data) {

        Bitmap selectedImage = null;
        File file = null;
        try {
            //capture image and convert it into Bitmap image, then open a temporary file to store the captured image
            selectedImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);

            //the name of temporary file will be "Image.jpg"
            file = File.createTempFile("Image", ".jpg", getActivity().getFilesDir());

            //use a FileOutputStream like a support to execute an image compression
            FileOutputStream out = new FileOutputStream(file);
            selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, out);

            //update FileOutputStream and close it
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //set the path of selected image
        pathSelectedImage = file.getPath();
    }

}
