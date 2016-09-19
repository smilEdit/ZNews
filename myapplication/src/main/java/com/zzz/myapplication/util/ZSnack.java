package com.zzz.myapplication.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * @创建者 zlf
 * @创建时间 2016/8/18 17:00
 */
public class ZSnack {
    private static Snackbar sSnackbar;

    public static void showSnackLong(View view, CharSequence show, CharSequence actionShow) {
        if (sSnackbar == null) {
            sSnackbar = Snackbar.make(view, show, Snackbar.LENGTH_LONG);
        }
        sSnackbar.setText(show);
        sSnackbar.setAction(actionShow, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sSnackbar.dismiss();
            }
        });
        sSnackbar.show();
    }

    public static void showSnackShort(View view, CharSequence show) {
        if (sSnackbar == null) {
            sSnackbar = Snackbar.make(view, show, Snackbar.LENGTH_SHORT);
        }
        sSnackbar.setText(show);
        sSnackbar.show();
    }
}
