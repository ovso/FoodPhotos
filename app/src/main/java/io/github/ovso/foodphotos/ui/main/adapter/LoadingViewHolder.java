package io.github.ovso.foodphotos.ui.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.ovso.foodphotos.R;
import timber.log.Timber;

/**
 * Created by Ahmed Abd-Elmeged on 2/20/2018.
 */
public class LoadingViewHolder extends RecyclerView.ViewHolder {

  @BindView(R.id.progressbar_loadingviewholder)
  ProgressBar loadingProgressBar;

  private LoadingViewHolder(View itemView, RetryCallback retryCallback) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public void bindTo(NetworkState networkState) {
    Timber.d("NetworkState = " + networkState);
  }

  public static LoadingViewHolder create(ViewGroup parent, RetryCallback retryCallback) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.item_loadingviewholder, parent, false);
    return new LoadingViewHolder(view, retryCallback);
  }
}
