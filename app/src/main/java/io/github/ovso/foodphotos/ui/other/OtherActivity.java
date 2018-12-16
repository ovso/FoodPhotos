package io.github.ovso.foodphotos.ui.other;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import io.github.ovso.foodphotos.R;
import io.github.ovso.foodphotos.ui.base.BaseActivity;
import javax.inject.Inject;

public class OtherActivity extends BaseActivity implements OtherPresenter.View {
  @BindView(R.id.recyclerview_other) RecyclerView recyclerView;
  @Inject OtherPresenter presenter;

  @Override protected int getLayoutResId() {
    return R.layout.activity_other;
  }

  @Override public void setupRecyclerView() {
    recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
  }
}