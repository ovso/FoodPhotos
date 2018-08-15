package io.github.ovso.foodphotos.data.network.model;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString public class Photo {
  private String title;
  private String link;
  private String thumbnail;
  private String sizeheight;
  private String sizewidth;
}
