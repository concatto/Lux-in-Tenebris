package tenebris.lux.visao;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameStage extends Stage {
	private static final double LARGURA = 800;
	private static final double ALTURA = 600;
	private TileMap map = new TileMap(LARGURA, ALTURA);
	private Solo solo = new Solo(LARGURA);
	private AnchorPane root = new AnchorPane(map);
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
		elemento.setLowerBound(buscarSuperficie(0, 0));
		elemento.setOwner(this);
	}
	
	public double buscarSuperficie(double x, double y) {
		return map.findGround(x, y);
	}
	
	public Solo getSolo() {
		return solo;
	}
	
	public TileMap getMap() {
		return map;
	}
}
