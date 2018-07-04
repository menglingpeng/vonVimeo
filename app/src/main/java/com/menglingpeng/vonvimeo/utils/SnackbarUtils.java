package com.menglingpeng.vonvimeo.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

public class SnackbarUtils {

    public static void showSnackShort(Context context, View rootView, CharSequence text) {
        Snackbar snackbar = Snackbar.make(rootView, text, Snackbar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }

    public static void showErrorSnackShort(Context context, View rootView, CharSequence text) {
        Snackbar snackbar = Snackbar.make(rootView, text, Snackbar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        snackbar.show();
    }

    public static void showSnackLong(Context context, View rootView, CharSequence text) {
        Snackbar snackbar = Snackbar.make(rootView, text, Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }
}
