package it.dadino.welcome.page;

import java.util.ArrayList;
import java.util.List;

import it.dadino.welcome.Illustration;

public class TutorialPageBuilder {

	private String  title;
	private String  subtitle;
	private int     backgroundColor;
	private int     footerColor;
	private boolean isExitPage;
	private List<Illustration> illustrations = new ArrayList<>();

	public static TutorialPage exit() {
		return new TutorialPageBuilder().setIsExitPage(true)
		                                .build();
	}

	public static TutorialPageBuilder create() {
		return new TutorialPageBuilder();
	}

	public TutorialPageBuilder title(String title) {
		this.title = title;
		return this;
	}

	public TutorialPageBuilder subtitle(String subtitle) {
		this.subtitle = subtitle;
		return this;
	}

	public TutorialPageBuilder backgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
		return this;
	}

	public TutorialPageBuilder footerColor(int footerColor) {
		this.footerColor = footerColor;
		return this;
	}

	public TutorialPageBuilder addIllustration(Illustration illustration) {
		this.illustrations.add(illustration);
		return this;
	}

	public TutorialPageBuilder setIllustrations(List<Illustration> illustrations) {
		this.illustrations = illustrations;
		return this;
	}

	private TutorialPageBuilder setIsExitPage(boolean isExitPage) {
		this.isExitPage = isExitPage;
		return this;
	}

	public TutorialPage build() {
		return new TutorialPage(title, subtitle, backgroundColor, footerColor, illustrations,
				isExitPage);
	}
}