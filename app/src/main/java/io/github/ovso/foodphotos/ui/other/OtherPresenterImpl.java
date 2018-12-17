package io.github.ovso.foodphotos.ui.other;

import io.reactivex.disposables.CompositeDisposable;

public class OtherPresenterImpl implements OtherPresenter {

  private OtherPresenter.View view;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();

  public OtherPresenterImpl(OtherPresenter.View $view) {
    view = $view;
  }

  @Override public void onCreate() {
    view.setupRecyclerView();
  }

  @Override public void onDestroy() {
    compositeDisposable.clear();
  }
}