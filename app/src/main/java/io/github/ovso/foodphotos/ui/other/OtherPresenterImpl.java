package io.github.ovso.foodphotos.ui.other;

import timber.log.Timber;

public class OtherPresenterImpl implements OtherPresenter {

  private OtherPresenter.View view;
  public OtherPresenterImpl(OtherPresenter.View $view) {
    view = $view;
  }

  @Override public void onCreate() {
    view.setupRecyclerView();
  }

  @Override public void onDestroy() {

  }
}
