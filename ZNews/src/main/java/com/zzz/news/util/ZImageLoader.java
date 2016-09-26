package com.zzz.news.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.io.File;

import retrofit2.http.Url;

/**
 * @创建者 zlf
 * @创建时间 2016/9/1 14:29
 */
public class ZImageLoader {

    public static void setAvatar(final Context context, Url url, ImageView imageView) {
        Glide.with(context).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                view.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    public static void setAvatar(final Context context, File url, ImageView imageView) {
        Glide.with(context).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                view.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    public static void setAvatar(final Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                view.setImageDrawable(circularBitmapDrawable);
            }
        });
    }


    public static void setAvatar(final Context context, Integer url, ImageView imageView) {
        Glide.with(context).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                view.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    public static void setImgp(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }

    public static void setImg(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).centerCrop().into(imageView);
    }


    public static void setImg(Context context, File url, ImageView imageView) {
        Glide.with(context).load(url).centerCrop().into(imageView);
    }


    public static void setImg(Context context, Url url, ImageView imageView) {
        Glide.with(context).load(url).centerCrop().into(imageView);
    }

    public static void setImg(Context context, Integer url, ImageView imageView) {
        Glide.with(context).load(url).centerCrop().into(imageView);
    }

    public static void clear(View view) {
        Glide.clear(view);
    }

}
