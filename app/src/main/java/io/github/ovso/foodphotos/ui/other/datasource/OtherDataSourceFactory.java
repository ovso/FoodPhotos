package io.github.ovso.foodphotos.ui.other.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.support.annotation.NonNull;
import io.github.ovso.foodphotos.data.network.model.Photo;
import io.reactivex.disposables.CompositeDisposable;

public class OtherDataSourceFactory extends DataSource.Factory<Long, Photo> {

  private CompositeDisposable compositeDisposable;

  private MutableLiveData<OtherDataSource> otherDataSourceLiveData = new MutableLiveData<>();

  public OtherDataSourceFactory(CompositeDisposable compositeDisposable) {
    this.compositeDisposable = compositeDisposable;
  }

  @Override
  public DataSource<Long, Photo> create() {
    OtherDataSource photosDataSource = new OtherDataSource(compositeDisposable);
    otherDataSourceLiveData.postValue(photosDataSource);
    return photosDataSource;
  }

  @NonNull
  public MutableLiveData<OtherDataSource> getOtherDataSourceLiveData() {
    return otherDataSourceLiveData;
  }
}
