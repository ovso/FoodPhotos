package io.github.ovso.foodphotos.ui.main.adapter;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.ovso.foodphotos.R;
import io.github.ovso.foodphotos.data.network.model.Photo;
import io.github.ovso.foodphotos.ui.base.GlideApp;
import io.github.ovso.foodphotos.ui.base.adapter.AdapterDataModel;
import io.github.ovso.foodphotos.ui.base.adapter.AdapterView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import timber.log.Timber;

public class MainAdapter extends PagedListAdapter<Photo, RecyclerView.ViewHolder>
    implements AdapterView, AdapterDataModel<Photo> {

  private NetworkState networkState;

  private RetryCallback retryCallback;

  private List<Photo> items = new ArrayList<>();

  public MainAdapter(RetryCallback $retryCallback) {
    super(phtosDiffCallback);
    retryCallback = $retryCallback;
  }

  @NonNull @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    Timber.d("viewType = " + viewType);
    switch (viewType) {
      case R.layout.list_item_main:
        return MainViewHolder.create(parent);
      case R.layout.item_loadingviewholder:
        return LoadingViewHolder.create(parent, retryCallback);
      default:
        throw new IllegalArgumentException("unknown view type");
    }
  }

  @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof MainViewHolder) {
      ((MainViewHolder) holder).bind(getItem(position));
    } else if (holder instanceof LoadingViewHolder) {

    }
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

  public void setNetworkState(NetworkState newNetworkState) {
    if (getCurrentList() != null) {
      if (getCurrentList().size() != 0) {
        NetworkState previousState = this.networkState;
        boolean hadExtraRow = hasExtraRow();
        this.networkState = newNetworkState;
        boolean hasExtraRow = hasExtraRow();
        if (hadExtraRow != hasExtraRow) {
          if (hadExtraRow) {
            notifyItemRemoved(getItemCount());
          } else {
            notifyItemInserted(getItemCount());
          }
        } else if (hasExtraRow && previousState != newNetworkState) {
          notifyItemChanged(getItemCount() - 1);
        }
      }
    }
  }

  private boolean hasExtraRow() {
    return networkState != null && networkState != NetworkState.LOADED;
  }

  @Override
  public int getItemViewType(int position) {
    if (hasExtraRow() && position == getItemCount() - 1) {
      return R.layout.item_loadingviewholder;
    } else {
      return R.layout.list_item_main;
    }
  }

  public static class MainViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.imageview_mainviewholder) AppCompatImageView imageView;
    @BindView(R.id.textview_mainviewholder) TextView titleTextview;

    public MainViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
    public void bind(Photo photo) {
      GlideApp.with(itemView.getContext()).load(photo.getLink()).into(imageView);
      titleTextview.setText(photo.getTitle());
    }
    public static RecyclerView.ViewHolder create(ViewGroup parent) {
      LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
      View view = layoutInflater.inflate(R.layout.list_item_main, parent, false);
      return new MainViewHolder(view);
    }
  }

  private static DiffUtil.ItemCallback<Photo> phtosDiffCallback =
      new DiffUtil.ItemCallback<Photo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
          return oldItem.getTitle() == newItem.getTitle();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
          return Objects.equals(oldItem, newItem);
        }
      };
}
