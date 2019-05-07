package com.esameUNIVPM.reko;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class PickedImage {

    private byte[] imageArray;

    PickedImage() {

    }

    public byte[] getImageArray() {
        return imageArray;
    }

    /**
     * Convert a Bitmap image into an array of byte.
     * @param image Bitmap of the selected image.
     */
    public void convertBitmap(Context context, Bitmap image) {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), image, "Title", null);

        try {
            InputStream inputStream = context.getContentResolver().openInputStream(Uri.parse(path));
            imageArray = getBytesArray(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void convertUri(Context context, Uri selectedImage){
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(selectedImage);
            imageArray = getBytesArray(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private byte[] getBytesArray(InputStream inputStream){
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int len = 0;

        try {
            while (true) {
                if (((len = inputStream.read(buffer)) == -1)) {
                    break;
                }
                byteBuffer.write(buffer, 0, len);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return byteBuffer.toByteArray();
    }

}