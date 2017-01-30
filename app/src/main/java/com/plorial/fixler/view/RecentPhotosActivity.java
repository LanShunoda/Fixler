package com.plorial.fixler.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.Toast;

import com.plorial.fixler.R;
import com.plorial.fixler.model.pojo.Photo;
import com.plorial.fixler.presenter.RecentPhotosPresenter;
import com.plorial.fixler.view.adapters.PhotoAdapter;

import java.util.List;

public class RecentPhotosActivity extends AppCompatActivity implements RecentPhotosView, AbsListView.OnScrollListener {

    public static final String TAG = RecentPhotosActivity.class.getSimpleName();

    private PhotoAdapter mAdapter;
    private RecentPhotosPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_photos);
        GridView gridView = (GridView) findViewById(R.id.gridView);
        mPresenter = new RecentPhotosPresenter(this);
        mAdapter = new PhotoAdapter(this, R.layout.photo_item, mPresenter);
        gridView.setAdapter(mAdapter);
        mPresenter.loadPhotos();
        gridView.setOnScrollListener(this);
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
    public void addPhotos(List<Photo> photos) {
        mAdapter.addAll(photos);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(totalItemCount - (firstVisibleItem + visibleItemCount) < 4){
            mPresenter.loadPhotos();
        }
    }
}
