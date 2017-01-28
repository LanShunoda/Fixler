package com.plorial.fixler.view;

import com.plorial.fixler.model.pojo.Photo;

import java.util.List;

/**
 * Created by plorial on 1/28/17.
 */

public interface RecentPhotosView extends View {

    void addPhotos(List<Photo> photos);
}
