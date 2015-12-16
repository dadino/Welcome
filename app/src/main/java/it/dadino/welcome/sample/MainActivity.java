package it.dadino.welcome.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Animation titleAnim = new XAnimation().power(TITLE_POWER)
		                                            .interpolator(new DecelerateInterpolator());
		final Animation subtitleTransl = new XAnimation().power(SUBTITLE_POWER)
		                                                 .interpolator(
				                                                 new DecelerateInterpolator());
		final AlphaAnimation alphaAnimation = new AlphaAnimation();

		Text title1 = new Text("Title 1").addAnimation(titleAnim)
		                                 .addAnimation(alphaAnimation);
		Text subtitle1 = new Text("Subtitle 1").addAnimation(subtitleTransl)
		                                       .addAnimation(alphaAnimation);
		Text title2 = new Text("Title 2").addAnimation(titleAnim)
		                                 .addAnimation(alphaAnimation);
		Text subtitle2 = new Text("Subtitle 2").addAnimation(subtitleTransl)
		                                       .addAnimation(alphaAnimation);
		Text title3 = new Text("Title 3").addAnimation(titleAnim)
		                                 .addAnimation(alphaAnimation);
		Text subtitle3 = new Text("Subtitle 3").addAnimation(subtitleTransl)
		                                       .addAnimation(alphaAnimation);

		Illustration ill11 = new Illustration(R.mipmap.ic_launcher).addAnimation(new XAnimation());
		Illustration ill12 = new Illustration(R.mipmap.ic_launcher).addAnimation(
				new XAnimation().power(1.1f)
				                .interpolator(new BounceInterpolator()));
		Illustration ill13 = new Illustration(R.mipmap.ic_launcher).addAnimation(
				new XAnimation().power(1.2f)
				                .interpolator(new AccelerateDecelerateInterpolator()));
		Illustration ill14 = new Illustration(R.mipmap.ic_launcher).addAnimation(
				new XAnimation().power(1.3f)
				                .interpolator(new AnticipateOvershootInterpolator()))
		                                                           .addAnimation(alphaAnimation);
		Illustration ill15 = new Illustration(R.mipmap.ic_launcher).addAnimation(
				new YAnimation().interpolator(new AccelerateDecelerateInterpolator()));

		TutorialPage page1 = TutorialPageBuilder.create()
		                                        .title(title1)
		                                        .subtitle(subtitle1)
		                                        .backgroundColor(android.R.color.holo_orange_light)
		                                        .footerColor(android.R.color.holo_orange_dark)
		                                        .addIllustration(ill11)
		                                        .addIllustration(ill12)
		                                        .addIllustration(ill13)
		                                        .addIllustration(ill14)
		                                        .addIllustration(ill15)
		                                        .build();

		TutorialPage page2 = TutorialPageBuilder.create()
		                                        .title(title2)
		                                        .subtitle(subtitle2)
		                                        .backgroundColor(android.R.color.holo_blue_bright)
		                                        .footerColor(android.R.color.holo_blue_dark)
		                                        .addIllustration(ill11)
		                                        .addIllustration(ill12)
		                                        .addIllustration(ill13)
		                                        .addIllustration(ill14)
		                                        .addIllustration(ill15)
		                                        .build();

		TutorialPage page3 = TutorialPageBuilder.create()
		                                        .title(title3)
		                                        .subtitle(subtitle3)
		                                        .backgroundColor(android.R.color.holo_green_light)
		                                        .footerColor(android.R.color.holo_green_dark)
		                                        .addIllustration(ill11)
		                                        .addIllustration(ill12)
		                                        .addIllustration(ill13)
		                                        .addIllustration(ill14)
		                                        .addIllustration(ill15)
		                                        .build();

		WelcomeBuilder.with(this)
		              .done("Done")
		              .skip("Skip")
		              .next("Next")
		              .addPage(page1)
		              .addPage(page2)
		              .addPage(page3)
		              .listener(new WelcomeView.WelcomeListener() {
			              @Override
			              public void onSkipClicked(int page) {
				              Toast.makeText(MainActivity.this, "Skip clicked", Toast.LENGTH_SHORT)
				                   .show();
			              }

			              @Override
			              public void onNextClicked(int page) {
				              Toast.makeText(MainActivity.this, "Next clicked", Toast.LENGTH_SHORT)
				                   .show();
			              }

			              @Override
			              public void onDoneClicked() {
				              Toast.makeText(MainActivity.this, "Done clicked", Toast.LENGTH_SHORT)
				                   .show();
			              }

			              @Override
			              public void onExitWelcome(int page) {
				              Toast.makeText(MainActivity.this, "Exiting Welcome",
						              Toast.LENGTH_SHORT)
				                   .show();
			              }
		              })
		              .show();
	}
}
