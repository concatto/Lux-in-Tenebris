package tenebris.lux.visao;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Rectangle;
import tenebris.lux.controlador.Direction;
import tenebris.lux.controlador.Loop;

public abstract class ElementoMovel extends Rectangle {
	private Direction direcao = Direction.STOPPED;
	private KeyFrame frame = new KeyFrame(Loop.FRAMES_PER_SECOND, e -> handleMovimento());
	private Timeline timeline = new Timeline(frame);
	
	public ElementoMovel() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setRate(3);
	}
	
	public void setVelocidade(double velocidade) {
		timeline.setRate(velocidade);
	}
	
	public void ativar() {
		timeline.playFromStart();
	}
	
	public void desativar() {
		timeline.stop();
	}
	
	public void setLower(double position) {
		setY(position - getHeight());
	}
	
	public void setDirecao(Direction direcao) {
		this.direcao = direcao;
	}
	
	public Direction getDirecao() {
		return direcao;
	}
	
	public void moverEsquerda() {
		setX(getX() - 1);
	}
	
	public void moverDireita() {
		setX(getX() + 1);
	}
	
	private void handleMovimento() {
		switch (direcao) {
		case LEFT:
			moverEsquerda();
			break;
		case RIGHT:
			moverDireita();
			break;
		case STOPPED:
			break;
		}
	}
}
