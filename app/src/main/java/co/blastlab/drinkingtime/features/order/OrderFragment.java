package co.blastlab.drinkingtime.features.order;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import co.blastlab.drinkingtime.R;

@EFragment(R.layout.f_order)
public class OrderFragment extends Fragment {

	boolean orderPlaced;

	@ViewById(R.id.orderProgress)
	ProgressBar orderProgressBar;

	@ViewById(R.id.orderView)
	LinearLayout orderView;

	@Click(R.id.orderButton)
	void placeOrder() {
		if (orderPlaced) {
			Toast.makeText(getContext(), getString(R.string.order_in_progress), Toast.LENGTH_SHORT).show();
			return;
		}
		orderPlaced = true;
		orderProgressBar.setVisibility(View.VISIBLE);
		orderAsyncMock();

		Snackbar.make(orderView, getString(R.string.order_placed), Snackbar.LENGTH_LONG).show();
	}

	/*
		Mock of order behaviour
		After delay we are hiding progress bar and showing snackbar
	 */
	@UiThread(delay = 4500)
	void orderAsyncMock() {
		orderPlaced = false;
		orderProgressBar.setVisibility(View.INVISIBLE);

		Snackbar.make(orderView, getString(R.string.order_finished), Snackbar.LENGTH_LONG).show();
	}
}
