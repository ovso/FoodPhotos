package io.github.ovso.foodphotos.data.network.model;

import java.util.List;
import lombok.Getter;

@Getter public class Photos {
  private String lastBuildDate;
  private int total;
  private int start;
  private int display;
  private List<Photo> items;
}
