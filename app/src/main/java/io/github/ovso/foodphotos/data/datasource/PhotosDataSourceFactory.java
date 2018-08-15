package io.github.ovso.foodphotos.data.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.support.annotation.NonNull;
import io.github.ovso.foodphotos.data.network.model.Photo;
import io.reactivex.disposables.CompositeDisposable;

public class PhotosDataSourceFactory extends DataSource.Factory<Long, Photo> {

  private CompositeDisposable compositeDisposable;

  private MutableLiveData<PhotosDataSource> photosDataSourceLiveData = new MutableLiveData<>();

  public PhotosDataSourceFactory(CompositeDisposable compositeDisposable) {
    this.compositeDisposable = compositeDisposable;
  }

  @Override
  public DataSource<Long, Photo> create() {
    PhotosDataSource photosDataSource = new PhotosDataSource(compositeDisposable);
    photosDataSourceLiveData.postValue(photosDataSource);
    return photosDataSource;
  }

  @NonNull
  public MutableLiveData<PhotosDataSource> getPhotosDataSourceLiveData() {
    return photosDataSourceLiveData;
  }
}
