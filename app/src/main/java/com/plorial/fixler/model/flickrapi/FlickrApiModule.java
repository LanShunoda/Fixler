package com.plorial.fixler.model.flickrapi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by plorial on 1/28/17.
 */

public class FlickrApiModule {

    private final static String BASE_URL = "https://api.flickr.com/services/rest/";
    public final static String API_KEY = "e88cd2cfd5b2530014e767a79cb0dd6f";

    public static FlickrApiInterface getApiInterface(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(FlickrApiInterface.class);
    }
}
