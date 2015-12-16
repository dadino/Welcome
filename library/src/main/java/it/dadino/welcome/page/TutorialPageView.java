package it.dadino.welcome.page;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import it.dadino.welcome.R;
import it.dadino.welcome.animated.Illustration;
import it.dadino.welcome.animations.PageInfo;


public class TutorialPageView extends LinearLayout {

	public TextView    title;
	public TextView    subtitle;
	public FrameLayout illustrations;
	public View        footer;

	private TutorialPage mTutorialPage;
	private PageInfo     mPageInfo;


	public TutorialPageView(Context context, TutorialPage tutorialPage) {
		super(context);
		this.mTutorialPage = tutorialPage;
		init();
	}

	private void init() {
		setOrientation(VERTICAL);
		inflate(getContext(), layout(), this);

		title = (TextView) findViewById(R.id.page_title);
		subtitle = (TextView) findViewById(R.id.page_subtitle);
		illustrations = (FrameLayout) findViewById(R.id.page_illustrations);
		footer = findViewById(R.id.page_footer);

		if (title != null) {
			mTutorialPage.getTitle()
			             .setView(title);
		}
		if (subtitle != null) {
			mTutorialPage.getSubtitle()
			             .setView(subtitle);
		}

		inflateIllustrations();
	}

	private void inflateIllustrations() {
		if (illustrations != null)
			for (Illustration illustration : mTutorialPage.getIllustrations()) {
			ImageView image = new ImageView(getContext());
			image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
			FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
					FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
			image.setLayoutParams(params);
				illustration.setView(image);
			illustrations.addView(image);
		}
	}

	public void transform(float fraction) {
		if (mPageInfo == null) mPageInfo = new PageInfo(getWidth(), getHeight());

		if (title != null) {
			mTutorialPage.getTitle()
			             .animate(mPageInfo, fraction);
		}
		if (subtitle != null) {
			mTutorialPage.getSubtitle()
			             .animate(mPageInfo, fraction);
		}

		if (illustrations != null) {
			for (Illustration illustration : mTutorialPage.getIllustrations()) {
				illustration.animate(mPageInfo, fraction);
			}
		}
	}

	public int getFooterHeight() {
		return getHeight() - (footer != null ? footer.getTop() : 0);
	}

	private int layout() {
		if (!mTutorialPage.isExitPage()) return R.layout.tutorial_page;
		else return R.layout.tutorial_page_exit;
	}

	public int backgroundColor() {
		return mTutorialPage.getBackgroundColor();
	}

	public int footerColor() {
		return mTutorialPage.getFooterColor();
	}
}
