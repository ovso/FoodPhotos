package io.github.ovso.foodphotos.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import io.github.ovso.foodphotos.ui.main.MainActivity;
import io.github.ovso.foodphotos.ui.main.di.MainActivityModule;
import io.github.ovso.foodphotos.ui.main.di.MainActivityViewModule;
import io.github.ovso.foodphotos.ui.other.OtherActivity;
import io.github.ovso.foodphotos.ui.other.di.OtherActivityModule;
import io.github.ovso.foodphotos.ui.other.di.OtherActivityViewModule;
import javax.inject.Singleton;

@Module(includes = { AndroidSupportInjectionModule.class })
public abstract class ActivityBuilder {
  @Singleton
  @ContributesAndroidInjector(modules = { MainActivityModule.class, MainActivityViewModule.class })
  abstract MainActivity bindMainActivity();

  @Singleton
  @ContributesAndroidInjector(modules = { OtherActivityModule.class, OtherActivityViewModule.class })
  abstract OtherActivity bindOtherActivity();
}