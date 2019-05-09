package reko.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class PickedImage {

    private byte[] imageArray;

    public PickedImage() {

    }

   //get the image selected in form of byte array
    public byte[] getImageArray() {

        //return a filled byte array
        return imageArray;
    }

    //convert a Bitmap image into an array of byte
    public void imageToByteArray(String path) {

        //get an InputStream from the file
        InputStream imageStream = null;
        try {
            imageStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //decoding the input stream to build a bitmap image
        Bitmap bitmap = BitmapFactory.decodeStream(imageStream);

        //fill ByteArrayOutputStream with image's bytes in JPEG format
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        //fill byte array
        imageArray = bytes.toByteArray();
    }

}