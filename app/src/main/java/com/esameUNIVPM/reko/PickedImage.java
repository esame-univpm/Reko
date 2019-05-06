package com.esameUNIVPM.reko;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class PickedImage implements Parcelable {

    private Uri imageSelected;
    private byte[] image;
    private AppCompatActivity activity;

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public PickedImage createFromParcel(Parcel in) {
            return new PickedImage(in);
        }

        public PickedImage[] newArray(int size) {
            return new PickedImage[size];
        }
    };

    public PickedImage(Uri image, AppCompatActivity activity) {
        this.imageSelected=image;
        this.activity=activity;
        converToByteArray();
    }

    private void converToByteArray(){
        try {
            InputStream input = activity.getContentResolver().openInputStream(imageSelected);
            ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int len = 0;
            while (true) {
                if (!((len = input.read(buffer)) != -1)) {
                    break;
                }
                byteBuffer.write(buffer, 0, len);
            }
            image = byteBuffer.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setImage(byte[] image){
        this.image=image;
    }

    public byte[] getImage(){
        return image;
    }

    public PickedImage(Parcel in) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(imageSelected, flags);
        dest.writeByteArray(image);
    }
}
