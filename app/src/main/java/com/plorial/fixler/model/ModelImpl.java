package com.plorial.fixler.model;

import com.plorial.fixler.model.flickrapi.FlickrApiInterface;
import com.plorial.fixler.model.flickrapi.FlickrApiModule;
import com.plorial.fixler.model.pojo.FlickrResponse;
import com.plorial.fixler.model.pojo.Photos;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by plorial on 1/28/17.
 */

public class ModelImpl implements Model {

    private FlickrApiInterface mApiInterface = FlickrApiModule.getApiInterface();

    @Override
    public Observable<Photos> getRecentPhotos(int perPage, int page) {
        return mApiInterface.getRecentPhotos(FlickrApiModule.API_KEY, "json", String.valueOf(1), String.valueOf(perPage), String.valueOf(page))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<FlickrResponse, Observable<Photos>>() {
                    @Override
                    public Observable<Photos> call(FlickrResponse flickrResponse) {
                        return Observable.just(flickrResponse.getPhotos());
                    }
                });
    }
}
