package it.dadino.welcome;


import android.content.Context;

import it.dadino.welcome.page.TutorialPage;
import it.dadino.welcome.page.TutorialPageView;

public class WelcomeAdapter extends ViewPagerAdapter<TutorialPageView> {

	private final Context context;

	public WelcomeAdapter(Context context) {
		this.context = context;
	}

	public void addPage(TutorialPage page) {
		addView(new TutorialPageView(context, page), page.getTitle());
	}
}
