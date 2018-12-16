package io.github.ovso.foodphotos.ui.other.di;

import dagger.Binds;
import dagger.Module;
import io.github.ovso.foodphotos.ui.other.OtherActivity;
import io.github.ovso.foodphotos.ui.other.OtherPresenter;

@Module public abstract class OtherActivityViewModule {
  @Binds abstract OtherPresenter.View bindOtherView(OtherActivity act);
}
