package reko.model;

/**
 * The DetectedText class contains the data of a text detected.
 */
public class DetectedText {

    private String text;    //text detected

    private int confidence; //data trust in percentage

    private int[] topLeft;  //coordinates of the top left vertex

    private int[] topRight; //coordinates of the top right vertex

    private int[] bottomLeft; //coordinates of the bottom left vertex

    private int[] bottomRight;  //coordinates of the bottom right vertex

    /**
     * Sets the text value
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Returns the text value
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the confidence value
     * @param confidence
     */
    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    /**
     * Returns the confidence value
     * @return
     */
    public int getConfidence() {
        return confidence;
    }

    /**
     * Sets the top left vertex values
     * @param topLeft
     */
    public void setTopLeft(int[] topLeft) {
        this.topLeft = topLeft;
    }

    /**
     * Returns the top left vertex values
     * @return
     */
    public int[] getTopLeft() {
        return topLeft;
    }

    /**
     * Sets the top right vertex values
     * @param topRight
     */
    public void setTopRight(int[] topRight) {
        this.topRight = topRight;
    }

    /**
     * Returns the top right vertex values
     * @return
     */
    public int[] getTopRight() {
        return topRight;
    }

    /**
     * Sets the bottom left vertex values
     * @param bottomLeft
     */
    public void setBottomLeft(int[] bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    /**
     * Returns the bottom left vertex values
     * @return
     */
    public int[] getBottomLeft() {
        return bottomLeft;
    }

    /**
     * Sets the bottom right vertex values
     * @param bottomRight
     */
    public void setBottomRight(int[] bottomRight) {
        this.bottomRight = bottomRight;
    }

    /**
     * Returns the bottom right values
     * @return
     */
    public int[] getBottomRight() {
        return bottomRight;
    }
}
