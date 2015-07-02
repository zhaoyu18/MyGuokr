package com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.header;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.util.PtrLocalDisplay;

/**
 * @author wangzhaoyu
 */
public class MoocGlassesDrawable extends Drawable implements Animatable {
    private final static String TAG = MoocGlassesDrawable.class.getSimpleName();
    private static final float GLASSES_INITIAL_SCALE = 1.00f;
    private static final float GLASSES_FINAL_SCALE = 1.00f;

    private static final float EYE_INITIAL_ROTATE_GROWTH = 1.2f;
    private static final float EYE_FINAL_ROTATE_GROWTH = 1.5f;
    private static final float EYE_ALPHA_START_PERCENT = 0.5f;

    private static final int ANIMATION_DURATION = 1000;
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    private static final float SCALE_START_PERCENT = 0.3f;

    private final Context mContext;
    private final View mParent;
    private final Matrix mMatrix;
    //向下拉动的最大距离
    private int mTotalDragDistance;
    private int mScreenWidth;

    //高度？
    private int mGlassesPosHeight;
    private float mGlassesInitialTopOffset;
    private float mGlassesFinalTopOffset;
    private Bitmap mGlasses;
    private Bitmap mEye;

    private int mTop;

    private Animation mAnimation;
    private float mRotate;
    private float mPercent;
    private boolean isRefreshing;

    private float mSunLeftOffset;
    private float mSunTopOffset;

    public MoocGlassesDrawable(Context context, View parent) {
        mContext = context;
        mParent = parent;
        mMatrix = new Matrix();

        initiateDimens();
        createBitmaps();
        setupAnimations();
    }

    @Override
    public void draw(Canvas canvas) {
        final int saveCount = canvas.save();
        //canvas 移动到中间位置
        canvas.translate(mScreenWidth / 2, (mTotalDragDistance - mTop) / 2);

        drawGlasses(canvas);

        canvas.restoreToCount(saveCount);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void start() {
        mAnimation.reset();
        isRefreshing = true;
        mParent.startAnimation(mAnimation);
    }

    @Override
    public void stop() {
        mParent.clearAnimation();
        isRefreshing = false;
        resetOriginals();
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    private void initiateDimens() {
        PtrLocalDisplay.init(mContext);
        mTotalDragDistance = PtrLocalDisplay.dp2px(55);
        mScreenWidth = mContext.getResources().getDisplayMetrics().widthPixels;

        //Glass init
        mGlassesPosHeight = mTotalDragDistance;
        Log.i(TAG, mGlassesPosHeight + "");
        mGlassesInitialTopOffset = mTotalDragDistance * .5f;
        Log.i(TAG, mGlassesInitialTopOffset + "");
        mGlassesFinalTopOffset = mTotalDragDistance * .5f;
        Log.i(TAG, mGlassesFinalTopOffset + "");

        mSunLeftOffset = 0.3f * (float) mScreenWidth;
        mSunTopOffset = (mTotalDragDistance * 0.5f);

        mTop = 0;
    }

    private void createBitmaps() {
        mGlasses = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.glasses);
        mEye = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.eyes);
    }

    private void setupAnimations() {
        mAnimation = new Animation() {
            @Override
            public void applyTransformation(float interpolatedTime, Transformation t) {
                setRotate(interpolatedTime);
            }
        };
        mAnimation.setRepeatCount(Animation.INFINITE);
        mAnimation.setRepeatMode(Animation.RESTART);
        mAnimation.setInterpolator(LINEAR_INTERPOLATOR);
        mAnimation.setDuration(ANIMATION_DURATION);
    }

    public void setRotate(float rotate) {
        mRotate = rotate;
        mParent.invalidate();
        invalidateSelf();
    }

    public void setPercent(float percent) {
        mPercent = percent;
        setRotate(percent);
    }

    public void resetOriginals() {
        setPercent(0);
        setRotate(0);
    }

    public int getTotalDragDistance() {
        return mTotalDragDistance;
    }

    public void offsetTopAndBottom(int offset) {
        mTop = offset;
        invalidateSelf();
    }

    private void drawGlasses(Canvas canvas) {
        Matrix matrix = mMatrix;
        matrix.reset();

        //mTop为当前显示拖动高度，会大于mTotalDragDistance，padY为两者差值，过拉状态补长
        int padY = Math.max(0, mTop - mTotalDragDistance);
//        Log.i(TAG, mTop + "|" + padY);
        float dragPercent = Math.min(1f, Math.abs(mPercent));
        float glassesScale;
        float glassesTopOffset;
        float scalePercentDelta = dragPercent - SCALE_START_PERCENT;
        //从大于0.3的拖动百分比，开始动画效果
        if (scalePercentDelta > 0) {
            /**
             * Change glassesScale between {@link #GLASSES_INITIAL_SCALE} and {@link #GLASSES_FINAL_SCALE} depending on {@link #mPercent}
             * Change glassesTopOffset between {@link #mGlassesInitialTopOffset} and {@link #mGlassesFinalTopOffset} depending on {@link #mPercent}
             */
            //重新计算百分比，使其映射到0~1
            float scalePercent = scalePercentDelta / (1.0f - SCALE_START_PERCENT);
            //按百分比过渡大小
            glassesScale = GLASSES_INITIAL_SCALE + (GLASSES_FINAL_SCALE - GLASSES_INITIAL_SCALE) * scalePercent;
            //按百分比过渡距离top偏移
            glassesTopOffset = mGlassesInitialTopOffset - (mGlassesFinalTopOffset - mGlassesInitialTopOffset) * scalePercent;
        } else {
            glassesScale = GLASSES_INITIAL_SCALE;
            glassesTopOffset = mGlassesInitialTopOffset;
        }

        float offsetX = -mGlasses.getWidth() * glassesScale / 2;
        float offsetY = padY + glassesTopOffset + mGlasses.getHeight() * glassesScale / 2; // Offset town scaling

        matrix.postScale(glassesScale, glassesScale);
        matrix.postTranslate(offsetX, offsetY);

        canvas.drawBitmap(mGlasses, matrix, null);

        //draw eyes
        dragPercent = mPercent;
        if (dragPercent > 1.0f) { // Slow down if pulling over set height
            dragPercent = (dragPercent + 9.0f) / 10;
        }

        float eyeRotateGrowth;

        scalePercentDelta = dragPercent - SCALE_START_PERCENT;
        if (scalePercentDelta > 0) {
            float scalePercent = scalePercentDelta / (1.0f - SCALE_START_PERCENT);
            eyeRotateGrowth = EYE_INITIAL_ROTATE_GROWTH + (EYE_FINAL_ROTATE_GROWTH - EYE_INITIAL_ROTATE_GROWTH) * scalePercent;
        } else {
            eyeRotateGrowth = EYE_INITIAL_ROTATE_GROWTH;
        }

        Paint paint = new Paint();
        int alpha;
        if (dragPercent < EYE_ALPHA_START_PERCENT) {
            alpha = 0;
        } else {
            alpha = (int) (100 * (dragPercent - EYE_ALPHA_START_PERCENT) * (1 / EYE_ALPHA_START_PERCENT));
        }
        paint.setAlpha(alpha);

        float r = (isRefreshing ? 360 : -360) * mRotate * (isRefreshing ? 1 : eyeRotateGrowth);

        matrix.reset();
        matrix.postRotate(r, PtrLocalDisplay.dp2px(9), PtrLocalDisplay.dp2px(9));
        matrix.postTranslate(offsetX + PtrLocalDisplay.dp2px(10), offsetY + PtrLocalDisplay.dp2px(4));
        canvas.drawBitmap(mEye, matrix, paint);

        matrix.reset();
        matrix.postRotate(r, PtrLocalDisplay.dp2px(9), PtrLocalDisplay.dp2px(9));
        matrix.postTranslate(offsetX + PtrLocalDisplay.dp2px(46), offsetY + PtrLocalDisplay.dp2px(4));
        canvas.drawBitmap(mEye, matrix, paint);
    }
}
