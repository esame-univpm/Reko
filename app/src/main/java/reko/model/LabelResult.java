package reko.model;

public class LabelResult {
	
	private String name;
	private float confidence;
	private Float height;
	private Float width;
	private Float top;
	private Float left;
	private boolean background;
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setConfidence(float confidence) {
		this.confidence=confidence;
	}
	
	public float getConfidence() {
		return confidence;
	}
	
	public void setHeight(Float height) {
		this.height=height;
	}
	
	public Float getHeight() {
		return height;
	}
	
	public void setWidth(Float width) {
		this.width=width;
	}
	
	public Float getWidth() {
		return width;
	}
	
	public void setTop(Float top) {
		this.top=top;
	}
	
	public Float getTop() {
		return top;
	}
	
	public void setLeft(Float left) {
		this.left=left;
	}
	
	public Float getLeft() {
		return left;
	}
	
	public void setBackground(boolean background) {
		this.background=background;
	}
	
	public boolean getBackground() {
		return background;
	}

	public LabelResult(){
		this.height=null;
		this.left=null;
		this.top=null;
		this.width=null;
		this.background=true;
	}

}
