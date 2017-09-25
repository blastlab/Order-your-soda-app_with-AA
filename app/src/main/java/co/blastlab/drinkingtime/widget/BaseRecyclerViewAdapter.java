package co.blastlab.drinkingtime.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class BaseRecyclerViewAdapter<T, V extends View & Bindable<T>> extends RecyclerView.Adapter<SingleViewHolder<V>> {

	private List<T> items = new ArrayList<>();

	@Override
	public int getItemCount() {
		return items.size();
	}

	@Override
	public final SingleViewHolder<V> onCreateViewHolder(ViewGroup parent, int viewType) {
		return new SingleViewHolder<>(onCreateItemView(parent, viewType));
	}

	@Override
	public void onBindViewHolder(SingleViewHolder<V> holder, int position) {
		T item = getItems().get(position);
		V view = holder.getView();
		view.bind(item);
	}

	public List<T> getItems() {
		return items;
	}

	/**
	 * Sets the list on which this adapter works as the reference to given object.
	 * All next operations on adapter data set, like {@link #add(Object)} or {@link #addAll(Collection)},
	 * will be executed on this object.
	 * Without calling this method adapter will work on it'setupFragment own list
	 * and only item objects will be used by reference, not whole list.
	 */
	public void setList(List<T> items) {
		this.items = items;
		notifyDataSetChanged();
	}

	public void add(T item) {
		items.add(item);
		notifyItemInserted(getItemCount() - 1);
	}

	public void addAll(Collection<T> collection) {
		items.addAll(collection);
		notifyDataSetChanged();
	}

	public void clear() {
		items.clear();
	}

	protected abstract V onCreateItemView(ViewGroup parent, int viewType);
}
