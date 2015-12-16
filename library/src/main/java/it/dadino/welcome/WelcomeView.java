package it.dadino.welcome;

import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import it.dadino.welcome.page.TutorialPage;
import it.dadino.welcome.page.TutorialPageBuilder;
import it.dadino.welcome.page.TutorialPageView;


public class WelcomeView extends FrameLayout {

	private final WelcomeListener mListener;
	private final Welcome         mWelcome;
	private       View            background;
	private       ViewPager       pager;
	private       Button          skip;
	private       Button          next;
	private       View            footer;
	private       int             mNumberOfPages;
	private       WelcomeAdapter  adapter;

	public WelcomeView(Context context, Welcome welcome) {
		super(context);
		this.mWelcome = welcome;
		this.mListener = welcome.getListener();
		init();
	}

	private void init() {
		inflate(getContext(), R.layout.welcome_view, this);
		initializeViews();
	}

	public void show(Activity activity) {
		//TODO
		ViewGroup root = (ViewGroup) activity.findViewById(android.R.id.content);
		root.addView(this);
	}

	private void prepareStatusBarColor() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window window = ((Activity) getContext()).getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		}
	}

	public void onNextClicked() {
		if (pager.getCurrentItem() < adapter.getCount() - 1) {
			if (mListener != null) mListener.onNextClicked(pager.getCurrentItem());
			pager.setCurrentItem(pager.getCurrentItem() + 1, true);
		} else {
			if (mListener != null) mListener.onDoneClicked();
			exitTutorial();
		}
	}

	public void onSkipClicked() {
		if (mListener != null) mListener.onSkipClicked(pager.getCurrentItem());
		exitTutorial();
	}


	private void exitTutorial() {
		if (mListener != null) mListener.onExitWelcome(pager.getCurrentItem());
		PrefUtils.saveTutorialSeen(getContext(), true);
		((ViewGroup) getParent()).removeView(this);
	}

	public void onBackPressed() {
		exitTutorial();
	}

	public void initializeViews() {
		prepareStatusBarColor();
		CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
		background = findViewById(R.id.root);
		pager = (ViewPager) findViewById(R.id.pager);
		skip = (Button) findViewById(R.id.skip);
		next = (Button) findViewById(R.id.next);
		footer = findViewById(R.id.footer);

		skip.setText(mWelcome.getSkip());

		next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onNextClicked();
			}
		});

		skip.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onSkipClicked();
			}
		});

		adapter = new WelcomeAdapter(getContext());
		for (TutorialPage page : mWelcome.getPages()) {
			adapter.addPage(page);
		}
		adapter.addPage(TutorialPageBuilder.exit());

		mNumberOfPages = adapter.getCount();
		pager.setAdapter(adapter);
		indicator.setViewPager(pager);
		pager.setPageTransformer(true, new CrossfadePageTransformer());
		pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			final ArgbEvaluator argbEvaluator = new ArgbEvaluator();
			int rightColor;
			int rightFooterColor;
			int finalColor;
			int finalFooterColor;

			@Override
			public void onPageScrolled(int position, float positionOffset,
			                           int positionOffsetPixels) {
				//Background Color
				rightColor = R.color.transparent;
				if (adapter.getCount() - 2 > position) rightColor = adapter.getView(position + 1)
				                                                           .pageColor();

				finalColor = (int) argbEvaluator.evaluate(positionOffset, ContextCompat.getColor(
						getContext(), adapter.getView(position)
						                     .pageColor()), ContextCompat.getColor(getContext(),
						rightColor));

				background.setBackgroundColor(finalColor);

				//Footer Color
				rightFooterColor = R.color.transparent;
				if (adapter.getCount() - 2 > position) rightFooterColor = adapter.getView(
						position + 1)
				                                                                 .pageFooterColor();

				finalFooterColor = (int) argbEvaluator.evaluate(positionOffset,
						ContextCompat.getColor(getContext(), adapter.getView(position)
						                                            .pageFooterColor()),
						ContextCompat.getColor(getContext(), rightFooterColor));

				footer.setBackgroundColor(finalFooterColor);
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
					((Activity) getContext()).getWindow()
					                         .setStatusBarColor(finalFooterColor);
			}

			@Override
			public void onPageSelected(int position) {

				if (position == mNumberOfPages - 2) {
					skip.setVisibility(View.INVISIBLE);
					next.setText(mWelcome.getDone());
					//done.setVisibility(View.VISIBLE);
				} else if (position < mNumberOfPages - 2) {
					skip.setVisibility(View.VISIBLE);
					next.setText(mWelcome.getNext());
				} else if (position == mNumberOfPages - 1) {
					exitTutorial();
				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				//Unused
			}
		});

		final TutorialPageView view = adapter.getView(0);
		view.getViewTreeObserver()
		    .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			    @Override
			    public void onGlobalLayout() {
				    footer.getLayoutParams().height = view.getFooterHeight();
				    ((RelativeLayout.LayoutParams) footer.getLayoutParams()).addRule(
						    RelativeLayout.ALIGN_PARENT_BOTTOM);

				    if (view.getFooterHeight() > 0)
					    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
						    view.getViewTreeObserver()
						        .removeOnGlobalLayoutListener(this);
					    else view.getViewTreeObserver()
					             .removeGlobalOnLayoutListener(this);
			    }
		    });

		int initialFooterColor = ContextCompat.getColor(getContext(), adapter.getView(0)
		                                                                     .pageFooterColor());
		footer.setBackgroundColor(initialFooterColor);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
			((Activity) getContext()).getWindow()
			                         .setStatusBarColor(initialFooterColor);
	}


	public interface WelcomeListener {

		void onSkipClicked(int page);
		void onNextClicked(int page);
		void onDoneClicked();
		void onExitWelcome(int page);
	}

	public class CrossfadePageTransformer implements ViewPager.PageTransformer {

		@Override
		public void transformPage(View page, float position) {
			if (page instanceof TutorialPageView) {
				((TutorialPageView) page).transform(position);
			}
		}
	}
}
