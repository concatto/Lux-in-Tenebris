package tenebris.lux.visao;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Rectangle;
import tenebris.lux.controlador.Direction;
import tenebris.lux.controlador.Loop;
import tenebris.lux.modelo.JumpFunction;

public abstract class ElementoMovel extends Rectangle {
	private Direction direcao = Direction.STOPPED;
	private KeyFrame frameMovimento = new KeyFrame(Loop.FRAMES_PER_SECOND, e -> handleMovimento());
	private Timeline movimento = new Timeline(frameMovimento);
	private JumpFunction salto = new JumpFunction(this);
	
	public ElementoMovel() {
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
	
	public void saltar(double altura) {
		salto.ascend(altura);
	}
	
	public void cair() {
		salto.fall();
	}
	
	public void pararQueda() {
		salto.stopFall();
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
		return salto.isFalling();
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
