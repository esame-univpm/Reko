package reko.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class ManageImage {

    /**
     * Returns the image in the form of byte array
     * @param path
     * @return
     */
    public static byte[] imageToByteArray(String path){
        ByteArrayOutputStream bytes = getByteStream(path);
        //fill byte array
        return bytes.toByteArray();
    }

    /**
     * Return image in Bitmap format
     * @param path
     * @return
     */
    private static ByteArrayOutputStream getByteStream(String path){
        //get an InputStream from the file
        InputStream imageStream = null;
        try {
            imageStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //decoding the input stream to build a bitmap image
        Bitmap bitmap = BitmapFactory.decodeStream(imageStream);

        //scale image
        bitmap = Bitmap.createScaledBitmap(bitmap, 1080, 1920, true);

        //fill ByteArrayOutputStream with image's bytes in JPEG format
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        //return image in form of Bitmap
        return bytes;
    }

    public static PImage imageToPImage(String path){
        ByteArrayOutputStream bytes = getByteStream(path);

        byte[] image = bytes.toByteArray();

        //First we must create a Bitmap image from the byte array
        Bitmap bitmap = BitmapFactory.decodeByteArray(image , 0, image .length);

        //now copy the Bitmap reconstructed into an integer array of pixel
        int[] pixels = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());

        //create an empty PImage, with the same dimensions of the Bitmap image
        PImage outImage = new PApplet().createImage(bitmap.getWidth(), bitmap.getHeight(), PConstants.RGB);
        outImage.loadPixels();

        //copy all the pixels stored in the integer array
        for(int i=0; i<bitmap.getWidth() * bitmap.getHeight(); i++){
            outImage.pixels[i]=pixels[i];
        }

        //update the PImage with the copied pixels
        outImage.updatePixels();

        //return the image converted
        return outImage;
    }
}
