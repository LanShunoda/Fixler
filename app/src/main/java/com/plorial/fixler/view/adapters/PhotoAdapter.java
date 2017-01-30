package com.plorial.fixler.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.plorial.fixler.R;
import com.plorial.fixler.model.pojo.Photo;
import com.plorial.fixler.presenter.RecentPhotosPresenter;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by plorial on 1/28/17.
 */

public class PhotoAdapter extends ArrayAdapter<Photo>{

    private Context context;
    private int resource;
    private RecentPhotosPresenter mPresenter;

    public PhotoAdapter(Context context, int resource, RecentPhotosPresenter presenter) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.mPresenter = presenter;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, null);
        }

        Photo photo = super.getItem(position);
        if(photo != null){
            final ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            DisplayMetrics metrics = new DisplayMetrics();
            ((AppCompatActivity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);

            try {
                URL photoUrl = new URL(photo.getUrlQ());

                switch(metrics.densityDpi){
                    case DisplayMetrics.DENSITY_LOW:
                        photoUrl =  new URL(photo.getUrlS());
                        break;
                    case DisplayMetrics.DENSITY_MEDIUM:
                        photoUrl = new URL(photo.getUrlM());
                        break;
                    case DisplayMetrics.DENSITY_HIGH:
                        photoUrl = new URL(photo.getUrlN());
                        break;
                    case DisplayMetrics.DENSITY_XHIGH:
                        photoUrl = new URL(photo.getUrlZ());
                        break;
                    case DisplayMetrics.DENSITY_XXHIGH:
                        photoUrl = new URL(photo.getUrlC());
                        break;
                    case DisplayMetrics.DENSITY_XXXHIGH:
                        photoUrl = new URL(photo.getUrlL());
                        break;
                }
                mPresenter.loadBitmap(imageView, photoUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return view;
    }
}
