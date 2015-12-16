package it.dadino.welcome.animations;

import android.support.annotation.FloatRange;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

public abstract class Animation {

	protected float        power        = 1f;
	protected Interpolator interpolator = new LinearInterpolator();

	public abstract void animate(View view, float fraction, PageInfo pageInfo);

	public Interpolator getInterpolator() {
		return interpolator;
	}

	public Animation interpolator(Interpolator interpolator) {
		this.interpolator = interpolator;
		return this;
	}

	public Animation power(@FloatRange(from = 0, to = Float.MAX_VALUE) float power) {
		this.power = power;
		return this;
	}
}
