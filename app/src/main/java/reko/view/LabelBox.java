package reko.view;

import reko.model.DetectLabelResult;

public class LabelBox extends MainButton{

    private DetectLabelResult result;

    private DetectLabelProcessing detectLabelProcessing;

    private int positionX;
    private int positionY;
    private int width;
    private int height;

    LabelBox(DetectLabelProcessing detectLabelProcessing, DetectLabelResult result){
        this. detectLabelProcessing = detectLabelProcessing;
        this.result = result;
        positionX = Math.round(detectLabelProcessing.width*result.getLeft());
        positionY = Math.round(detectLabelProcessing.height*result.getTop());
        width = Math.round(detectLabelProcessing.width*result.getWidth());
        height = Math.round(detectLabelProcessing.height*result.getHeight());
    }

    public void draw(){
        detectLabelProcessing.noFill();
        detectLabelProcessing.strokeWeight(2);
        detectLabelProcessing.stroke(255);
        detectLabelProcessing.rect(positionX, positionY, width, height);
        if(pressButton(detectLabelProcessing, positionX, positionY, width, height)){
            detectLabelProcessing.textSize(20);
            detectLabelProcessing.text(result.getName() + " "+ result.getConfidence()+"%", positionX+100, positionY-100);
        }
    }

}
