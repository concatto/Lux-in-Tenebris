package tenebris.lux.visao;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class GameStage extends Stage {
	private static final double LARGURA = 800;
	private static final double ALTURA = 600;
	private Solo solo = new Solo(LARGURA);
	private AnchorPane root = new AnchorPane(solo);
	private Scene cena = new Scene(root, LARGURA, ALTURA);
	
	public GameStage(EventHandler<KeyEvent> handlerPressionado, EventHandler<KeyEvent> handlerSoltado) {
		cena.getStylesheets().add(GameStage.class.getResource("/style.css").toExternalForm());
		cena.setOnKeyPressed(handlerPressionado);
		cena.setOnKeyReleased(handlerSoltado);
		
		AnchorPane.setBottomAnchor(solo, 0d);
		setTitle("Lux in Tenebris");
		setScene(cena);
	}
	
	public void adicionarElementoMovel(ElementoMovel elemento) {
		root.getChildren().add(elemento);
		elemento.setLower(buscarSuperficie(solo, elemento.getX()));
	}
	
	public double buscarSuperficie(Shape superficie, double x) {
		Bounds bounds = superficie.getBoundsInParent();
		for (int i = 0; i < ALTURA; i++) {
			if (bounds.contains(x, i)) return i;
		}
		return 0;
	}
	
	public Solo getSolo() {
		return solo;
	}
}
