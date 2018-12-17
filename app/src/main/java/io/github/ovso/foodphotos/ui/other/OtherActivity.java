package io.github.ovso.foodphotos.ui.other;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.paging.PagedList;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import dagger.Provides;
import io.github.ovso.foodphotos.R;
import io.github.ovso.foodphotos.data.network.model.Photo;
import io.github.ovso.foodphotos.ui.base.BaseActivity;
import io.github.ovso.foodphotos.ui.main.adapter.NetworkState;
import io.github.ovso.foodphotos.ui.other.adapter.OtherAdapter;
import io.github.ovso.foodphotos.ui.other.datasource.OtherDataSource;
import io.github.ovso.foodphotos.ui.other.datasource.OtherDataSourceFactory;
import javax.inject.Inject;
import javax.inject.Singleton;

public class OtherActivity extends BaseActivity implements OtherPresenter.View {
  @BindView(R.id.recyclerview_other) RecyclerView recyclerView;
  @Inject OtherPresenter presenter;
  @Inject OtherAdapter adapter;
  @Inject PagedList.Config config;
  @Inject LiveData<PagedList<Photo>> pagedList;
  @Inject OtherDataSourceFactory dataSourceFactory;

  @Override protected int getLayoutResId() {
    return R.layout.activity_other;
  }

  @Override public void setupRecyclerView() {
    recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    recyclerView.setAdapter(adapter);
  }

  public LiveData<NetworkState> getNetworkState() {
    return Transformations.switchMap(dataSourceFactory.getOtherDataSourceLiveData(),
        OtherDataSource::getNetworkState);
  }

  public LiveData<NetworkState> getRefreshState() {
    return Transformations.switchMap(dataSourceFactory.getOtherDataSourceLiveData(),
        OtherDataSource::getInitialLoad);
  }
}