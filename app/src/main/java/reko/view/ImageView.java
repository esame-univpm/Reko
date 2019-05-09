package reko.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import processing.core.*;
import reko.controller.PickedImage;

public class ImageView implements AppView{

    //instance of processing object in use
    private PApplet p;
    //instance of fragment in use
    private Fragment pFragment;
    //Absolute path of selected image
    private String pathSelectedImage;
    //image selected
    private PImage selectedImage;

    public ImageView(PApplet p, Fragment pFragment){
        this.p=p;
        this.pFragment=pFragment;
    }

    public void setPathSelectedImage(String pathSelectedImage) {
        this.pathSelectedImage = pathSelectedImage;
        init();
    }

    public void init(){

        //build a byte array from the selected image
        PickedImage pickedImage = new PickedImage();
        pickedImage.imageToByteArray(pathSelectedImage);
        byte[] image = pickedImage.getImageArray();

        //convert image
        selectedImage = convertByteArray(image);
    }

    public void show(){

        //show the image
        p.image(selectedImage, 0, 0, p.width, p.height);

    }

    //convert byte array into drawable image
    private PImage convertByteArray(byte[] image){

        //First we must create a Bitmap image from the byte array
        Bitmap bitmap = BitmapFactory.decodeByteArray(image , 0, image .length);

        //now copy the Bitmap reconstructed into an integer array of pixel
        int[] pixels = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());

        //create an empty PImage, with the same dimensions of the Bitmap image
        PImage outImage = p.createImage(bitmap.getWidth(), bitmap.getHeight(), PConstants.RGB);
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
