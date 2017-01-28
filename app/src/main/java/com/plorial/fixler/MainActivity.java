package com.plorial.fixler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.plorial.fixler.model.Model;
import com.plorial.fixler.model.ModelImpl;
import com.plorial.fixler.model.pojo.Photos;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Model model = new ModelImpl();
       Observable<Photos> photosObservable = model.getRecentPhotos(10, 1);
        photosObservable.subscribe(new Action1<Photos>() {
            @Override
            public void call(Photos photos) {
                System.out.println(photos.getPhoto().size());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
                System.out.println(((HttpException)throwable).response().raw());
            }
        });
    }
}
