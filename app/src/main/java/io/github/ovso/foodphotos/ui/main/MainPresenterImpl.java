package io.github.ovso.foodphotos.ui.main;

import io.github.ovso.foodphotos.data.network.MainRequest;
import io.github.ovso.foodphotos.data.network.model.SearchResult;
import io.github.ovso.foodphotos.data.network.model.SearchResultItem;
import io.github.ovso.foodphotos.ui.base.adapter.AdapterDataModel;
import io.github.ovso.foodphotos.utils.SchedulersFacade;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

public class MainPresenterImpl implements MainPresenter {

  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private MainPresenter.View view;
  private MainRequest net;
  private SchedulersFacade schedulersFacade;
  private AdapterDataModel<SearchResultItem> adapterDataModel;

  public MainPresenterImpl(MainPresenter.View $view, MainRequest $net,
      SchedulersFacade $schedulersFacade, AdapterDataModel<SearchResultItem> a$dapterDataModel) {
    view = $view;
    net = $net;
    schedulersFacade = $schedulersFacade;
    adapterDataModel = a$dapterDataModel;
  }

  @Override public void onCreate() {
    view.setupRecyclerView();
    compositeDisposable.add(net.getResult()
        .subscribeOn(schedulersFacade.io())
        .observeOn(schedulersFacade.ui())
        .subscribe(
            new Consumer<SearchResult>() {
              @Override public void accept(SearchResult result) throws Exception {
                adapterDataModel.addItems(result.getItems());
                view.refresh();
              }
            }, new Consumer<Throwable>() {
              @Override public void accept(Throwable throwable) throws Exception {
                Timber.d(throwable);
              }
            }));
  }
}