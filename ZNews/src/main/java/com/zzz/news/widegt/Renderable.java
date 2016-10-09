package com.zzz.news.widegt;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * @创建者 zlf
 * @创建时间 2016/10/9 9:20
 */

public class Renderable {

    public Bitmap bitmap;
    public float  x;//bitmap放置的x坐标
    public float  y;//bitmap放置的y坐标

    public Renderable(Bitmap bitmap, float x, float y) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
    }

    public void update(float deltaTime) {

    }

    public void draw(Canvas canvas) {
        canvas.save();
        canvas.drawBitmap(bitmap, x, y, null);
        canvas.restore();
    }
}
