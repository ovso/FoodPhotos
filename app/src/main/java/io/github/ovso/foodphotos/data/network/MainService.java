package io.github.ovso.foodphotos.data.network;

import io.github.ovso.foodphotos.data.network.model.Photos;
import io.reactivex.Observable;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface MainService {
  @GET("/v1/search/image") Observable<Photos> getSearchResult(
      @QueryMap Map<String, Object> queryMap);
}
