package it.dadino.welcome.animations;

import android.view.View;

public class AlphaAnimation extends Animation {

	@Override
	public void animate(View view, float fraction, PageInfo info) {
		view.setAlpha(1 - interpolator.getInterpolation(Math.abs(fraction)));
	}
}
