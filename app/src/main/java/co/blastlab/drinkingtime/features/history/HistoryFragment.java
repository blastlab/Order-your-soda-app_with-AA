package co.blastlab.drinkingtime.features.history;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import co.blastlab.drinkingtime.R;
import co.blastlab.drinkingtime.features.MainActivity;
import co.blastlab.drinkingtime.widget.Bindable;
import co.blastlab.drinkingtime.widget.ClickableRecyclerViewAdapter;
import co.blastlab.drinkingtime.widget.DividerItemDecoration;
import co.blastlab.drinkingtime.widget.SingleViewHolder;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

/*
	Temporary usage of graph library
 */
@EFragment(R.layout.f_history)
public class HistoryFragment extends Fragment {

	static final int MIN_PLOT_VAL = 0;
	static final int MAX_PLOT_VAL = 4;

	@ViewById(R.id.timeChart)
	protected LineChartView chart;

	@ViewById(R.id.listview)
	protected RecyclerView recyclerView;

	@Bean
	protected OrderViewAdapter adapter;

	@AfterViews
	void setupChart() {
		// setting plot with fake data
		Random r = new Random();
		List<PointValue> values = new ArrayList<>();
		for (int i = 0; i <= 8; i++) {
			values.add(new PointValue(
				r.nextInt(MAX_PLOT_VAL - MIN_PLOT_VAL + 1) + MIN_PLOT_VAL,
				r.nextInt(MAX_PLOT_VAL - MIN_PLOT_VAL + 1) + MIN_PLOT_VAL
			));
		}

		//In most cased you can call data model methods in builder-pattern-like manner.
		Line line = new Line(values)
			.setColor(Color.BLUE)
			.setCubic(false)
			.setStrokeWidth(2);

		List<Line> lines = new ArrayList<>();
		lines.add(line);

		Axis axisY = new Axis().setHasLines(true);

		LineChartData data = new LineChartData();
		data.setAxisYLeft(axisY);
		data.setLines(lines);

		chart.setLineChartData(data);
		chart.setInteractive(false);

		// setting list with fake data
		final ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i <= 8; i++) {
			list.add("Order " + i);
		}
		final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

		recyclerView.setLayoutManager(layoutManager);
		recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
		recyclerView.setHasFixedSize(true);
		recyclerView.setAdapter(adapter);

		// TODO: load data in background task

		adapter.clear();
		adapter.addAll(list);
		adapter.notifyDataSetChanged();
	}

	@EBean
	static class OrderViewAdapter extends ClickableRecyclerViewAdapter<String, OrderItemView> {

		@RootContext
		MainActivity context;

		OrderViewAdapter() {
			setHasStableIds(true);
		}

		@Override
		protected OrderItemView onCreateItemView(ViewGroup parent, int viewType) {
			return HistoryFragment_.OrderItemView_.build(context);
		}

		@Override
		public void onBindViewHolder(final SingleViewHolder<OrderItemView> holder, int position) {
			super.onBindViewHolder(holder, position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
	}

	@EViewGroup(R.layout.i_order)
	public static class OrderItemView extends RelativeLayout implements Bindable<String> {

		@ViewById(R.id.icon)
		protected ImageView icon;

		@ViewById(R.id.secondLine)
		protected TextView secondLine;

		@ViewById(R.id.firstLine)
		protected TextView firstLine;

		public OrderItemView(Context context) {
			super(context);
		}

		@Override
		public void bind(String order) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String currentDateAndTime = sdf.format(new Date());

			firstLine.setText(order);
			secondLine.setText(currentDateAndTime);
		}
	}
}
