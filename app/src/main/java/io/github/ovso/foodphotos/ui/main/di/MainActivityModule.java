package io.github.ovso.foodphotos.ui.main.di;

import android.arch.lifecycle.ViewModelProviders;
import dagger.Module;
import dagger.Provides;
import io.github.ovso.foodphotos.data.network.MainRequest;
import io.github.ovso.foodphotos.ui.main.MainActivity;
import io.github.ovso.foodphotos.ui.main.PhotosViewModel;
import io.github.ovso.foodphotos.ui.main.adapter.MainAdapter;
import javax.inject.Singleton;

@Module public class MainActivityModule {
  /*
  @Singleton @Provides
  public MainPresenter provideMainPresenter(MainPresenter.View view, MainRequest net,
      SchedulersFacade schedulersFacade, AdapterDataModel<Photo> adapterDataModel) {
    return new MainPresenterImpl(view, net, schedulersFacade, adapterDataModel);
  }
  */

  @Singleton @Provides MainRequest provideMainNet() {
    return new MainRequest();
  }

  @Singleton @Provides PhotosViewModel providePhotosViewModel(MainActivity activity) {
    return ViewModelProviders.of(activity).get(PhotosViewModel.class);
  }

  @Singleton @Provides MainAdapter provideMainAdapter2(MainActivity activity) {
    return new MainAdapter(activity);
  }
}
