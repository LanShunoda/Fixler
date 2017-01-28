package com.plorial.fixler.presenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by plorial on 1/28/17.
 */

public class BasePresenter implements Presenter {

    protected BasePresenter(){}

    CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    protected void addSubscription(Subscription subscription){
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        mCompositeSubscription.unsubscribe();
    }
}
