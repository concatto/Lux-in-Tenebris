package tenebris.lux.visao;

import tenebris.lux.controlador.Direction;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TileMap extends TilePane {
	public static final int TILE_WIDTH = 16;
	public static final int TILE_HEIGHT = 16;
	
	public TileMap(double width, double height) {
		this((int) width, (int) height);
	}
	
	public TileMap(int width, int height) {
		setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);
		setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
		setPrefTileHeight(16);
		setPrefTileWidth(16);
		setPrefColumns(width / TILE_WIDTH);
		setPrefRows(height / TILE_HEIGHT);
		
		for (int i = 0; i < getPrefRows(); i++) {
			Color fill = i > 35 ? Color.FORESTGREEN : Color.SLATEGRAY;
			for (int j = 0; j < getPrefColumns(); j++) {
				Rectangle rect = new Rectangle();
				rect.setStroke(Color.DARKGRAY);
				rect.setFill(fill);
				rect.setWidth(TILE_WIDTH);
				rect.setHeight(TILE_HEIGHT);
				getChildren().add(rect);
			}
		}
	}
	
	public Node findNearest(double x, double y) {
		return getChildren().get(getPosition(x, y));
	}
	
	public double findGround(double leftBound, double lowerBound) {
		int index = getPosition(leftBound, lowerBound);
		for (int i = index; i < getChildren().size(); i += getPrefColumns()) {
			Rectangle left = (Rectangle) getChildren().get(i);
			Rectangle right = (Rectangle) getChildren().get(i + 1);
			Color leftColor = (Color) left.getFill();
			Color rightColor = (Color) right.getFill();
			if ((leftColor.equals(Color.FORESTGREEN) || leftColor.equals(Color.LIGHTSKYBLUE)) || (rightColor.equals(Color.FORESTGREEN) || rightColor.equals(Color.LIGHTSKYBLUE))) return right.getBoundsInParent().getMinY();
		}
		return 0;
	}
	
	public boolean isMoveable(Direction direction, Bounds bounds) {
		double x = direction.equals(Direction.LEFT) ? bounds.getMinX() - 2 : bounds.getMaxX();
		int startIndex = getPosition(x, bounds.getMinY());
		int endIndex = getPosition(x, bounds.getMaxY());
		
		for (int i = startIndex; i < endIndex; i += getPrefColumns()) {
			Rectangle rect = (Rectangle) getChildren().get(i);
			if (rect.getFill().equals(Color.LIGHTSKYBLUE)) return false;
		}
		return true;
	}
	
	private int getPosition(double x, double y) {
		int xIndex = (int) x / TILE_WIDTH;
		int yIndex = (int) y / TILE_HEIGHT;
		return (yIndex * getPrefColumns()) + xIndex;
	}
}
