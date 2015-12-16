package it.dadino.welcome.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;

import it.dadino.welcome.Illustration;
import it.dadino.welcome.WelcomeBuilder;
import it.dadino.welcome.WelcomeView;
import it.dadino.welcome.animations.AlphaAnimation;
import it.dadino.welcome.animations.TranslationXAnimation;
import it.dadino.welcome.animations.TranslationYAnimation;
import it.dadino.welcome.page.TutorialPage;
import it.dadino.welcome.page.TutorialPageBuilder;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Illustration ill11 = new Illustration(R.mipmap.ic_launcher).addAnimation(
				new TranslationXAnimation());
		Illustration ill12 = new Illustration(R.mipmap.ic_launcher).addAnimation(
				new TranslationXAnimation().power(1.1f)
				                           .interpolator(new BounceInterpolator()));
		Illustration ill13 = new Illustration(R.mipmap.ic_launcher).addAnimation(
				new TranslationXAnimation().power(1.2f)
				                           .interpolator(new AccelerateDecelerateInterpolator()));
		Illustration ill14 = new Illustration(R.mipmap.ic_launcher).addAnimation(
				new TranslationXAnimation().power(1.3f)
				                           .interpolator(new AnticipateOvershootInterpolator()))
		                                                           .addAnimation(
				                                                           new AlphaAnimation());
		Illustration ill15 = new Illustration(R.mipmap.ic_launcher).addAnimation(
				new TranslationYAnimation().interpolator(new AccelerateDecelerateInterpolator()));

		TutorialPage page1 = TutorialPageBuilder.create()
		                                        .title("Page 1")
		                                        .subtitle("Subtitle 1")
		                                        .backgroundColor(android.R.color.holo_orange_light)
		                                        .footerColor(android.R.color.holo_orange_dark)
		                                        .addIllustration(ill11)
		                                        .addIllustration(ill12)
		                                        .addIllustration(ill13)
		                                        .addIllustration(ill14)
		                                        .addIllustration(ill15)
		                                        .build();
		TutorialPage page2 = TutorialPageBuilder.create()
		                                        .title("Page 2")
		                                        .subtitle("Subtitle 2")
		                                        .backgroundColor(android.R.color.holo_blue_bright)
		                                        .footerColor(android.R.color.holo_blue_dark)
		                                        .addIllustration(ill11)
		                                        .addIllustration(ill12)
		                                        .addIllustration(ill13)
		                                        .addIllustration(ill14)
		                                        .addIllustration(ill15)
		                                        .build();
		TutorialPage page3 = TutorialPageBuilder.create()
		                                        .title("Page 3")
		                                        .subtitle("Subtitle 3")
		                                        .backgroundColor(android.R.color.holo_green_light)
		                                        .footerColor(android.R.color.holo_green_dark)
		                                        .addIllustration(ill11)
		                                        .addIllustration(ill12)
		                                        .addIllustration(ill13)
		                                        .addIllustration(ill14)
		                                        .addIllustration(ill15)
		                                        .build();

		WelcomeBuilder.with(this)
		              .done("Fatto")
		              .skip("Salta")
		              .next("Avanti")
		              .addPage(page1)
		              .addPage(page2)
		              .addPage(page3)
		              .listener(new WelcomeView.WelcomeListener() {
			              @Override
			              public void onSkipClicked(int page) {

			              }

			              @Override
			              public void onNextClicked(int page) {

			              }

			              @Override
			              public void onDoneClicked() {

			              }

			              @Override
			              public void onExitWelcome(int page) {

			              }
		              })
		              .show();
	}
}
