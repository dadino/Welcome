package it.dadino.welcome.page;

import java.util.List;

import it.dadino.welcome.animated.Illustration;
import it.dadino.welcome.animated.Text;

public class TutorialPage {

	private final Text               title;
	private final Text               subtitle;
	private final int                backgroundColor;
	private final int                footerColor;
	private final List<Illustration> illustrations;
	private final boolean            isExitPage;

	public TutorialPage(Text title, Text subtitle, int backgroundColor, int footerColor,
	                    List<Illustration> illustrations, boolean isExitPage) {

		this.backgroundColor = backgroundColor;
		this.footerColor = footerColor;
		this.title = title;
		this.subtitle = subtitle;
		this.illustrations = illustrations;
		this.isExitPage = isExitPage;
	}


	public int getBackgroundColor() {
		return backgroundColor;
	}

	public int getFooterColor() {
		return footerColor;
	}

	public List<Illustration> getIllustrations() {
		return illustrations;
	}

	public boolean isExitPage() {
		return isExitPage;
	}

	public Text getTitle() {
		return title;
	}

	public Text getSubtitle() {
		return subtitle;
	}
}
