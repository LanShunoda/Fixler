package com.plorial.fixler.model.flickrapi;

import android.support.annotation.Nullable;

import com.plorial.fixler.model.pojo.FlickrResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by plorial on 1/28/17.
 */

public interface FlickrApiInterface {

    @GET("?method=flickr.photos.getRecent&")
    Observable<FlickrResponse> getRecentPhotos(@Query("api_key") String apiKey,
                                               @Nullable @Query("format") String format,
                                               @Nullable @Query("nojsoncallback") String one,
                                               @Nullable @Query("per_page") String perPage,
                                               @Nullable @Query("page") String page,
                                               @Nullable @Query("extras") String extras);
}
