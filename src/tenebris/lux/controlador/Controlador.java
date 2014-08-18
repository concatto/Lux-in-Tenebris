package tenebris.lux.controlador;

import javafx.application.Application;
import javafx.stage.Stage;

public class Controlador extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Loop loop = new Loop();
		loop.start();
	}
}
