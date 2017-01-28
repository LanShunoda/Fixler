package com.plorial.fixler.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.plorial.fixler.model.flickrapi.FlickrApiInterface;
import com.plorial.fixler.model.flickrapi.FlickrApiModule;
import com.plorial.fixler.model.pojo.FlickrResponse;
import com.plorial.fixler.model.pojo.Photos;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import rx.Observable;
import rx.Subscriber;
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
        return mApiInterface.getRecentPhotos(FlickrApiModule.API_KEY,
                "json", String.valueOf(1), String.valueOf(perPage), String.valueOf(page),
                "url_sq,url_t,url_s,url_q,url_m,url_n,url_z,url_c,url_l,url_o")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<FlickrResponse, Observable<Photos>>() {
                    @Override
                    public Observable<Photos> call(FlickrResponse flickrResponse) {
                        return Observable.just(flickrResponse.getPhotos());
                    }
                });
    }

    @Override
    public Observable<Bitmap> getPhotoFromUrl(final URL url) {
       return Observable.create(new Observable.OnSubscribe<Bitmap>() {
           @Override
           public void call(Subscriber<? super Bitmap> subscriber) {
               Bitmap bitmap = null;
               InputStream inputStream = null;
               try {
                   inputStream = url.openStream();
                   bitmap = BitmapFactory.decodeStream(inputStream);
                   subscriber.onNext(bitmap);
                   subscriber.onCompleted();
               } catch (IOException e) {
                   e.printStackTrace();
                   subscriber.onError(e);
               } finally {
                   if(inputStream != null){
                       try {
                           inputStream.close();
                       } catch (IOException e) {
                           e.printStackTrace();
                           subscriber.onError(e);
                       }
                   }
               }
           }
       });
    }
}
