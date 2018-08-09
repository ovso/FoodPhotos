package io.github.ovso.foodphotos.utils;

import android.content.Context;
import timber.log.Timber;

public final class AppInitUtils {
  private AppInitUtils() {
  }

  public static void timber(Context context, boolean debug) {
    if (debug) {
      Timber.plant(new Timber.DebugTree());
    }
  }
}
