package reko.controller;

import android.content.Intent;

public interface UploadImage {

    //Check app permissions
    void checkPermissions(int requestCode);

    //set path of selected image
    void setPathSelectedImage(Intent data);

}
