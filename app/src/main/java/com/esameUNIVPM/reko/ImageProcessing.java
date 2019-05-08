package com.esameUNIVPM.reko;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import processing.core.PApplet;
import processing.core.PImage;

public class ImageProcessing extends PApplet{

    private byte[] image;
    private AppCompatActivity activity;
    private PImage selectedImage;

    ImageProcessing(byte[] image, AppCompatActivity activity){
        this.image=image;
        selectedImage = convertByteArray(image);
        this.activity = activity;
    }

    public void setup(){


    }

    public void settings(){

        fullScreen();
    }

    public void draw(){
        background(255);
        image(selectedImage, 0, 0, width, height);
    }

    /**
     * Convert byte array into a PImage
     * @param image byte array to convert
     * @return PImage converted
     */
    private PImage convertByteArray(byte[] image){

        //First we must create a Bitmap image from the byte array
        Bitmap bitmap = BitmapFactory.decodeByteArray(image , 0, image .length);

        //now copy the Bitmap reconstructed into an integer array of pixel
        int[] pixels = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());

        //create an empty PImage, with the same dimensions of the Bitmap image
        PImage outImage = createImage(bitmap.getWidth(), bitmap.getHeight(), RGB);
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
