package com.menglingpeng.vonvimeo.mvp.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.menglingpeng.vonvimeo.mvp.view.custom.BaseKeyboard;
import com.menglingpeng.vonvimeo.utils.UnitConversionUtils;

import java.lang.reflect.Method;
import java.util.List;

public class KeyboardManager {

    protected Context context;

    protected ViewGroup rootView;

    protected FrameLayout.LayoutParams keyboardContainerLayoutParams;

    protected BaseKeyboard.DefaultKeyStyle defaultKeyStyle = new BaseKeyboard.DefaultKeyStyle();

    public KeyboardManager(Context context) {
        context = context;
        if (context instanceof Activity) {
            rootView = (ViewGroup) ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
            hideSystemSoftKeyboard(keyboardWithSearchView.getEditText());
            keyboardContainerLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                    .LayoutParams.WRAP_CONTENT);
            keyboardContainerLayoutParams.gravity = Gravity.BOTTOM;
        } else {
        }
    }


    public void setSearchResult(List data, boolean hasFixedSize) {
        keyboardWithSearchView.setSearchResult(data,hasFixedSize);
    }

    public void bindToEditor(EditText editText, BaseKeyboard keyboard) {
        hideSystemSoftKeyboard(editText);
        editText.setTag(R.id.bind_keyboard_2_editor, keyboard);
        if (keyboard.getKeyStyle() == null) {
            keyboard.setKeyStyle(defaultKeyStyle);
        }
        editText.setOnFocusChangeListener(editorFocusChangeListener);
    }

    private BaseKeyboard getBindKeyboard(EditText editText) {
        if (editText != null) {
            return (BaseKeyboard) editText.getTag(R.id.bind_keyboard_2_editor);
        }
        return null;
    }

    public void setShowAnchorView(View showAnchorView,EditText editText) {
        editText.setTag(R.id.anchor_view,showAnchorView);
    }

    public void showSoftKeyboard(EditText editText) {
        rootView.addOnLayoutChangeListener(onLayoutChangeListener);
        BaseKeyboard keyboard = getBindKeyboard(editText);
        if (keyboard == null) {
            return;
        }
        keyboard.setEditText(editText);
        keyboard.setNextFocusView(keyboardWithSearchView.getEditText());
        initKeyboard(keyboard);
        keyboardWithSearchView.getKeyboadViewContainer().setPadding(UnitConversionUtils.dipToPx(context, keyboard.getPadding().left),
                .dipToPx(context, keyboard.getPadding().top),
                UnitConversionUtils.dipToPx(context, keyboard.getPadding().right),
                UnitConversionUtils.dipToPx(context, keyboard.getPadding().bottom));
        if(rootView.indexOfChild(keyboardWithSearchView) == -1) {
            rootView.addView(keyboardWithSearchView, keyboardContainerLayoutParams);
        }else {
            keyboardWithSearchView.setVisibility(View.VISIBLE);
        }
        keyboardWithSearchView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.down_to_up));
    }

    private void hideSoftKeyboard() {
        //mRootView.removeView(mKeyboardWithSearchView);
        mKeyboardWithSearchView.setVisibility(View.GONE);
        mKeyboardWithSearchView.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.up_to_hide));
        //mRootView.removeOnLayoutChangeListener(mOnLayoutChangeListener);
    }

    public void hideKeyboard() {
        if(rootView != null) {
            rootView.clearFocus();
        }
    }

    public static void hideSystemSoftKeyboard(EditText editText) {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 11) {
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(editText, false);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            editText.setInputType(InputType.TYPE_NULL);
        }
    }

    private final View.OnFocusChangeListener editorFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(final View v, boolean hasFocus) {
            if (v instanceof EditText) {
                if (hasFocus) {
                    v.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showSoftKeyboard((EditText) v);
                        }
                    },300);
                } else {
                    hideSoftKeyboard();
                }
            }
        }
    };
}
