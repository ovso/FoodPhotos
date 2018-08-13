package io.github.ovso.foodphotos.ui.main;

public interface MainPresenter {

  void onCreate();

  interface View {

    void refresh();

    void setupRecyclerView();
  }
}
