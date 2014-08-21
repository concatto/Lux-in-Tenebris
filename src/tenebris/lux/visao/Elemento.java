package tenebris.lux.visao;

import javafx.scene.shape.Rectangle;

public class Elemento extends Rectangle {
	private GameStage owner;
	
	public Elemento() {
		
	}
	
	public void setRightBound(double position) {
		setX(position - getWidth());
	}
	
	public void setLeftBound(double position) {
		setX(position);
	}
	
	public void setUpperBound(double position) {
		setY(position);
	}
	
	public void setLowerBound(double position) {
		setY(position - getHeight());
	}
	
	public double getRightBound() {
		return getBoundsInParent().getMaxX();
	}
	
	public double getLeftBound() {
		return getBoundsInParent().getMinX();
	}
	
	public double getUpperBound() {
		return getBoundsInParent().getMinY();
	}
	
	public double getLowerBound() {
		return getBoundsInParent().getMaxY();
	}
	
	public void setOwner(GameStage owner) {
		this.owner = owner;
	}
	
	public GameStage getOwner() {
		return owner;
	}
}
