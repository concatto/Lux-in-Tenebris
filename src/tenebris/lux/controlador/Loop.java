package tenebris.lux.controlador;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import tenebris.lux.visao.GameStage;
import tenebris.lux.visao.Personagem;

public class Loop  {
	public static final Duration FRAMES_PER_SECOND = Duration.millis(1000/60);
	private Personagem personagem = new Personagem();
	private KeyFrame frame;
	private Timeline timeline;
	private EventHandler<KeyEvent> pressionado = this::handlePressionado;
	private EventHandler<KeyEvent> soltado = this::handleSoltado;
	private GameStage gameStage = new GameStage(pressionado, soltado);
	
	public Loop() {
		gameStage.show();
		gameStage.adicionarElementoMovel(personagem);
		personagem.ativar();
		
		frame = new KeyFrame(FRAMES_PER_SECOND, e -> handle());
		timeline = new Timeline(frame);
		timeline.setCycleCount(Timeline.INDEFINITE);
	}
	
	public void start() {
		timeline.playFromStart();
	}
	
	private void handle() {
		if ((personagem.getY() + personagem.getHeight() + personagem.getAceleracao()) > gameStage.buscarSuperficie(gameStage.getSolo(), personagem.getX())) {
			personagem.pararQueda();
		}
		if (!personagem.isCaindo()) personagem.setLower(gameStage.buscarSuperficie(gameStage.getSolo(), personagem.getX()));
	}
	
	private void handlePressionado(KeyEvent e) {
		Direction direcao = Direction.forKeyCode(e.getCode());
		if (direcao != null) personagem.setDirecao(direcao);
		if (e.getCode().equals(KeyCode.Z)) personagem.ascender(300);
	}
	
	private void handleSoltado(KeyEvent e) {
		Direction direcao = Direction.forKeyCode(e.getCode());
		if (personagem.getDirecao().equals(direcao)) personagem.setDirecao(Direction.STOPPED);
	}
}
