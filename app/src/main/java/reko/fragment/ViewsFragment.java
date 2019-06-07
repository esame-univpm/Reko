package reko.fragment;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import reko.processing.FaceResult;
import reko.processing.LabelResult;
import reko.processing.TextResult;
import reko.processing.ViewsController;

public class ViewsFragment extends MainFragment {

    private ViewsController viewsController;        //instance of Processing sketch which runs on this fragment

    private Uri imageUri;       //image acquired from camera

    //request codes
    private final int CAMERA_REQUEST = 1;
    private final int GALLERY_REQUEST = 2;

    /**
     * On create runs ViewsController sketch
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //runs startView, the Processing sketch
        viewsController = new ViewsController(mainActivity);
        setSketch(viewsController);
    }

    /**
     * Starts camera Intent
     */
    public void openCamera(){
        //set the values of the new image using ContentValues
        ContentValues values = new ContentValues();
        //create new Uri image
        imageUri = mainActivity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        //instance an Intent for camera
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        //pass the Uri image as arguments to the camera intent
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        //starts camera intent
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    /**
     * Starts gallery Intent
     */
    public void openGallery(){
        //instance an Intent for gallery
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //starts gallery intent
        startActivityForResult(galleryIntent, GALLERY_REQUEST);
    }

    /**
     * This method return the path of the image captured by the camera.
     * @return String
     */
    private String setPath(){
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
        return file.getPath();
    }

    /**
     * Returns the path of the image selected from gallery.
     * @param data
     * @return String
     */
    private String setPath(Intent data){
        //capture image
        Uri selectedImage = data.getData();

        //find path of captured image
        Cursor cursor = getActivity().getContentResolver().query(selectedImage, null, null, null, null);
        cursor.moveToFirst();
        int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);

        //set the path of selected image
        return cursor.getString(index);
    }

    /**
     * This method manages the results of both camera and gallery Intent
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        String pathSelectedImage = null;    //path of selected image

        if(resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case GALLERY_REQUEST: {
                    pathSelectedImage = setPath(data);
                    break;
                }
                case CAMERA_REQUEST: {
                    //set the path of selected image
                    pathSelectedImage = setPath();
                    break;
                }
            }
            //switch to loading view
            switch (viewsController.getNumberView()){
                case 1:{
                    ((LabelResult) viewsController.getViewsMap().get(4)).setImage(pathSelectedImage);
                    viewsController.setNumberView(4);
                    break;
                }
                case 2:{
                    ((FaceResult) viewsController.getViewsMap().get(5)).setImage(pathSelectedImage);
                    viewsController.setNumberView(5);
                    break;
                }
                case 3:{
                    ((TextResult) viewsController.getViewsMap().get(6)).setImage(pathSelectedImage);
                    viewsController.setNumberView(6);
                    break;
                }
            }
        }
        viewsController.loop();
    }

    public ViewsController getViewsController(){
        return viewsController;
    }

}
