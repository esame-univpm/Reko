package com.esameUNIVPM.reko;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkPermission();
    }

    //Check if gallery permission is already granted or not
    private void checkPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //if is not granted, ask user for it
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        else{
            //if is granted, open gallery
            Intent openGallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(openGallery, 1);
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
                Toast.makeText(this, "Gallery permission granted", Toast.LENGTH_LONG).show();

                //Open camera
                Intent openGallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGallery, 1);
            }
            else {
                //If user press "Deny", send feedback to the user and go back to the main view
                Toast.makeText(this, "Gallery permission denied", Toast.LENGTH_LONG).show();
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
            Uri selectedImage = data.getData();

            //get ByteArray from Uri
            PickedImage pickedImage = new PickedImage();
            pickedImage.convertUri(this, selectedImage);
            byte[] image = pickedImage.getImageArray();

            Intent imageActivity = new Intent(this, ImageActivity.class);
            imageActivity.putExtra("byteArray", image);
            startActivity(imageActivity);

        }
    }

    public void backMainActivity(){
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }
}
