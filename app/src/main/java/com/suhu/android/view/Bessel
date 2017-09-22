package com.suhu.android.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * @author suhu
 * @data 2017/9/22.
 * @description
 */

public class Bessel extends ViewGroup{

    /**
     * 下拉最多高度
     */
    private static final int MAX_MOVE = 350;

    /**
     * 控制点的Y坐标
     */
    private static int mTarGetGravityHeight = 10;

    /**
     * 角度变换，0-120度
     */
    private static int mTangentAngle = 120;

    /**
     * 半径
     */
    private static int radius = 60;

    /**
     * 目标宽度
     */
    private static int mTargetWidth = 700;

    /**
     * 圆的画笔
     */
    private Paint mCirClePaint;

    /**
     * 贝塞尔曲线的路劲以及画笔
     * */
    private Paint mPathPaint;

    private int scrollStartY,scrollEndY;
    private Scroller scroller;
    private int mLsstY,y;

    private float down;
    private float progress;
    private float mCirClePointX, mCirClePointY;


    private Path mPath = new Path();
    private ValueAnimator valueAnimator;
    private Interpolator mTangentInterpolator;
    private Interpolator mProgressInterpolator = new DecelerateInterpolator();



    public Bessel(Context context) {
        super(context);
        init(context);
    }



    public Bessel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Bessel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int child = getChildCount();
        MarginLayoutParams params = (MarginLayoutParams) getLayoutParams();
        int totalHeight = (int) (MAX_MOVE*progress+0.5f);
        for (int i = 0; i < child; i++) {
            View childView = getChildAt(i);
            int height = childView.getMeasuredHeight();
            if (childView.getVisibility()!=GONE){
                childView.layout(l,totalHeight,r,totalHeight+height);
                totalHeight = totalHeight+height;
            }
        }
        params.height = totalHeight;
        setLayoutParams(params);

        updatePathLayout(progress);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpec = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        int heightSpec = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        DisplayMetrics display = getResources().getDisplayMetrics();

        int measureWidth;
        int measureHeight;
        switch (widthSpec){
            case MeasureSpec.EXACTLY:
                measureWidth = width;
                break;
            case MeasureSpec.AT_MOST:
                measureWidth = Math.min(width,display.widthPixels);
                break;
            default:
                measureWidth = display.widthPixels;
                break;
        }

        switch (heightSpec){
            case MeasureSpec.EXACTLY:
                measureHeight = height;
                break;
            case MeasureSpec.AT_MOST:
                measureHeight = Math.min(height,display.heightPixels);
                break;
            default:
                measureHeight = display.heightPixels;
                break;
        }
        setMeasuredDimension(measureWidth,measureHeight);

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                down = event.getY();
                scrollStartY = getScrollY();
                mLsstY = y;
                return true;
            case MotionEvent.ACTION_MOVE:
                float touch = event.getY();

                if (touch>down){
                    float moveSize = touch-down;
                    progress=moveSize>=MAX_MOVE?1:moveSize/MAX_MOVE;
                    requestLayout();
                }else {
                    //scrollEndY = getScrollY();
//                        int dScrollY = (int) (event.getY()-down);
//                        if (!scroller.isFinished()){
//                            scroller.abortAnimation();
//                        }
//                        scrollBy(0,dScrollY);
                }

                break;
            case MotionEvent.ACTION_UP:
                release();
                break;
        }
        return super.onTouchEvent(event);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int count = canvas.save();
        float tranX = (getWidth() - getValueByLine(getWidth(), mTargetWidth, progress)) / 2;
        canvas.translate(tranX, 0);
        //画贝塞尔曲线
        canvas.drawPath(mPath, mPathPaint);
        //画圆
        canvas.drawCircle(mCirClePointX, mCirClePointY, radius, mCirClePaint);
        canvas.restoreToCount(count);
    }

    private void init(Context context) {
        //设置加载onDraw
        setWillNotDraw(false);

        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置看锯齿
        p.setAntiAlias(true);
        //设置防抖动
        p.setDither(true);
        //设置为填充方式
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.RED);
        mCirClePaint = p;

        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置看锯齿
        p.setAntiAlias(true);
        //设置防抖动
        p.setDither(true);
        //设置为填充方式
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.RED);
        mPathPaint = p;
        //切角路劲插值器
        mTangentInterpolator = PathInterpolatorCompat.create((radius*2.0f)/MAX_MOVE,90.0f/mTangentAngle);

        scroller = new Scroller(context);
    }




    /**
     *@method 释放动画
     *@author suhu
     *@time 2017/9/22 13:44
     *
     */
    private void release() {
        if (valueAnimator==null){
            valueAnimator = ValueAnimator.ofFloat(progress,0F);
            valueAnimator.setInterpolator(new DecelerateInterpolator());
            valueAnimator.setDuration(400);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    Object val = valueAnimator.getAnimatedValue();
                    if (val instanceof  Float){
                        progress = (Float) val;
                        requestLayout();
                    }
                }
            });
        }else {
            valueAnimator.cancel();
            valueAnimator.setFloatValues(progress,0F);
        }
        valueAnimator.start();
    }

    /**
     * 更行我们的路径
     */
    private void updatePathLayout(float mProgress) {
        //获取进度
        final float progress = mProgressInterpolator.getInterpolation(mProgress);
        //获取可绘制区域高度宽度
        final float w = getValueByLine(getWidth(), mTargetWidth, mProgress);
        final float h = getValueByLine(0, MAX_MOVE, mProgress);
        //圆的中心点X坐标
        final float cPointX = w / 2;
        //圆的半径
        final float cRadius = radius;
        //圆的中心点Y坐标
        final float cPointY = h - cRadius;
        //控制点借宿Y的值
        final float endControlY = mTarGetGravityHeight;


        mCirClePointX = cPointX;
        mCirClePointY = cPointY;

        final Path path = mPath;

        //复位操作
        path.reset();
        path.moveTo(0, 0);
        //左边部分的结束点和控制点
        float lEndPointX, lEndPointY;
        float lControlPointX, lControlPointY;
        //获取当前切线的弧度
        float angle =mTangentAngle* mTangentInterpolator.getInterpolation(progress);
        double radian = Math.toRadians(angle);

        float x = (float) (Math.sin(radian) * cRadius);

        float y = (float) (Math.cos(radian) * cRadius);


        lEndPointX = cPointX - x;
        lEndPointY = cPointY + y;
        //控制点Y的变化
        lControlPointY = getValueByLine(0, endControlY, progress);
        //控制点与结束点的高度
        float tHeight = lEndPointY - lControlPointY;
        //控制点与X的坐标距离
        float tWidth = (float) (tHeight / Math.tan(radian));
        lControlPointX = lEndPointX - tWidth;

        path.quadTo(lControlPointX, lControlPointY, lEndPointX, lEndPointY);

        //链接到右边
        path.lineTo(cPointX + (cPointX - lEndPointX), lEndPointY);
        path.quadTo(cPointX + cPointX - lControlPointX, lControlPointY, w, 0);
    }

    private float getValueByLine(float start, float end, float progress) {
        return start + (end - start) * progress;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()){
            scrollTo(0,scroller.getCurrY());
            postInvalidate();
        }
    }
}
