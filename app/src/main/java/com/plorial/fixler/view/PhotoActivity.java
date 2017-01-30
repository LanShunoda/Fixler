package com.plorial.fixler.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.plorial.fixler.R;
import com.plorial.fixler.presenter.PhotoPresenter;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by plorial on 1/30/17.
 */

public class PhotoActivity extends AppCompatActivity implements PhotoView {

    public static final String PHOTO_URL = "PHOTO_URL";

    private PhotoPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        mPresenter = new PhotoPresenter(this);
        ImageView imageView = (ImageView) findViewById(R.id.imageViewFull);
        String u = getIntent().getStringExtra(PHOTO_URL);
        try {
            URL url = new URL(u);
            mPresenter.loadBitmap(imageView, url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
