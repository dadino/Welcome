package it.dadino.welcome.page;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import it.dadino.welcome.Illustration;
import it.dadino.welcome.R;
import it.dadino.welcome.animations.Animation;
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
		inflate(getContext(), pageLayout(), this);

		title = (TextView) findViewById(R.id.page_title);
		subtitle = (TextView) findViewById(R.id.page_subtitle);
		illustrations = (FrameLayout) findViewById(R.id.page_illustrations);
		footer = findViewById(R.id.footer);

		if (title != null) title.setText(mTutorialPage.getTitle());
		if (subtitle != null) subtitle.setText(mTutorialPage.getSubtitle());

		inflateIllustrations();
	}

	private void inflateIllustrations() {
		if (illustrations != null) for (Illustration i : mTutorialPage.getIllustrations()) {
			ImageView image = new ImageView(getContext());
			image.setImageResource(i.getDrawable());
			image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
			FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
					FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
			image.setLayoutParams(params);
			illustrations.addView(image);
		}
	}

	public void transform(float position) {
		if (mPageInfo == null) mPageInfo = new PageInfo(getWidth(), getHeight());

		if (title != null) {
			title.setTranslationX(mPageInfo.width * position * 1.2f);
			title.setAlpha(1.0f - Math.abs(position));
		}
		if (subtitle != null) {
			subtitle.setTranslationX(mPageInfo.width * position * 1.5f);
			subtitle.setAlpha(1.0f - Math.abs(position));
		}

		if (illustrations != null) {
			for (int i = 0; i < illustrations.getChildCount(); i++) {
				for (Animation animation : mTutorialPage.getIllustrations()
				                                        .get(i)
				                                        .getAnimations()) {
					animation.animate(illustrations.getChildAt(i), position, mPageInfo);
				}
			}
		}
	}

	public int getFooterHeight() {
		return getHeight() - (footer != null ? footer.getTop() : 0);
	}

	private int pageLayout() {
		if (!mTutorialPage.isExitPage()) return R.layout.tutorial_page;
		else return R.layout.tutorial_page_exit;
	}

	public int pageColor() {
		return mTutorialPage.getBackgroundColor();
	}

	public int pageFooterColor() {
		return mTutorialPage.getFooterColor();
	}
}
