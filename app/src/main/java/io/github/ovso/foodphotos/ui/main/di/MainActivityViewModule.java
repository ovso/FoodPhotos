package io.github.ovso.foodphotos.ui.main.di;

import dagger.Binds;
import dagger.Module;
import io.github.ovso.foodphotos.data.network.model.SearchResultItem;
import io.github.ovso.foodphotos.ui.base.adapter.AdapterDataModel;
import io.github.ovso.foodphotos.ui.base.adapter.AdapterView;
import io.github.ovso.foodphotos.ui.main.MainActivity;
import io.github.ovso.foodphotos.ui.main.MainAdapter;
import io.github.ovso.foodphotos.ui.main.MainPresenter;

@Module
public abstract class MainActivityViewModule {
  @Binds abstract MainPresenter.View bindWebView(MainActivity activity);

  @Binds abstract AdapterView bindAdapterView(MainAdapter adapter);

  @Binds abstract AdapterDataModel<SearchResultItem> bindAdapterDataModel(MainAdapter adapter);
}

