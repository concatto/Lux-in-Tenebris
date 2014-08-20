package tenebris.lux.visao;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import tenebris.lux.controlador.Direction;
import tenebris.lux.controlador.Loop;
import tenebris.lux.modelo.JumpInterpolator;

public abstract class ElementoMovel extends Rectangle {
	private static final double GRAVIDADE = 1;
	private double aceleracao = 2;
	private Direction direcao = Direction.STOPPED;
	private KeyFrame frameMovimento = new KeyFrame(Loop.FRAMES_PER_SECOND, e -> handleMovimento());
	private KeyFrame frameQueda = new KeyFrame(Loop.FRAMES_PER_SECOND, e -> handleQueda());
	private Timeline movimento = new Timeline(frameMovimento);
	private Timeline queda = new Timeline(frameQueda);
	private Timeline ascensao = new Timeline();
	
	public ElementoMovel() {
		queda.setCycleCount(Timeline.INDEFINITE);
		movimento.setCycleCount(Timeline.INDEFINITE);
		movimento.setRate(3);
	}
	
	private void handleQueda() {
		aceleracao += (Math.pow(aceleracao, GRAVIDADE) / 10);
		moverBaixo();
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
		KeyFrame frame = new KeyFrame(Duration.millis(400), e -> descender(), new KeyValue(yProperty(), getY() - altura, JumpInterpolator.getOut()));
		ascensao.getKeyFrames().clear();
		ascensao.getKeyFrames().add(frame);
		ascensao.playFromStart();
	}
	
	public void descender() {
		aceleracao = 2;
		queda.playFromStart();
	}
	
	public void pararQueda() {
		queda.stop();
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
		setY(getY() + aceleracao);
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
