package io.github.ovso.foodphotos.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import butterknife.BindView;
import io.github.ovso.foodphotos.R;
import io.github.ovso.foodphotos.ui.main.adapter.NetworkState;
import io.github.ovso.foodphotos.ui.main.adapter.Status;
import io.github.ovso.foodphotos.ui.base.BaseActivity;
import io.github.ovso.foodphotos.ui.main.adapter.MainAdapter;
import io.github.ovso.foodphotos.ui.main.adapter.RetryCallback;
import javax.inject.Inject;

public class MainActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener, RetryCallback {
  @BindView(R.id.recycler_view) RecyclerView recyclerView;
  @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipe;

  @Inject MainAdapter adapter2;
  @Inject PhotosViewModel photosViewModel;

  @Override protected int getLayoutResId() {
    return R.layout.activity_main;
  }

  @Override protected void onCreated(@Nullable Bundle saveInstanceState) {

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    initAdapter();
    initSwipeToRefresh();
  }

  private void initSwipeToRefresh() {
    photosViewModel.getRefreshState().observe(this, networkState -> {
      if (networkState != null) {
        if (adapter2.getCurrentList() != null) {
          if (adapter2.getCurrentList().size() > 0) {
            swipe.setRefreshing(
                networkState.getStatus() == NetworkState.LOADING.getStatus());
          } else {
            setInitialLoadingState(networkState);
          }
        } else {
          setInitialLoadingState(networkState);
        }
      }
    });
    swipe.setOnRefreshListener(() -> photosViewModel.refresh());
  }

  private void setInitialLoadingState(NetworkState networkState) {
    //error message
    //errorMessageTextView.setVisibility(networkState.getMessage() != null ? View.VISIBLE : View.GONE);
    if (networkState.getMessage() != null) {
      //errorMessageTextView.setText(networkState.getMessage());
    }

    //loading and retry
    //retryLoadingButton.setVisibility(networkState.getStatus() == Status.FAILED ? View.VISIBLE : View.GONE);
    //loadingProgressBar.setVisibility(networkState.getStatus() == Status.RUNNING ? View.VISIBLE : View.GONE);

    swipe.setEnabled(networkState.getStatus() == Status.SUCCESS);
  }

  private void initAdapter() {
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
        LinearLayoutManager.VERTICAL, false);
    recyclerView.setLayoutManager(linearLayoutManager);
    recyclerView.setAdapter(adapter2);
    photosViewModel.photoList.observe(this, pagedList -> adapter2.submitList(pagedList));
    photosViewModel.getNetworkState().observe(this, adapter2::setNetworkState);
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
      // Handle the camera action
    } else if (id == R.id.nav_gallery) {

    } else if (id == R.id.nav_slideshow) {

    } else if (id == R.id.nav_manage) {

    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  @Override public void retry() {

  }
}
