package com.plorial.fixler.model;

import android.graphics.Bitmap;
import android.net.Uri;

import com.plorial.fixler.model.pojo.Photos;

import java.net.URL;

import rx.Observable;

/**
 * Created by plorial on 1/28/17.
 */

public interface Model {

    Observable<Photos> getRecentPhotos(int perPage, int page);
    Observable<Bitmap> getPhotoFromUrl(URL url);
}
