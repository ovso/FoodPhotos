package io.github.ovso.foodphotos.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;
import io.github.ovso.foodphotos.R;

public abstract class BaseActivity extends DaggerAppCompatActivity {
  @BindView(R.id.toolbar) protected Toolbar toolbar;

  private Unbinder bind;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResId());
    setupButterKnife();
    setupActionBar();
  }

  private void setupActionBar() {
    setSupportActionBar(toolbar);
  }

  private void setupButterKnife() {
    bind = ButterKnife.bind(this);
  }

  protected abstract int getLayoutResId();

  @Override protected void onDestroy() {
    super.onDestroy();
    bind.unbind();
  }
}