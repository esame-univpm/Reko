package com.esameUNIVPM.reko;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Objects;

public class CameraActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkPermission();
    }

    //Check if camera permission is already granted or not
    private void checkPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //if is not granted, ask user for it
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }
        else{
            //if is granted, open camera
            Intent openCamera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(openCamera, 1);
        }
    }

    /**
     * This method define what happens when a permission is requested from the user
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1) {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //If user press "Allow"

                    //Send feedback on the user
                    Toast.makeText(this, "Camera permission granted", Toast.LENGTH_LONG).show();

                    //Open camera
                    Intent openCamera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(openCamera, 1);
                }
                else {
                    //If user press "Deny", send feedback to the user and go back to the main view
                    Toast.makeText(this, "Camera permission denied", Toast.LENGTH_LONG).show();
                    backMainActivity();
                }
        }
    }

    /**
     * This method define the result of this activity, the image which is captured from the camera
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK)
        {
            //capture image
            Bitmap selectedImage = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");

            //get ByteArray from Bitmap
            PickedImage pickedImage = new PickedImage();
            pickedImage.convertBitmap(selectedImage);
            byte[] image = pickedImage.getImageArray();

            Intent imageActivity = new Intent(this, ImageActivity.class);
            imageActivity.putExtra("byteArray", image);
            startActivity(imageActivity);


        }
    }

    //This method is used to back in the main view
    public void backMainActivity(){
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }
}
