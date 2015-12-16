package it.dadino.welcome;

import android.support.annotation.DrawableRes;

import java.util.ArrayList;
import java.util.List;

import it.dadino.welcome.animations.Animation;

public class Illustration {

	private final int drawable;
	private List<Animation> animations = new ArrayList<>();

	public Illustration(@DrawableRes int drawable) {
		this.drawable = drawable;
	}

	public Illustration addAnimation(Animation animation) {
		animations.add(animation);
		return this;
	}

	public int getDrawable() {
		return drawable;
	}

	public List<Animation> getAnimations() {
		return animations;
	}
}
