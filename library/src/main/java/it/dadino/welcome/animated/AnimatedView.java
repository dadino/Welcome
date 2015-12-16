package it.dadino.welcome.animated;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import it.dadino.welcome.animations.Animation;
import it.dadino.welcome.animations.PageInfo;

public abstract class AnimatedView<T extends View> {

	protected List<Animation> animations = new ArrayList<>();
	private T view;

	public AnimatedView() { }


	public List<Animation> getAnimations() {
		return animations;
	}

	public View getView() {
		return view;
	}

	public void setView(T view) {
		this.view = view;
		onViewSetted(this.view);
	}

	protected abstract void onViewSetted(T view);

	public void animate(PageInfo pageInfo, float fraction) {
		for (Animation animation : animations) {
			animation.animate(view, fraction, pageInfo);
		}
	}
}
