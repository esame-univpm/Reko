package reko.fragment;

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
import reko.processing.ViewsProcessing;

/**
 * The ViewsFragment class is used to displays ViewProcessing's views and controls the Intent that allow the user to get an image
 */
public class ViewsFragment extends MainFragment {

    //instance of Processing sketch which runs on this fragment
    private ViewsProcessing viewsProcessing;

    //image acquired from camera
    private Uri imageUri;

    //request codes
    private final int CAMERA_REQUEST = 1;
    private final int GALLERY_REQUEST = 2;

    //path of the image selected
    private String pathSelectedImage = null;

    @Override
    public void onResume() {
        super.onResume();
        viewsProcessing.setNumberView(0);
    }

    //on create run StartView Processing sketch
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //runs startView, the Processing sketch
        viewsProcessing = new ViewsProcessing(mainActivity);
        setSketch(viewsProcessing);
    }

    //this method manage the result of
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        switch (requestCode){
            case GALLERY_REQUEST:{
                pathSelectedImage = setPathFromGallery(data);
                break;
            }
            case CAMERA_REQUEST:{
                //set the path of selected image
                pathSelectedImage = setPathFromCamera();
                break;
            }
        }

        /*
        switch (viewsProcessing.getNumberView()){
            case 1:{

                //change fragment
                mainActivity.getViewsFragment().setActive(false);
                mainActivity.getDetectLabelFragment().setActive(true);
                Bundle bundle = new Bundle();
                bundle.putString("path", pathSelectedImage);    //pass the path to the new fragment
                mainActivity.getDetectLabelFragment().setArguments(bundle);
                mainActivity.getViewsFragment().getFragmentManager().beginTransaction().replace(R.id.frameLayout, mainActivity.getDetectLabelFragment()).commit();
                break;
            }
        }
        */

    }



    //starts camera intent
    public void openCamera(){
        //set format of image that will be captured
        setImageUri();
        //instance an Intent for camera
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        //pass the Uri image as arguments to the camera intent
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        //starts camera intent
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    //starts gallery intent
    public void openGallery(){
        //instance an Intent for gallery
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //starts gallery intent
        startActivityForResult(galleryIntent, GALLERY_REQUEST);
    }

    //method to return the sketch
    public ViewsProcessing getViewsProcessing(){
        return viewsProcessing;
    }

    private void setImageUri(){
        //set the values of the new image using ContentValues
        ContentValues values = new ContentValues();
        //create new Uri image
        imageUri = mainActivity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }

    //stores image captured and return the path of the image
    private String setPathFromCamera() {
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

    private String setPathFromGallery(Intent data){
        //capture image
        Uri selectedImage = data.getData();

        //find path of captured image
        Cursor cursor = getActivity().getContentResolver().query(selectedImage, null, null, null, null);
        cursor.moveToFirst();
        int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);

        //set the path of selected image
        return cursor.getString(index);
    }
}
