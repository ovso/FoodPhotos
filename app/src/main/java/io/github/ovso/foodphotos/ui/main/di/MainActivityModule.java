package io.github.ovso.foodphotos.ui.main.di;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.foodphotos.data.network.MainRequest;
import io.github.ovso.foodphotos.data.network.model.Photo;
import io.github.ovso.foodphotos.ui.base.adapter.AdapterDataModel;
import io.github.ovso.foodphotos.ui.main.MainActivity;
import io.github.ovso.foodphotos.ui.main.adapter.MainAdapter;
import io.github.ovso.foodphotos.ui.main.MainPresenter;
import io.github.ovso.foodphotos.ui.main.MainPresenterImpl;
import io.github.ovso.foodphotos.ui.main.adapter.MainAdapter2;
import io.github.ovso.foodphotos.utils.SchedulersFacade;
import javax.inject.Singleton;

@Module public class MainActivityModule {
  @Singleton @Provides
  public MainPresenter provideMainPresenter(MainPresenter.View view, MainRequest net,
      SchedulersFacade schedulersFacade, AdapterDataModel<Photo> adapterDataModel) {
    return new MainPresenterImpl(view, net, schedulersFacade, adapterDataModel);
  }

  @Singleton @Provides MainRequest provideMainNet() {
    return new MainRequest();
  }

  @Singleton @Provides MainAdapter provideMainAdapter() {
    return new MainAdapter();
  }

  @Singleton @Provides MainAdapter2 provideMainAdapter2(MainActivity activity) {
    return new MainAdapter2(activity);
  }
}
