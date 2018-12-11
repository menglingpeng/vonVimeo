package com.menglingpeng.vonvimeo.mvp.view.custom;

import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;

public class NumberKeyboardView extends KeyboardView {
    public NumberKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NumberKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    public void initView(Context context, AttributeSet attrs, int defStyleAttr) {

    }

}
