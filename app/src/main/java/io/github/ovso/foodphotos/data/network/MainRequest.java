package io.github.ovso.foodphotos.data.network;

import io.github.ovso.foodphotos.Security;
import io.github.ovso.foodphotos.data.network.model.Photos;
import io.reactivex.Observable;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Headers;

public class MainRequest extends BaseNetwork<MainService> {
  @Override protected Class<MainService> getApiClass() {
    return MainService.class;
  }

  @Override protected Headers createHeaders() {
    Security.Api.Header client = Security.Api.Header.CLIENT;
    return new Headers.Builder().add(client.getIdName(), client.getId())
        .add(client.getSecretName(), client.getSecret())
        .build();
  }

  @Override protected String getBaseUrl() {
    return Security.Api.URL.getUrl();
  }

  public Observable<Photos> getPhotos(int page) {
    Map<String, Object> queryMap = new HashMap<>();
    queryMap.put("display", String.valueOf(20));
    queryMap.put("start", page);
    queryMap.put("query", "햄버거");

    return getApi().getSearchResult(queryMap);
  }
}
