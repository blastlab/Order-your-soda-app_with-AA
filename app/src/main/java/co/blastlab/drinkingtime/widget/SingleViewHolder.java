package co.blastlab.drinkingtime.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;

@SuppressWarnings({"unused", "WeakerAccess"})
public class SingleViewHolder<V extends View> extends RecyclerView.ViewHolder {

	private V view;

	public SingleViewHolder(V itemView) {
		super(itemView);
		view = itemView;
	}

	public V getView() {
		return view;
	}
}