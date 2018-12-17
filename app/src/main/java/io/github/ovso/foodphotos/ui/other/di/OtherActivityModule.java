package io.github.ovso.foodphotos.ui.other.di;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import dagger.Module;
import dagger.Provides;
import io.github.ovso.foodphotos.data.network.model.Photo;
import io.github.ovso.foodphotos.ui.other.OtherActivity;
import io.github.ovso.foodphotos.ui.other.OtherPresenter;
import io.github.ovso.foodphotos.ui.other.OtherPresenterImpl;
import io.github.ovso.foodphotos.ui.other.adapter.OtherAdapter;
import io.github.ovso.foodphotos.ui.other.datasource.OtherDataSourceFactory;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Singleton;

@Module public class OtherActivityModule {

  @Singleton @Provides OtherPresenter provideOtherPresenter(OtherPresenter.View view) {
    OtherPresenter presenter = new OtherPresenterImpl(view);
    return presenter;
  }

  @Singleton @Provides OtherAdapter provideOtherAdapter() {
    return new OtherAdapter();
  }

  @Singleton @Provides PagedList.Config providePageListConfig() {
    return new PagedList.Config.Builder()
        .setPageSize(20)
        .setInitialLoadSizeHint(20 + 1)
        .setEnablePlaceholders(false)
        .build();
  }

  @Singleton @Provides OtherDataSourceFactory provideOtherDataSourceFactory(OtherActivity act) {
    OtherDataSourceFactory factory =
        new OtherDataSourceFactory(new CompositeDisposable());
    act.getLifecycle().addObserver(factory);
    return factory;
  }

  @Singleton @Provides LiveData<PagedList<Photo>> providePagedList(OtherDataSourceFactory factory,
      PagedList.Config config) {
    return new LivePagedListBuilder<>(factory, config).build();
  }
}