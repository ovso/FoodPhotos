package io.github.ovso.foodphotos;

import android.app.Application;
import io.github.ovso.foodphotos.utils.AppInitUtils;
import io.github.ovso.foodphotos.utils.SystemUtils;
import lombok.Getter;

public class App extends Application {
  @Getter private boolean debug;

  @Override public void onCreate() {
    super.onCreate();
    debug = SystemUtils.isDebuggable(this);
    AppInitUtils.timber(this, debug);
  }
}
