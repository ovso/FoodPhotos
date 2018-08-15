package io.github.ovso.foodphotos.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import io.github.ovso.foodphotos.data.NetworkState;
import io.github.ovso.foodphotos.data.datasource.PhotosDataSource;
import io.github.ovso.foodphotos.data.datasource.PhotosDataSourceFactory;
import io.github.ovso.foodphotos.data.network.model.Photo;
import io.reactivex.disposables.CompositeDisposable;

public class PhotosViewModel extends ViewModel {
  LiveData<PagedList<Photo>> photoList;

  private CompositeDisposable compositeDisposable = new CompositeDisposable();

  private static final int pageSize = 15;

  private PhotosDataSourceFactory photosDataSourceFactory;

  public PhotosViewModel() {
    photosDataSourceFactory = new PhotosDataSourceFactory(compositeDisposable);
    PagedList.Config config = new PagedList.Config.Builder()
        .setPageSize(pageSize)
        .setInitialLoadSizeHint(pageSize * 2)
        .setEnablePlaceholders(false)
        .build();

    photoList = new LivePagedListBuilder<>(photosDataSourceFactory, config).build();
  }

  public void retry() {
    photosDataSourceFactory.getPhotosDataSourceLiveData().getValue().retry();
  }

  public void refresh() {
    photosDataSourceFactory.getPhotosDataSourceLiveData().getValue().invalidate();
  }

  public LiveData<NetworkState> getNetworkState() {
    return Transformations.switchMap(photosDataSourceFactory.getPhotosDataSourceLiveData(),
        PhotosDataSource::getNetworkState);
  }

  public LiveData<NetworkState> getRefreshState() {
    return Transformations.switchMap(photosDataSourceFactory.getPhotosDataSourceLiveData(),
        PhotosDataSource::getInitialLoad);
  }

  @Override
  protected void onCleared() {
    super.onCleared();
    compositeDisposable.dispose();
  }
}
