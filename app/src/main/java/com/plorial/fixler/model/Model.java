package com.plorial.fixler.model;

import com.plorial.fixler.model.pojo.Photos;

import rx.Observable;

/**
 * Created by plorial on 1/28/17.
 */

public interface Model {

    Observable<Photos> getRecentPhotos(int perPage, int page);
}
