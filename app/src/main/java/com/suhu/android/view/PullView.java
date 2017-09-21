package com.suhu.android.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import com.suhu.android.R;

/**
 * Created by Administrator on 2017/9/21 0021.
 */

public class PullView extends View {
    //圆的画笔
    private Paint mCirClePaint;
    //圆的半径
    private float MCirCleRadius = 50;

    //可拖动的高度
    private int mDragHeight = 300;
    //进度值
    private float mProgress;
    private float mCirClePointX, mCirClePointY;

    //目标宽度
    private int mTargetWidth = 400;
    //贝塞尔曲线的路劲以及画笔
    private Paint mPathPaint;
    private Path mPath = new Path();
    //重心点最总高度，决定控制点的Y坐标
    private int mTarGetGravityHeight = 10;
    //角度变换，0-135度
    private int mTangentAngle = 105;


    private Drawable mContent = null;

    private  int mContentMargin= 0;

    private Interpolator mProgressInterpolator = new DecelerateInterpolator();

    private Interpolator mTangentInterpolator;

    public PullView(Context context) {

        super(context);
        init(null);
    }

    public PullView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PullView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PullView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    /**
     * 当进行测量的时候触发
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //宽度的意图。类型
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        //高度的类型
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int measureWidth;
        int measureHeight;
        int iWidth = (int) (2 * MCirCleRadius + getPaddingLeft() + getPaddingRight());
        int iHeight = (int) ((mDragHeight * mProgress + 0.5f) + getPaddingTop() + getPaddingBottom());


        if (widthMode == MeasureSpec.EXACTLY) {
            //却确的
            measureWidth = width;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //最多的
            measureWidth = Math.min(iWidth, width);
        } else {
            measureWidth = iWidth;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            //却确的
            measureHeight = height;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //最多的
            measureHeight = Math.min(iHeight, height);
        } else {
            measureHeight = iHeight;
        }
        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //进行基础坐标参数系改变
        int count = canvas.save();
        float tranX = (getWidth() - getValueByLine(getWidth(), mTargetWidth, mProgress)) / 2;

        canvas.translate(tranX, 0);
        //画贝塞尔曲线
        canvas.drawPath(mPath, mPathPaint);
        //画圆
        canvas.drawCircle(mCirClePointX, mCirClePointY, MCirCleRadius, mCirClePaint);
        Drawable drawable = mContent;
        if(drawable!=null){
            //剪切举行区域
            Log.i("Tag","----=--lai");
            canvas.save();
            canvas.clipRect(drawable.getBounds());
            //绘制Drawble
            drawable.draw(canvas);
            canvas.restore();
        }

        canvas.restoreToCount(count);
    }

    /**
     * 当大小该表时调用
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //当高度变化时，进行更新
        updatePathLayout();
    }

    /**
     * 初始化方法
     * @param attrs
     */
    private void init(AttributeSet attrs) {

        final Context context = getContext();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.PullView);
        int color = array.getColor(R.styleable.PullView_pColor,0xff000000);

        MCirCleRadius = array.getDimension(R.styleable.PullView_pRadius,50);

        mDragHeight = array.getDimensionPixelOffset(R.styleable.PullView_pDragHeight,mDragHeight);
        mTangentAngle =array.getInteger(R.styleable.PullView_pTangentAngle,100);
        mTargetWidth = array.getDimensionPixelOffset(R.styleable.PullView_pTargetWidth,mTargetWidth);
        mTarGetGravityHeight = array.getDimensionPixelOffset(R.styleable.PullView_pTargetGravityHeight,mTarGetGravityHeight);
        //销毁

        mContent = array.getDrawable(R.styleable.PullView_pContentDrawBle);
        mContentMargin= array.getDimensionPixelOffset(R.styleable.PullView_pConTentDrawableMargin,0);


        array.recycle();
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置看锯齿
        p.setAntiAlias(true);
        //设置防抖动
        p.setDither(true);
        //设置为填充方式
        p.setStyle(Paint.Style.FILL);
        p.setColor(color);
        mCirClePaint = p;

        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置看锯齿
        p.setAntiAlias(true);
        //设置防抖动
        p.setDither(true);
        //设置为填充方式
        p.setStyle(Paint.Style.FILL);
        p.setColor(color);
        mPathPaint = p;
        //切角路劲插值器
        mTangentInterpolator = PathInterpolatorCompat.create((MCirCleRadius*2.0f)/mDragHeight,90.0f/mTangentAngle);

    }

    public void setProgress(float progress) {
        Log.i("TAGPULLVIEW", progress + "");
        this.mProgress = progress;
        //请求重新绘制
        requestLayout();
    }

    /**
     * 更行我们的路径
     */
    private void updatePathLayout() {
        //获取进度
        final float progress = mProgressInterpolator.getInterpolation(mProgress);
        //获取可绘制区域高度宽度
        final float w = getValueByLine(getWidth(), mTargetWidth, mProgress);
        final float h = getValueByLine(0, mDragHeight, mProgress);
        //圆的中心点X坐标
        final float cPointX = w / 2;
        //圆的半径
        final float cRadius = MCirCleRadius;
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
        updateContentLayout(cPointX,cPointY,cRadius);
    }

    /**
     * 对内容部分进行测量病设置
     * @param cx
     * @param cy
     * @param radius
     */
    private void updateContentLayout(float cx,float cy,float radius){
        Drawable drawable = mContent;

        if(drawable!= null)
        {

            int margin  = mContentMargin;
            int l = (int) (cx - radius+margin);
            int r = (int) (cx + radius-margin);
            int t = (int) (cy - radius+margin);
            int b= (int) (cy + radius-margin);
            drawable.setBounds(l,t,r,b);

        }

    }

    /**
     * 获取当前值
     *
     * @param start    起始值
     * @param end      结束值
     * @param progress 进度
     * @return
     */
    private float getValueByLine(float start, float end, float progress) {
        return start + (end - start) * progress;
    }

    //释放动画
    private ValueAnimator valueAnimator;

    /**
     * 释放操作动画
     */
    public void release() {
        if (valueAnimator == null) {
            ValueAnimator animator = ValueAnimator.ofFloat(mProgress, 0f);
            animator.setInterpolator(new DecelerateInterpolator());//设置动画减速
            animator.setDuration(400);//动画时间
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animator) {
                    Object val = animator.getAnimatedValue();
                    if (val instanceof Float) {
                        setProgress((Float) val);
                    }
                }
            });

            valueAnimator = animator;
        } else {
            valueAnimator.cancel();
            valueAnimator.setFloatValues(mProgress, 0f);
        }
        valueAnimator.start();

    }
}
