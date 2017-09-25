package co.blastlab.drinkingtime.widget;

import android.view.View;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class ClickableRecyclerViewAdapter<T, V extends View & Bindable<T>> extends BaseRecyclerViewAdapter<T, V> {

	private OnItemClickListener<T> onItemClickListener;

	@Override
	public void onBindViewHolder(final SingleViewHolder<V> holder, int position) {
		super.onBindViewHolder(holder, position);

		holder.getView().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (onItemClickListener != null && holder.getAdapterPosition() >= 0 && !getItems().isEmpty()) {
					onItemClickListener.onItemClick(getItems().get(holder.getAdapterPosition()), v, holder.getAdapterPosition());
				}
			}
		});
	}

	public void setOnItemClickListener(OnItemClickListener<T> listener) {
		this.onItemClickListener = listener;
	}

	public interface OnItemClickListener<T> {
		void onItemClick(T item, View v, int position);
	}
}
