package it.dadino.welcome.animations;

import android.view.View;

public class XAnimation extends Animation {

	@Override
	public void animate(View view, float fraction, PageInfo info) {
		//	view.setTranslationX(info.width * Mirror.mirror(interpolator.getInterpolation
		// (fraction)));
		view.setTranslationX(info.width * interpolator.getInterpolation(Math.abs(fraction)) *
		                     (fraction < 0 ? -1 : 1) * power);
	}
}
