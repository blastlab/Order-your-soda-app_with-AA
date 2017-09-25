package co.blastlab.drinkingtime.features.settings;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.PreferenceScreen;
import org.androidannotations.annotations.sharedpreferences.Pref;

import co.blastlab.drinkingtime.R;
import co.blastlab.drinkingtime.app.DrinkingTime;
import co.blastlab.drinkingtime.app.Preferences_;

@EFragment
@PreferenceScreen(R.xml.app_preferences)
public class SettingsFragment extends PreferenceFragmentCompat {

	@Pref
	protected Preferences_ prefs;

	@App
	protected DrinkingTime app;

	@Override
	public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
		// this is done using AndroidAnnotations
		// but method needs to be implemented
	}
}
