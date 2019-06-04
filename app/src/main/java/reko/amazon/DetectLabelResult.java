package reko.amazon;

public class DetectLabelResult {

    private String name;
    private boolean background;
    private Float height;
    private Float width;
    private Float top;
    private Float left;
    private float confidence;

    public DetectLabelResult(String name, boolean background, Float height, Float width, Float top, Float left, float confidence){
        this.name = name;
        this.background = background;
        this.height = height;
        this.width = width;
        this.top = top;
        this.left = left;
        this.confidence = confidence;
    }

    public String getName() {
        return name;
    }

    public boolean isBackground() {
        return background;
    }

    public Float getHeight() {
        return height;
    }

    public Float getWidth() {
        return width;
    }

    public Float getTop() {
        return top;
    }

    public Float getLeft() {
        return left;
    }

    public float getConfidence() {
        return confidence;
    }
}
