package io.github.ovso.foodphotos.ui.other.adapter;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import io.github.ovso.foodphotos.data.network.model.Photo;
import io.github.ovso.foodphotos.ui.base.adapter.AdapterDataModel;
import io.github.ovso.foodphotos.ui.base.adapter.AdapterView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OtherAdapter extends PagedListAdapter<Photo, RecyclerView.ViewHolder>
    implements AdapterView, AdapterDataModel<Photo> {

  private List<Photo> items = new ArrayList<>();

  public OtherAdapter() {
    super(photosDiffCallback);
  }

  @NonNull @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return OtherViewHolder.create(parent);
  }

  @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    ((OtherViewHolder) holder).bind(getItem(position));
  }

  @Override public void addItems(List $items) {
    items.addAll($items);
  }

  @Override public void setItems(List $items) {
    items.clear();
    items.addAll($items);
  }

  @Override public void addItem(Photo $item) {

  }

  @Override public void setItem(Photo $item) {

  }

  @Override public int getSize() {
    return items.size();
  }

  @Override public void clear() {
    items.clear();
  }

  @Override public void refresh() {
    notifyItemRangeChanged(0, getSize());
  }

  private static DiffUtil.ItemCallback<Photo> photosDiffCallback =
      new DiffUtil.ItemCallback<Photo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
          return oldItem.getTitle().equals(newItem.getTitle());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
          return Objects.equals(oldItem, newItem);
        }
      };
}
