package it.dadino.welcome.sample;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import it.dadino.welcome.WelcomeBuilder;
import it.dadino.welcome.WelcomeView;
import it.dadino.welcome.animated.Illustration;
import it.dadino.welcome.animated.Text;
import it.dadino.welcome.animations.AlphaAnimation;
import it.dadino.welcome.animations.Animation;
import it.dadino.welcome.animations.XAnimation;
import it.dadino.welcome.animations.YAnimation;
import it.dadino.welcome.page.TutorialPage;
import it.dadino.welcome.page.TutorialPageBuilder;

public class MainActivity extends AppCompatActivity {

	public static final float TITLE_POWER    = 1.2f;
	public static final float SUBTITLE_POWER = 1.5f;
	private WelcomeView welcome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showTutorial();
			}
		});
		showTutorial();
	}

	private void showTutorial() {

		welcome = WelcomeBuilder.with(this)
		                        .done("Done")
		                        .skip("Skip")
		                        .next("Next")
		                        .addPage(createPage(1, android.R.color.holo_orange_light,
				                        android.R.color.holo_orange_dark))
		                        .addPage(createPage(2, android.R.color.holo_blue_light,
				                        android.R.color.holo_blue_dark))
		                        .addPage(createPage(3, android.R.color.holo_green_light,
				                        android.R.color.holo_green_dark))
		                        .listener(new WelcomeView.WelcomeListener() {
			                        @Override
			                        public void onSkipClicked(int page) {
				                        Toast.makeText(MainActivity.this, "Skip clicked",
						                        Toast.LENGTH_SHORT)
				                             .show();
			                        }

			                        @Override
			                        public void onNextClicked(int page) {
				                        Toast.makeText(MainActivity.this, "Next clicked",
						                        Toast.LENGTH_SHORT)
				                             .show();
			                        }

			                        @Override
			                        public void onDoneClicked() {
				                        Toast.makeText(MainActivity.this, "Done clicked",
						                        Toast.LENGTH_SHORT)
				                             .show();
			                        }

			                        @Override
			                        public void onExitWelcome(int page) {
				                        Toast.makeText(MainActivity.this, "Exiting Welcome",
						                        Toast.LENGTH_SHORT)
				                             .show();
				                        welcome = null;
			                        }
		                        })
		                        .show();
	}

	private TutorialPage createPage(int pageNumber, @ColorRes int background,
	                                @ColorRes int footer) {
		final Animation titleAnim = new XAnimation().power(TITLE_POWER)
		                                            .interpolator(new DecelerateInterpolator());
		final Animation subtitleTransl = new XAnimation().power(SUBTITLE_POWER)
		                                                 .interpolator(
				                                                 new DecelerateInterpolator());
		final AlphaAnimation alphaAnimation = new AlphaAnimation();
		Text title = new Text("Title " + pageNumber).addAnimation(titleAnim)
		                                            .addAnimation(alphaAnimation);
		Text subtitle = new Text("Subtitle " + pageNumber).addAnimation(subtitleTransl)
		                                                  .addAnimation(alphaAnimation);
		Illustration ill1 = new Illustration(R.mipmap.ic_launcher).addAnimation(new XAnimation());
		Illustration ill2 = new Illustration(R.mipmap.ic_launcher).addAnimation(
				new XAnimation().power(1.1f)
				                .interpolator(new BounceInterpolator()));
		Illustration ill3 = new Illustration(R.mipmap.ic_launcher).addAnimation(
				new XAnimation().power(1.2f)
				                .interpolator(new AccelerateDecelerateInterpolator()));
		Illustration ill4 = new Illustration(R.mipmap.ic_launcher).addAnimation(
				new YAnimation().power(1.3f)
				                .interpolator(new AccelerateDecelerateInterpolator()))
		                                                          .addAnimation(
				                                                          new XAnimation().power(
						                                                          1.3f)
				                                                                          .interpolator(
						                                                                          new AccelerateDecelerateInterpolator()))
		                                                          .addAnimation(alphaAnimation);
		Illustration ill5 = new Illustration(R.mipmap.ic_launcher).addAnimation(
				new YAnimation().interpolator(new AccelerateDecelerateInterpolator()));

		return TutorialPageBuilder.create()
		                          .title(title)
		                          .subtitle(subtitle)
		                          .backgroundColor(background)
		                          .footerColor(footer)
		                          .addIllustration(ill1)
		                          .addIllustration(ill2)
		                          .addIllustration(ill3)
		                          .addIllustration(ill4)
		                          .addIllustration(ill5)
		                          .build();
	}

	@Override
	public void onBackPressed() {
		if (welcome != null) welcome.onBackPressed();
		super.onBackPressed();
	}
}
