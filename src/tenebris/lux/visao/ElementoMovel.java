package tenebris.lux.visao;

import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import tenebris.lux.controlador.Direction;
import tenebris.lux.controlador.Loop;
import tenebris.lux.modelo.JumpInterpolator;

public abstract class ElementoMovel extends Rectangle {
	private static final double VELOCIDADE_QUEDA = 14;
	private Direction direcao = Direction.STOPPED;
	private KeyFrame frameMovimento = new KeyFrame(Loop.FRAMES_PER_SECOND, e -> handleMovimento());
	private KeyFrame frameQueda = new KeyFrame(Loop.FRAMES_PER_SECOND, e -> moverBaixo());
	private Timeline movimento = new Timeline(frameMovimento);
	private Timeline queda = new Timeline();
	private Timeline ascensao = new Timeline();
	private Timeline quedaConstante = new Timeline(frameQueda);
	
	public ElementoMovel() {
		quedaConstante.setCycleCount(Timeline.INDEFINITE);
		quedaConstante.setRate(VELOCIDADE_QUEDA);
		movimento.setCycleCount(Timeline.INDEFINITE);
		movimento.setRate(3);
	}

	public void setVelocidadeMovimento(double velocidade) {
		movimento.setRate(velocidade);
	}
	
	public void ativar() {
		movimento.playFromStart();
	}
	
	public void desativar() {
		movimento.stop();
	}
	
	public void ascender(double altura) {
		KeyFrame frame = new KeyFrame(Duration.millis(400), e -> descender(altura), new KeyValue(yProperty(), getY() - altura, JumpInterpolator.OUT_CUBIC));
		ascensao.getKeyFrames().clear();
		ascensao.getKeyFrames().add(frame);
		ascensao.playFromStart();
	}
	
	public void descender(double altura) {
		altura = altura - 10;
		KeyFrame frame = new KeyFrame(Duration.millis(500), e -> descensoConstante(), new KeyValue(yProperty(), getY() + altura, JumpInterpolator.IN_CUBIC));
		queda.getKeyFrames().clear();
		queda.getKeyFrames().add(frame);
		queda.playFromStart();
	}
	
	public void descensoConstante() {
		quedaConstante.setRate(VELOCIDADE_QUEDA);
		quedaConstante.playFromStart();
	}
	
	public void pararQueda() {
		/* Forçar parada imediata */
		quedaConstante.setRate(0);
		quedaConstante.stop();
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
	
	public void moverCima() {
		setY(getY() - 1);
	}
	
	public void moverBaixo() {
		setY(getY() + 1);
	}
	
	public boolean isCaindo() {
		return quedaConstante.getStatus().equals(Status.RUNNING);
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
