package io.github.ovso.foodphotos.ui.main.di;

import dagger.Binds;
import dagger.Module;
import io.github.ovso.foodphotos.ui.main.MainActivity;
import io.github.ovso.foodphotos.ui.main.MainPresenter;

@Module
public abstract class MainActivityViewModule {
  @Binds abstract MainPresenter.View bindWebView(MainActivity activity);
}
