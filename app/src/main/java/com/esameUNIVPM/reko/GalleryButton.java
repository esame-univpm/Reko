package com.esameUNIVPM.reko;

        import processing.core.PApplet;
        import processing.core.PImage;

public class GalleryButton extends MainButton {
    private PApplet p;
    private PImage gallerybutton;


    GalleryButton(PApplet p) {
        this.p = p;
        gallerybutton = p.loadImage("gallery_button.png");


    }

    @Override
    public void draw() {
        p.fill(p.color(255, 0, 0));
        p.ellipse(200, p.height - 200, 200, 200);
        p.image(gallerybutton, 100, p.height - 300, 200, 200);
        animation();

    }

    @Override
    public void animation() {
        pressButton();
    }

    @Override
    public boolean pressButton() {
        if (p.mousePressed) {
            if (p.mouseX > 100 && p.mouseX < 300) {
                if (p.mouseY > p.height - 100 && p.mouseY < p.height - 300) {
                    return true;
                }
            }

        }
        return false;
    }
}
