package com.suhu.android.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.suhu.android.R;
import com.suhu.android.base.activity.BaseTitleActivity;
import com.suhu.android.view.PullView;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/9/21 0021.
 */

public class BesselActivity extends BaseTitleActivity{
    @BindView(R.id.bessel)
    LinearLayout bessel;
    @BindView(R.id.pullView)
    PullView pullView;

    private static final float TOUCH_MOVE_MAX_Y = 600;
    private float mTouchMoveStartY = 0;

    @Override
    public int showContView() {
        return R.layout.activity_bessel;
    }

    @Override
    public void setActionBar() {
        title.setText("贝塞尔曲线");
    }

    @Override
    public void setCreateView(Bundle savedInstanceState) {
        setListener();
    }

    private void setListener() {
        bessel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        mTouchMoveStartY = motionEvent.getY();
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        float y = motionEvent.getY();
                        if (y > mTouchMoveStartY) {
                            float moveSize = y-mTouchMoveStartY;
                            float progress = moveSize>=TOUCH_MOVE_MAX_Y?1:moveSize/TOUCH_MOVE_MAX_Y;
                            pullView.setProgress(progress);
                        }
                        break;
                    case MotionEvent.ACTION_UP:

                        pullView.release();
                        break;
                }
                return false;
            }
        });

    }
}
