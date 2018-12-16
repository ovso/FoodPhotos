package io.github.ovso.foodphotos.ui.other.di;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.foodphotos.ui.other.OtherPresenter;
import io.github.ovso.foodphotos.ui.other.OtherPresenterImpl;
import io.github.ovso.foodphotos.ui.other.adapter.OtherAdapter;
import javax.inject.Singleton;

@Module public class OtherActivityModule {

  @Singleton @Provides OtherPresenter provideOtherPresenter(OtherPresenter.View view) {
    OtherPresenter presenter = new OtherPresenterImpl(view);
    return presenter;
  }

  @Singleton @Provides OtherAdapter provideOtherAdapter() {
    return new OtherAdapter();
  }
}
