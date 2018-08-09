package io.github.ovso.foodphotos.ui.main.di;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.foodphotos.data.network.MainNet;
import io.github.ovso.foodphotos.ui.main.MainPresenter;
import io.github.ovso.foodphotos.ui.main.MainPresenterImpl;
import io.github.ovso.foodphotos.utils.SchedulersFacade;
import javax.inject.Singleton;

@Module public class MainActivityModule {
  @Singleton @Provides
  public MainPresenter provideMainPresenter(MainPresenter.View view, MainNet net,
      SchedulersFacade schedulersFacade) {
    return new MainPresenterImpl(view, net, schedulersFacade);
  }

  @Singleton @Provides MainNet provideMainNet() {
    return new MainNet();
  }
}
