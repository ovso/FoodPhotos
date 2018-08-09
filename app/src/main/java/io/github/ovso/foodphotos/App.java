package io.github.ovso.foodphotos;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import io.github.ovso.foodphotos.di.DaggerAppComponent;
import io.github.ovso.foodphotos.utils.AppInitUtils;
import io.github.ovso.foodphotos.utils.SystemUtils;
import lombok.Getter;

public class App extends DaggerApplication {
  @Getter private boolean debug;

  @Override public void onCreate() {
    super.onCreate();
    debug = SystemUtils.isDebuggable(this);
    AppInitUtils.timber(this, debug);
  }

  @Override protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    return DaggerAppComponent.builder().application(this).build();
  }
}
