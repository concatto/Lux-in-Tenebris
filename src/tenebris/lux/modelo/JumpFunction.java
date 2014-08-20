package tenebris.lux.modelo;

import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import tenebris.lux.controlador.Loop;
import tenebris.lux.visao.ElementoMovel;

public class JumpFunction {
	private static final KeyFrame EMPTY_FRAME = new KeyFrame(Duration.ZERO);
	private static final double GRAVITY = 14;
	private ElementoMovel handledElement;
	private Timeline ascent = new Timeline(EMPTY_FRAME);
	private Timeline descent = new Timeline(EMPTY_FRAME);
	private Timeline fall = new Timeline();
	
	public JumpFunction(ElementoMovel handledElement) {
		this.handledElement = handledElement;
		
		KeyFrame frameQueda = new KeyFrame(Loop.FRAMES_PER_SECOND, e -> handledElement.moverBaixo());
		fall.getKeyFrames().add(frameQueda);
		
		fall.setCycleCount(Timeline.INDEFINITE);
		fall.setRate(GRAVITY);
	}
	
	public void ascend(double amount) {
		descent.stop();
		createAndPlay(ascent, 400, amount, true);
	}
	
	public void descend(double amount) {
		/* Redução para permitir o uso de fall() */
		amount -= 10;
		createAndPlay(descent, 500, amount, false);
	}

	public void fall() {
		fall.setRate(GRAVITY);
		fall.playFromStart();
	}
	
	public void stopFall() {
		fall.setRate(0);
		fall.stop();
	}
	
	public boolean isFalling() {
		return fall.getStatus().equals(Status.RUNNING);
	}
	
	private void createAndPlay(Timeline timeline, double duration, double amount, boolean isAscent) {
		double currentY = handledElement.getY();
		double targetAmount = isAscent ? currentY - amount : currentY + amount;
		JumpInterpolator interpolator = isAscent ? JumpInterpolator.OUT_CUBIC : JumpInterpolator.IN_CUBIC;
		EventHandler<ActionEvent> finish = isAscent ? e -> descend(amount) : e -> fall();
		
		KeyValue value = new KeyValue(handledElement.yProperty(), targetAmount, interpolator);
		KeyFrame frame = new KeyFrame(Duration.millis(duration), finish, value);
		
		timeline.getKeyFrames().set(0, frame);
		timeline.playFromStart();
	}
}
