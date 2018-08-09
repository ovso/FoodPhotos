package io.github.ovso.foodphotos.ui.main;

import io.github.ovso.foodphotos.data.network.MainNet;
import io.github.ovso.foodphotos.data.network.model.SearchResult;
import io.github.ovso.foodphotos.data.network.model.SearchResultItem;
import io.github.ovso.foodphotos.utils.SchedulersFacade;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.List;
import timber.log.Timber;

public class MainPresenterImpl implements MainPresenter {

  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private MainPresenter.View view;
  private MainNet net;
  private SchedulersFacade schedulersFacade;

  public MainPresenterImpl(MainPresenter.View $view, MainNet $net,
      SchedulersFacade $schedulersFacade) {
    view = $view;
    net = $net;
    schedulersFacade = $schedulersFacade;
  }

  @Override public void onCreate() {
    Timber.d("onCreate");
    compositeDisposable.add(net.getResult()
        .map(new Function<SearchResult, List<SearchResultItem>>() {
          @Override public List<SearchResultItem> apply(SearchResult searchResult)
              throws Exception {
            return searchResult.getItems();
          }
        })
        .subscribeOn(schedulersFacade.io())
        .observeOn(schedulersFacade.ui())
        .subscribe(
            new Consumer<List<SearchResultItem>>() {
              @Override public void accept(List<SearchResultItem> items) throws Exception {
                Timber.d("items = " + items.toString());
              }
            }, new Consumer<Throwable>() {
              @Override public void accept(Throwable throwable) throws Exception {
                Timber.d(throwable);
              }
            }));
  }
}
