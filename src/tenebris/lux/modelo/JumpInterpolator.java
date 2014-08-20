package tenebris.lux.modelo;

import javafx.animation.Interpolator;

public abstract class JumpInterpolator extends Interpolator {	
	public static JumpInterpolator getOut() {
		return new JumpInterpolator() {
			@Override
			protected double curve(double t) {
				return 0.6975 * Math.pow(t, 5) + -1.8425 * Math.pow(t, 4) + 1.995 * Math.pow(t, 3) + -2 * (t * t) + 2.15 * t;
			}
		};
	}
	
	public static JumpInterpolator getIn() {
		return new JumpInterpolator() {
			@Override
			protected double curve(double t) {
				return Math.pow(t, 3);
			}
		};
	}
}
