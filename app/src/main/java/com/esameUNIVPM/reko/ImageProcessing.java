package com.esameUNIVPM.reko;

import android.support.v7.app.AppCompatActivity;

import processing.core.PApplet;
import processing.core.PImage;

public class ImageProcessing extends PApplet {

    private byte[] image;
    private AppCompatActivity activity;
    private PImage pImage;


    ImageProcessing(byte[] image, AppCompatActivity activity){
        this.image = image;
        this.activity = activity;
    }

    public void setup(){
        int w = width;
        int h = height;
        int ch = 3;

        pImage = ByteArrayToImage(image, w, h, ch);
    }

    public void settings(){
        fullScreen();
    }

    public void draw(){
        image(pImage, 0, 0);
    }

    private PImage ByteArrayToImage(byte[] image, int w, int h, int ch){
        PImage out = new PImage(w, h, RGB);
        out.loadPixels();

        for(int i=0; i<w*h; i++){
            out.pixels[i]=color(image[i*ch], image[i*ch+1],image[i*ch+2]);
        }
        out.updatePixels();
        return out;
    }
}
