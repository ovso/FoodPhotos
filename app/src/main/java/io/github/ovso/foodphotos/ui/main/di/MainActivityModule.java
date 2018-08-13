package io.github.ovso.foodphotos.ui.main.di;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.foodphotos.data.network.MainRequest;
import io.github.ovso.foodphotos.data.network.model.SearchResultItem;
import io.github.ovso.foodphotos.ui.base.adapter.AdapterDataModel;
import io.github.ovso.foodphotos.ui.main.MainAdapter;
import io.github.ovso.foodphotos.ui.main.MainPresenter;
import io.github.ovso.foodphotos.ui.main.MainPresenterImpl;
import io.github.ovso.foodphotos.utils.SchedulersFacade;
import javax.inject.Singleton;

@Module public class MainActivityModule {
  @Singleton @Provides
  public MainPresenter provideMainPresenter(MainPresenter.View view, MainRequest net,
      SchedulersFacade schedulersFacade, AdapterDataModel<SearchResultItem> adapterDataModel) {
    return new MainPresenterImpl(view, net, schedulersFacade, adapterDataModel);
  }

  @Singleton @Provides MainRequest provideMainNet() {
    return new MainRequest();
  }

  @Singleton @Provides MainAdapter provideMainAdapter() {
    return new MainAdapter();
  }

  //@Provides AdapterView provideAdapterView(MainAdapter adapter) {
  //  return adapter;
  //}
  //
  //@Provides AdapterDataModel provideAdapterDataModel(MainAdapter adapter) {
  //  return adapter;
  //}
}
