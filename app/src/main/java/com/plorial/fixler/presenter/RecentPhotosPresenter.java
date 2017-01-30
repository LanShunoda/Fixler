package com.plorial.fixler.presenter;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.plorial.fixler.model.Model;
import com.plorial.fixler.model.ModelImpl;
import com.plorial.fixler.model.pojo.Photos;
import com.plorial.fixler.view.RecentPhotosView;

import java.net.URL;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by plorial on 1/28/17.
 */

public class RecentPhotosPresenter extends BasePresenter {

    private static final String TAG = RecentPhotosPresenter.class.getSimpleName();

    private RecentPhotosView mView;
    private Model mModel;
    private int mCurrentPage = 0;

    public RecentPhotosPresenter(RecentPhotosView mView) {
        this.mView = mView;
        mModel = new ModelImpl();
    }

    public void loadPhotos(){
        Subscription subscription = mModel.getRecentPhotos(6, mCurrentPage++).subscribe(new Action1<Photos>() {
            @Override
            public void call(Photos photos) {
                mCurrentPage = photos.getPage();
                mView.addPhotos(photos.getPhoto());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                showError(throwable);
            }
        });
        addSubscription(subscription);
    }

    public void loadBitmap(final ImageView imageView, URL url){
        Subscription subscription = mModel.getPhotoFromUrl(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                });
        addSubscription(subscription);
    }

    private void showError(Throwable throwable){
        Log.e(TAG, throwable.getMessage());
        throwable.printStackTrace();
        mView.showError(throwable.getLocalizedMessage());
    }
}
