package com.esameUNIVPM.reko;

import processing.core.PApplet;
import processing.core.PImage;

public class CameraButton extends MainButton {
    private PApplet p;
    private PImage camerabutton;


    CameraButton(PApplet p) {
        this.p = p;
        camerabutton = p.loadImage("camera_button.png");


    }

    @Override
    public void draw() {
        p.fill(p.color(255, 0, 0));
        p.ellipse(p.width - 200, p.height - 200, 200, 200);
        p.image(camerabutton, p.width - 300, p.height - 300, 200, 200);
        animation();

    }

    @Override
    public void animation() {
        pressButton();
    }

    @Override
    public boolean pressButton() {
        if (p.mousePressed) {
            if (p.mouseX > p.width - 300 && p.mouseX < p.width - 100) {
                if (p.mouseY > p.height - 300 && p.mouseY < p.height - 100) {
                    return true;
                }
            }

        }
        return false;
    }
}