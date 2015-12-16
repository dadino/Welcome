package it.dadino.welcome.animations;

import android.view.View;

public class YAnimation extends Animation {

	public static final int TOP    = 0;
	public static final int BOTTOM = 1;
	private int comingFrom;

	public void comingFrom(int direction) {
		this.comingFrom = direction;
	}

	@Override
	public void animate(View view, float fraction, PageInfo info) {
		view.setTranslationY(
				(comingFrom == TOP ? -1 : 1) * info.height * interpolator.getInterpolation(Math.abs(
						fraction)) * power);
	}
}
