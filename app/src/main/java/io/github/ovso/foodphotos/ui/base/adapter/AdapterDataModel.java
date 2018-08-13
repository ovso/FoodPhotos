package io.github.ovso.foodphotos.ui.base.adapter;

import java.util.List;

public interface AdapterDataModel<T> {
  void addItems(List<T> $items);

  void setItems(List<T> $items);

  void addItem(T $item);

  void setItem(T $item);

  int getSize();

  void clear();
}
