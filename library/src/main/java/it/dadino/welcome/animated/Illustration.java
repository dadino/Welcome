package it.dadino.welcome.animated;

import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import it.dadino.welcome.animations.Animation;

public class Illustration extends AnimatedView<ImageView> {

	private int drawable;

	public Illustration(@DrawableRes int drawable) {
		this.drawable = drawable;
	}

	public Illustration addAnimation(Animation animation) {
		animations.add(animation);
		return this;
	}

	@Override
	protected void onViewSetted(ImageView image) {
		image.setImageResource(drawable);
	}
}
