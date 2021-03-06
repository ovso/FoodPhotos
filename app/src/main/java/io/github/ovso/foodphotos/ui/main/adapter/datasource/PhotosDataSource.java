package io.github.ovso.foodphotos.ui.main.adapter.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;
import io.github.ovso.foodphotos.data.network.MainRequest;
import io.github.ovso.foodphotos.data.network.model.Photo;
import io.github.ovso.foodphotos.data.network.model.Photos;
import io.github.ovso.foodphotos.ui.main.adapter.NetworkState;
import io.github.ovso.foodphotos.utils.SchedulersFacade;
import io.reactivex.Completable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import timber.log.Timber;

/**
 * Created by Ahmed Abd-Elmeged on 2/13/2018.
 */
public class PhotosDataSource extends ItemKeyedDataSource<Long, Photo> {

  private MainRequest mainRequest;

  private CompositeDisposable compositeDisposable;

  private SchedulersFacade schedulersFacade;

  private MutableLiveData<NetworkState> networkState = new MutableLiveData<>();

  private MutableLiveData<NetworkState> initialLoad = new MutableLiveData<>();

  /**
   * Keep Completable reference for the retry event
   */
  private Completable retryCompletable;
  private AtomicInteger page = new AtomicInteger(1);

  PhotosDataSource(CompositeDisposable compositeDisposable) {
    this.mainRequest = new MainRequest();
    this.compositeDisposable = compositeDisposable;
    schedulersFacade = new SchedulersFacade();
  }

  public void retry() {
    if (retryCompletable != null) {
      compositeDisposable.add(retryCompletable
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(() -> {
          }, throwable -> Timber.e(throwable.getMessage())));
    }
  }

  @Override
  public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull
      LoadInitialCallback<Photo> callback) {
    Timber.d("loadInitial");
    // update network states.
    // we also provide an initial load state to the listeners so that the UI can know when the
    // very first list is loaded.
    networkState.postValue(NetworkState.LOADING);
    initialLoad.postValue(NetworkState.LOADING);

    mainRequest.getPhotos(page.get())
        .map(
            Photos::getItems
        )
        .subscribeOn(schedulersFacade.io())
        .observeOn(schedulersFacade.ui())
        .subscribe(new Observer<List<Photo>>() {
          @Override public void onSubscribe(Disposable d) {
            compositeDisposable.add(d);
          }

          @Override public void onNext(List<Photo> photos) {
            PhotosDataSource.this.setRetry(
                null);
            networkState.postValue(
                NetworkState.LOADED);
            initialLoad.postValue(
                NetworkState.LOADED);
            callback.onResult(photos);
          }

          @Override public void onError(Throwable e) {
            setRetry(() -> loadInitial(params, callback));
            NetworkState error = NetworkState.error(e.getMessage());
            // publish the error
            networkState.postValue(error);
            initialLoad.postValue(error);
          }

          @Override public void onComplete() {

          }
        });
  }

  @Override
  public void loadAfter(@NonNull LoadParams<Long> params,
      @NonNull LoadCallback<Photo> callback) {
    Timber.d("loadAfter");

    // set network value to loading.
    networkState.postValue(NetworkState.LOADING);

    //get the users from the api after id
    compositeDisposable.add(
        mainRequest.getPhotos(page.incrementAndGet())
            .map(Photos::getItems)
            .subscribeOn(schedulersFacade.io())
            .observeOn(schedulersFacade.ui())
            .subscribe(photos -> {
                  // clear retry since last request succeeded
                  setRetry(null);
                  networkState.postValue(NetworkState.LOADED);
                  callback.onResult(photos);
                },
                throwable -> {
                  // keep a Completable for future retry
                  setRetry(() -> loadAfter(params, callback));
                  // publish the error
                  networkState.postValue(NetworkState.error(throwable.getMessage()));
                }));
  }

  @Override
  public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Photo> callback) {
    // ignored, since we only ever append to our initial load
  }

  /**
   * The id field is a unique identifier for users.
   */
  @NonNull
  @Override
  public Long getKey(@NonNull Photo item) {
    return 0L;
  }

  @NonNull
  public MutableLiveData<NetworkState> getNetworkState() {
    return networkState;
  }

  @NonNull
  public MutableLiveData<NetworkState> getInitialLoad() {
    return initialLoad;
  }

  private void setRetry(final Action action) {
    if (action == null) {
      this.retryCompletable = null;
    } else {
      this.retryCompletable = Completable.fromAction(action);
    }
  }
}
