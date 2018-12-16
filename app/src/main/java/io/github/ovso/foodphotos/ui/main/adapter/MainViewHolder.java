package io.github.ovso.foodphotos.ui.main.adapter;

import android.content.Intent;
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
import io.github.ovso.foodphotos.ui.other.OtherActivity;

public class MainViewHolder extends RecyclerView.ViewHolder {
  @BindView(R.id.imageview_mainviewholder) AppCompatImageView imageView;
  @BindView(R.id.textview_mainviewholder) TextView titleTextview;

  public MainViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public void bind(Photo photo) {
    GlideApp.with(itemView.getContext()).load(photo.getLink()).into(imageView);
    titleTextview.setText(photo.getTitle());
    itemView.setOnClickListener(this::navigateToOther);
  }

  private void navigateToOther(View view) {

    Intent intent = new Intent(view.getContext(), OtherActivity.class);
    view.getContext().startActivity(intent);
  }

  public static RecyclerView.ViewHolder create(ViewGroup parent) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.list_item_main, parent, false);
    return new MainViewHolder(view);
  }
}
