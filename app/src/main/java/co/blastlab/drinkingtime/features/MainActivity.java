package co.blastlab.drinkingtime.features;

import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import co.blastlab.drinkingtime.R;
import co.blastlab.drinkingtime.features.history.HistoryFragment_;
import co.blastlab.drinkingtime.features.order.OrderFragment_;
import co.blastlab.drinkingtime.features.settings.SettingsFragment_;
import co.blastlab.drinkingtime.widget.ViewPagerAdapter;

@EActivity(R.layout.a_main)
public class MainActivity extends AppCompatActivity {

	private static final int POS_ORDER = 0;
	private static final int POS_STATS = 1;
	private static final int POS_SETTINGS = 2;

	private boolean doubleBackToExitPressedOnce;

	@ViewById(R.id.tabs)
	TabLayout tabLayout;

	@ViewById(R.id.viewpager)
	ViewPager viewPager;

	@AfterViews
	protected void setup() {
		ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
		adapter.addFrag(new OrderFragment_(), getString(R.string.tab_order));
		adapter.addFrag(new HistoryFragment_(), getString(R.string.tab_history));
		adapter.addFrag(new SettingsFragment_(), getString(R.string.tab_settings));

		viewPager.setAdapter(adapter);

		tabLayout.setupWithViewPager(viewPager);

		for (int i = 0; i < tabLayout.getTabCount(); i++) {
			switch (i) {
				case POS_ORDER:
					tabLayout.getTabAt(i).setIcon(R.mipmap.cup);
					break;
				case POS_STATS:
					tabLayout.getTabAt(i).setIcon(R.mipmap.history);
					break;
				case POS_SETTINGS:
					tabLayout.getTabAt(i).setIcon(R.mipmap.settings);
					break;
			}
		}
	}

	@Override
	public void onBackPressed() {
		if (doubleBackToExitPressedOnce) {
			finish();
			return;
		}

		doubleBackToExitPressedOnce = true;
		Toast.makeText(this, getString(R.string.exit_info), Toast.LENGTH_SHORT).show();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				doubleBackToExitPressedOnce = false;
			}
		}, 2000);
	}
}
