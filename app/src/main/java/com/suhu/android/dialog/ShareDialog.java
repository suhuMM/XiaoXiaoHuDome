package com.suhu.android.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.suhu.android.R;

import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;


/**
 * Created by Administrator on 2017/9/7 0007.
 * https://github.com/tvbarthel/BlurDialogFragment
 */

public class ShareDialog extends SupportBlurDialogFragment implements View.OnClickListener{
    private static final String BUNDLE_KEY_DOWN_SCALE_FACTOR = "bundle_key_down_scale_factor";
    private static final String BUNDLE_KEY_BLUR_RADIUS = "bundle_key_blur_radius";
    private static final String BUNDLE_KEY_DIMMING = "bundle_key_dimming_effect";
    private static final String BUNDLE_KEY_DEBUG = "bundle_key_debug_effect";
    private static final String BUNDLE_KEY_BLURRED_ACTION_BAR = "bundle_key_blurred_action_bar";
    private static final String BUNDLE_KEY_USE_RENDERSCRIPT = "bundle_key_use_renderscript";

    private int mRadius;
    private float mDownScaleFactor;
    private boolean mDimming;
    private boolean mDebug;
    private boolean mBlurredActionBar;
    private boolean mUseRenderScript;

    private ImageView sina,qq,wechat,wxcircle;


    private OnShareClickListener listener;



    public interface OnShareClickListener{
        void onClick(View view);
    }
    public void setOnShareClickListener(OnShareClickListener listener){
        this.listener = listener;
    }

    public static ShareDialog newInstance(int radius,
                                          float downScaleFactor,
                                          boolean dimming,
                                          boolean debug,
                                          boolean mBlurredActionBar) {
        ShareDialog fragment = new ShareDialog();
        Bundle args = new Bundle();
        args.putInt(
                BUNDLE_KEY_BLUR_RADIUS,
                radius
        );
        args.putFloat(
                BUNDLE_KEY_DOWN_SCALE_FACTOR,
                downScaleFactor
        );
        args.putBoolean(
                BUNDLE_KEY_DIMMING,
                dimming
        );
        args.putBoolean(
                BUNDLE_KEY_DEBUG,
                debug
        );
        args.putBoolean(
                BUNDLE_KEY_BLURRED_ACTION_BAR,
                mBlurredActionBar
        );
        args.putBoolean(
                BUNDLE_KEY_USE_RENDERSCRIPT,
                false
        );

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Bundle args = getArguments();
        mRadius = args.getInt(BUNDLE_KEY_BLUR_RADIUS);
        mDownScaleFactor = args.getFloat(BUNDLE_KEY_DOWN_SCALE_FACTOR);
        mDimming = args.getBoolean(BUNDLE_KEY_DIMMING);
        mDebug = args.getBoolean(BUNDLE_KEY_DEBUG);
        mBlurredActionBar = args.getBoolean(BUNDLE_KEY_BLURRED_ACTION_BAR);
        mUseRenderScript = args.getBoolean(BUNDLE_KEY_USE_RENDERSCRIPT);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_fragment, null);
        sina = view.findViewById(R.id.sina);
        qq = view.findViewById(R.id.qq);
        wechat = view.findViewById(R.id.wechat);
        wxcircle = view.findViewById(R.id.wxcircle);

        sina.setOnClickListener(this);
        qq.setOnClickListener(this);
        wechat.setOnClickListener(this);
        wxcircle.setOnClickListener(this);

        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view);
    }

    @Override
    protected boolean isDebugEnable() {
        return mDebug;
    }

    @Override
    protected boolean isDimmingEnable() {
        return mDimming;
    }

    @Override
    protected boolean isActionBarBlurred() {
        return mBlurredActionBar;
    }

    @Override
    protected float getDownScaleFactor() {
        return mDownScaleFactor;
    }

    @Override
    protected int getBlurRadius() {
        return mRadius;
    }

    @Override
    protected boolean isRenderScriptEnable() {
        return mUseRenderScript;
    }


}
