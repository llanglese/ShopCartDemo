package com.project.shopcart.uitls.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;

/**
 * Created by Administrator on 2017/2/28.
 * 圆角处理
 */

public class RoundedCornersTransformation implements Transformation {
    private BitmapPool mBitmapPool;

    private int radius;
    private int margin;

    public RoundedCornersTransformation(Context context, int radius, int margin) {
        this(Glide.get(context).getBitmapPool(), radius, margin);
    }

    public RoundedCornersTransformation(BitmapPool pool, int radius, int margin) {
        mBitmapPool = pool;
        this.radius = radius;
        this.margin = margin;
    }

    @Override
    public Resource transform(Resource resource, int outWidth, int outHeight) {
        Bitmap source = (Bitmap) resource.get();

        int width = source.getWidth();
        int height = source.getHeight();

        Bitmap bitmap = mBitmapPool.get(width, height, Bitmap.Config.ARGB_8888);
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect(new RectF(margin, margin, width - margin, height - margin), radius, radius,
                paint);

        return BitmapResource.obtain(bitmap, mBitmapPool);
    }

    @Override
    public String getId() {
        return "RoundedTransformation(radius=" + radius + ", margin=" + margin + ")";
    }
}
