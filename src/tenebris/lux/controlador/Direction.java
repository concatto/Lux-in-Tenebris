package tenebris.lux.controlador;

import javafx.scene.input.KeyCode;

public enum Direction {
	STOPPED, LEFT, RIGHT;
	
	public static Direction forKeyCode(KeyCode code) {
		switch (code) {
		case LEFT:
			return LEFT;
		case RIGHT:
			return RIGHT;
		default:
			return null;
		}
	}
}
