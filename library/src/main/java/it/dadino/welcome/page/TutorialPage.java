package it.dadino.welcome.page;

import java.util.List;

import it.dadino.welcome.Illustration;

public class TutorialPage {

	private final String             title;
	private final String             subtitle;
	private final int                backgroundColor;
	private final int                footerColor;
	private final List<Illustration> illustrations;
	private final boolean            isExitPage;

	public TutorialPage(String title, String subtitle, int backgroundColor, int footerColor,
	                    List<Illustration> illustrations, boolean isExitPage) {
		this.title = title;
		this.subtitle = subtitle;
		this.backgroundColor = backgroundColor;
		this.footerColor = footerColor;
		this.illustrations = illustrations;
		this.isExitPage = isExitPage;
	}

	public String getTitle() {
		return title;
	}

	public String getSubtitle() {
		return subtitle;
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
}
