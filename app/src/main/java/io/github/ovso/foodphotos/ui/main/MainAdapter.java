package io.github.ovso.foodphotos.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.ovso.foodphotos.R;
import io.github.ovso.foodphotos.data.network.model.SearchResultItem;
import io.github.ovso.foodphotos.ui.base.GlideApp;
import io.github.ovso.foodphotos.ui.base.adapter.AdapterDataModel;
import io.github.ovso.foodphotos.ui.base.adapter.AdapterView;
import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter
    implements AdapterView, AdapterDataModel<SearchResultItem> {

  private List<SearchResultItem> items = new ArrayList<>();

  @NonNull @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new MainViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_main, null));
  }

  @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof MainViewHolder) {
      AppCompatImageView imageView = ((MainViewHolder) holder).imageView;
      Context context = imageView.getContext();
      GlideApp.with(context).load(items.get(position).getThumbnail()).into(imageView);
    }
  }

  @Override public int getItemCount() {
    return getSize();
  }

  @Override public void addItems(List $items) {
    items.addAll($items);
  }

  @Override public void setItems(List $items) {
    items.clear();
    items.addAll($items);
  }

  @Override public void addItem(SearchResultItem $item) {

  }

  @Override public void setItem(SearchResultItem $item) {

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

  public static class MainViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image_view) AppCompatImageView imageView;

    public MainViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
