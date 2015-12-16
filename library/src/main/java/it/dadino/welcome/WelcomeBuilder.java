package it.dadino.welcome;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import it.dadino.welcome.page.TutorialPage;

public class WelcomeBuilder {

	private final Activity activity;
	private String                      skip     = "Skip";
	private String                      done     = "Done";
	private String                      next     = "Next";
	private List<TutorialPage>          pages    = new ArrayList<>();
	private WelcomeView.WelcomeListener listener = null;

	public WelcomeBuilder(Activity activity) {
		this.activity = activity;
	}

	public static WelcomeBuilder with(Activity activity) {
		return new WelcomeBuilder(activity);
	}

	public WelcomeBuilder skip(String skip) {
		this.skip = skip;
		return this;
	}

	public WelcomeBuilder done(String done) {
		this.done = done;
		return this;
	}

	public WelcomeBuilder next(String next) {
		this.next = next;
		return this;
	}

	public WelcomeBuilder addPage(TutorialPage page) {
		this.pages.add(page);
		return this;
	}

	public WelcomeBuilder pages(List<TutorialPage> pages) {
		this.pages = pages;
		return this;
	}

	public WelcomeBuilder listener(WelcomeView.WelcomeListener listener) {
		this.listener = listener;
		return this;
	}

	public WelcomeView show() {
		Welcome welcome = new Welcome(skip, done, next, pages, listener);
		WelcomeView welcomeView = new WelcomeView(activity, welcome);
		welcomeView.show(activity);
		return welcomeView;
	}
}