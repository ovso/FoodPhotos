package io.github.ovso.foodphotos.data.network;

import io.github.ovso.foodphotos.data.network.model.SearchResult;
import io.reactivex.Observable;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface MainApi {
  @GET("/v1/search/image") Observable<SearchResult> getSearchResult(
      @QueryMap Map<String, String> queryMap);
}
