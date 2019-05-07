package com.esameUNIVPM.reko;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import java.nio.ByteBuffer;
import processing.core.PApplet;
import processing.core.PImage;

public class ImageProcessing extends PApplet{

    private byte[] image;
    private AppCompatActivity activity;
    private PImage out;

    ImageProcessing(byte[] image, AppCompatActivity activity){
        this.image = image;
        this.activity = activity;
    }

    public void setup(){

        Bitmap bitmap = BitmapFactory.decodeByteArray(image , 0, image .length);
        int[] pixels = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        PImage outImage = createImage(bitmap.getWidth(), bitmap.getHeight(), RGB);
        outImage.loadPixels();
        for(int i=0; i<bitmap.getWidth() * bitmap.getHeight(); i++){
            outImage.pixels[i]=pixels[i];
        }
        outImage.updatePixels();
        out=outImage;

    }

    public void settings(){
        fullScreen();
    }

    public void draw(){
        background(255);
        image(out, 100, 100, width-200, height-200);
    }


}
