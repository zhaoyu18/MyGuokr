package com.example.wangzhaoyu.myguokr.core;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wangzhaoyu.myguokr.ui.widget.GlideCircleTransform;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * @author wangzhaoyu
 */
public class ImageUtils {
    private static final String TAG = ImageUtils.class.getSimpleName();

    private static class InstanceHolder {
        public static ImageUtils holder = new ImageUtils();
    }

    public static ImageUtils getInstance() {
        return InstanceHolder.holder;
    }

    /**
     * 头像圆形图片option
     *
     * @param size
     * @return
     */
    public static DisplayImageOptions getAvatarDisplayOptions(int size) {
        return new DisplayImageOptions.Builder()
//                .showImageOnFail(R.mipmap.ic_launcher)
//                .showImageForEmptyUri(R.mipmap.ic_launcher)
//                .showImageOnLoading(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .displayer(new RoundedBitmapDisplayer(size)).build();
    }

    public static Bitmap getCircularBitmapImage(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;
        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            source.recycle();
        }
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);
        squaredBitmap.recycle();
        return bitmap;
    }

    @BindingAdapter({"bind:avatarUrl", "bind:context"})
    public static void loadAvatar(ImageView view, String url, Context context) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .transform(new GlideCircleTransform(context))
                .into(view);
    }

    @BindingAdapter({"bind:imageUrl", "bind:context"})
    public static void loadImage(ImageView view, String url, Context context) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .animate(android.R.anim.fade_in)
                .into(view);
    }
}
