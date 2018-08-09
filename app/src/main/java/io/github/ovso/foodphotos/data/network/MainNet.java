package io.github.ovso.foodphotos.data.network;

import io.github.ovso.foodphotos.Security;
import io.github.ovso.foodphotos.data.network.model.SearchResult;
import io.reactivex.Observable;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Headers;

public class MainNet extends BaseNetwork<MainApi> {
  @Override protected Class<MainApi> getApiClass() {
    return MainApi.class;
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

  public Observable<SearchResult> getResult() {
    Map<String, String> queryMap = new HashMap<>();
    queryMap.put("display", String.valueOf(20));
    queryMap.put("start", String.valueOf(1));
    queryMap.put("query", "햄버거");

    return getApi().getSearchResult(queryMap);
  }
}
