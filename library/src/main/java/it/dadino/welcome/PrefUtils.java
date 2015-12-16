package it.dadino.welcome;

import android.content.Context;
import android.preference.PreferenceManager;

public class PrefUtils {

	private static final String PREF_TUTORIAL_SEEN = "pref_tutorial_seen";

	public static boolean loadTutorialSeen(Context context) {
		if (context == null) return false;
		return PreferenceManager.getDefaultSharedPreferences(context)
		                        .getBoolean(PREF_TUTORIAL_SEEN, false);
	}

	public static void saveTutorialSeen(Context context, boolean tutorialSeen) {
		if (context == null) return;
		PreferenceManager.getDefaultSharedPreferences(context)
		                 .edit()
		                 .putBoolean(PREF_TUTORIAL_SEEN, tutorialSeen)
		                 .apply();
	}
}
