package com.esameUNIVPM.reko;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * This class contains methods for convert an image, picked from camera or gallery, into an usable byte array.
 */

public class PickedImage {

    private byte[] imageArray;

    PickedImage() {

    }

    /**
     * Takes a previously filled array of bytes
     * @return image in byte[]
     */
    public byte[] getImageArray() {

        //return a filled byte array
        return imageArray;
    }

    /**
     * Convert a Bitmap image into an array of byte.
     * @param image Bitmap of the selected image.
     */
    public void convertBitmap(Bitmap image) {

        //fill ByteArrayOutputStream with image's bytes in JPEG format
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        //fill byte array
        imageArray = bytes.toByteArray();
    }

    /**
     * Converts Uri image into Bitmap image, then converts it into a byte array
     * @param context context of current state of the application
     * @param selectedImage Uri image to convert
     */
    public void convertUri(Context context, Uri selectedImage){

        Bitmap image = null;

        //convert Uri into Bitmap
        InputStream imageStream = null;
        try {
            imageStream = context.getContentResolver().openInputStream(selectedImage);
            image = BitmapFactory.decodeStream(imageStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //convert Bitmap into ByteArray
        convertBitmap(image);
    }

}