package com.zzz.myapplication.util;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;


public class ZToast {
    /**
     * Toast的文本内容。
     */
    private static final String TOAST_CONTENT  = "type";
    /**
     * Toast的显示时间。
     */
    private static final String TOAST_DURATION = "duration";
    /**
     * Toast对象。
     */
    private static Toast sToast;
    /**
     * Handler对象，用来处理子线程的Toast。
     */
    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            Bundle bundle = msg.getData();
            String text = bundle.getString(TOAST_CONTENT);
            Context context = (Context) msg.obj;
            if (sToast == null) {
                sToast = Toast.makeText(context, "", Toast.LENGTH_LONG);
            }
            int duration = bundle.getInt(TOAST_DURATION);
            sToast.setDuration(duration == 1 ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
            sToast.setText(text);
            sToast.show();
        }
    };

    //私有构造函数，防止别人创建本类对象
    private ZToast() {
        super();
    }

    /**
     * 弹出一个时间长的toast,这个toast是安全的,如果当前线程是子线程,那么toast会自动到UI线程中执行。
     *
     * @param context 上下文
     * @param text    提示文本
     */
    public static void showLongToast(Context context, String text) {
        sendMessage(context, text, 0);
    }

    /**
     * 弹出一个时间短的toast,这个toast是安全的,如果当前线程是子线程,那么toast会自动到UI线程中执行。
     *
     * @param context 上下文
     * @param text    提示文本
     */
    public static void showShortToast(Context context, String text) {
        sendMessage(context, text, 1);
    }

    /**
     * 向Handle发送消息执行弹出Toast
     *
     * @param context 上下文
     * @param text    要提示的文本
     * @param time    Toast的时长
     */
    private static void sendMessage(Context context, String text, int time) {
        if (TextUtils.equals("main", Thread.currentThread().getName())) {
            if (sToast == null) {
                sToast = Toast.makeText(context, "", Toast.LENGTH_LONG);
            }
            sToast.setDuration(time == 1 ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
            sToast.setText(text);
            sToast.show();
        } else {
            Message msg = new Message();
            msg.obj = context;
            Bundle Bundle = new Bundle();
            Bundle.putString(TOAST_CONTENT, text);
            Bundle.putInt(TOAST_DURATION, time);
            msg.setData(Bundle);
            mHandler.sendMessage(msg);
        }
    }
}
