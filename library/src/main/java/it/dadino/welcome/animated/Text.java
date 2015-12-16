package it.dadino.welcome.animated;

import android.widget.TextView;

import it.dadino.welcome.animations.Animation;

public class Text extends AnimatedView<TextView> {

	private final String text;

	public Text(String text) {
		this.text = text;
	}

	public Text addAnimation(Animation animation) {
		animations.add(animation);
		return this;
	}

	@Override
	protected void onViewSetted(TextView view) {
		view.setText(text);
	}

	public String text() {
		return text;
	}
}
