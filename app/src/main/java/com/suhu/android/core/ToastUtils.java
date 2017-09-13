package com.suhu.android.core;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 *@author suhu
 *@time 2017/4/8 10:01
 *
*/
public class ToastUtils {

    private static Toast toast;

    public static void disPlayShort(Context context, String content) {
        dissmis();
        toast = Toast.makeText(context.getApplicationContext(), content, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void disPlayLong(Context context, String content) {
        dissmis();
        toast = Toast.makeText(context.getApplicationContext(), content, Toast.LENGTH_LONG);
        toast.show();
    }

    public static void disPlayShortCenter(Context context, String content) {
        dissmis();
        toast = Toast.makeText(context.getApplicationContext(), content, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void disPlayLongCenter(Context context, String content) {
        dissmis();
        toast = Toast.makeText(context.getApplicationContext(), content, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void disPlayShort(Context context, String content, int imgId) {
        dissmis();
        toast = Toast.makeText(context.getApplicationContext(), content, Toast.LENGTH_SHORT);
        LinearLayout toastView = (LinearLayout) toast.getView();
        ImageView imageCodeProject = new ImageView(context);
        imageCodeProject.setImageResource(imgId);
        toastView.addView(imageCodeProject, 0);
        toast.show();
    }

    public static void disPlayShortCenter(Context context, String content, int imgId) {
        dissmis();
        toast = Toast.makeText(context.getApplicationContext(), content, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastView = (LinearLayout) toast.getView();
        ImageView imageCodeProject = new ImageView(context);
        imageCodeProject.setImageResource(imgId);
        toastView.addView(imageCodeProject, 0);
        toast.show();
    }

    public static void disPlayLong(Context context, String content, int imgId) {
        dissmis();
        toast = Toast.makeText(context.getApplicationContext(), content, Toast.LENGTH_LONG);
        LinearLayout toastView = (LinearLayout) toast.getView();
        ImageView imageCodeProject = new ImageView(context);
        imageCodeProject.setImageResource(imgId);
        toastView.addView(imageCodeProject, 0);
        toast.show();
    }

    public static void disPlayLongCenter(Context context, String content, int imgId) {
        dissmis();
        toast = Toast.makeText(context.getApplicationContext(), content, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastView = (LinearLayout) toast.getView();
        ImageView imageCodeProject = new ImageView(context);
        imageCodeProject.setImageResource(imgId);
        toastView.addView(imageCodeProject, 0);
        toast.show();
    }

    public static void disPlayShort(Context context, View view) {
        dissmis();
        toast = new Toast(context.getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

    public static void disPlayShortCenter(Context context, View view) {
        dissmis();
        toast = new Toast(context.getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(view);
        toast.show();
    }

    public static void disPlayLong(Context context, View view) {
        dissmis();
        toast = new Toast(context.getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }

    public static void disPlayLongCenter(Context context, View view) {
        dissmis();
        toast = new Toast(context.getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(view);
        toast.show();
    }

    public static void dissmis() {
        if (toast != null) {
            toast.cancel();
        }
    }
}
