package io.github.ovso.foodphotos.ui.other.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.ovso.foodphotos.R;
import io.github.ovso.foodphotos.ui.main.adapter.NetworkState;
import io.github.ovso.foodphotos.ui.main.adapter.RetryCallback;
import timber.log.Timber;

public class OtherLoadingViewHolder extends RecyclerView.ViewHolder {

  private OtherLoadingViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public void bindTo(NetworkState networkState) {
    Timber.d("NetworkState = " + networkState);
  }

  public static OtherLoadingViewHolder create(ViewGroup parent) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.item_loadingviewholder, parent, false);
    return new OtherLoadingViewHolder(view);
  }
}