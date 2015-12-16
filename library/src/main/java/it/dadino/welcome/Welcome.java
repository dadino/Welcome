package it.dadino.welcome;

import android.app.Activity;

import java.util.List;

import it.dadino.welcome.page.TutorialPage;

public class Welcome {

	private String                      skip;
	private String                      done;
	private String                      next;
	private List<TutorialPage>          pages;
	private WelcomeView.WelcomeListener listener;

	public Welcome(String skip, String done, String next, List<TutorialPage> pages,
	               WelcomeView.WelcomeListener listener) {
		this.skip = skip;
		this.done = done;
		this.next = next;
		this.pages = pages;
		this.listener = listener;
	}

	public String getSkip() {
		return skip;
	}

	public String getDone() {
		return done;
	}

	public String getNext() {
		return next;
	}

	public WelcomeView.WelcomeListener getListener() {
		return listener;
	}

	public List<TutorialPage> getPages() {
		return pages;
	}

	public void show(Activity activity) {
		new WelcomeView(activity, this).show(activity);
	}
}
