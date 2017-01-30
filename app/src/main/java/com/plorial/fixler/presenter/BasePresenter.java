package com.plorial.fixler.presenter;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.plorial.fixler.model.Model;
import com.plorial.fixler.model.ModelImpl;

import java.net.URL;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by plorial on 1/28/17.
 */

public class BasePresenter implements Presenter {

    protected Model mModel;

    protected BasePresenter(){
        mModel = new ModelImpl();
    }

    CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    protected void addSubscription(Subscription subscription){
        mCompositeSubscription.add(subscription);
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

    @Override
    public void onStop() {
        mCompositeSubscription.unsubscribe();
    }
}
