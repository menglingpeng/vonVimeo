package com.menglingpeng.vonvimeo.mvp.view.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.support.annotation.IntegerRes;
import android.text.Editable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;

public abstract class BaseKeyboard extends Keyboard implements KeyboardView.OnKeyboardActionListener{

    private EditText editText;

    private View nextFocusView;

    private KeyStyle keyStyle;

    protected Context context;

    public BaseKeyboard(Context context, int xmlLayoutResId) {
        super(context, xmlLayoutResId);
        context = context;
    }

    public BaseKeyboard(Context context, int xmlLayoutResId, int modeId) {
        super(context, xmlLayoutResId, modeId);
        context = context;
    }

    public BaseKeyboard(Context context, int xmlLayoutResId, int modeId, int width, int height) {
        super(context, xmlLayoutResId, modeId, width, height);
        context = context;
    }

    public BaseKeyboard(Context context, int layoutTemplateResId, CharSequence characters, int columns, int horizontalPadding) {
        super(context, layoutTemplateResId, characters, columns, horizontalPadding);
        context = context;
    }

    public void setEditText(EditText editText) {
        editText = editText;
    }

    public void setNextFocusView(View nextFocusView) {
        nextFocusView = nextFocusView;
    }

    public void setKeyStyle(KeyStyle keyStyle) {
        keyStyle = keyStyle;
    }

    public EditText getEditText() {
        return editText;
    }

    public View getNextFocusView() {
        return nextFocusView;
    }

    public KeyStyle getKeyStyle() {
        return keyStyle;
    }

    public int getKeyCode(@IntegerRes int redId) {
        return context.getResources().getInteger(redId);
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        if(null != editText && editText.hasFocus() && !handleSpecialKey(primaryCode)) {
            Editable editable = editText.getText();
            int start = editText.getSelectionStart();
            int end = editText.getSelectionEnd();
            if (end > start){
                editable.delete(start,end);
            }
            if(primaryCode == KEYCODE_DELETE) {
                if(!TextUtils.isEmpty(editable)) {
                    if(start > 0) {
                        editable.delete(start-1,start);
                    }
                }
            }else if(primaryCode == getKeyCode(R.integer.hide_keyboard)){
                hideKeyboard();
            }else {
                editable.insert(start,Character.toString((char) primaryCode));
            }
        }
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }

    public void hideKeyboard() {
        if(nextFocusView != null) {
            nextFocusView.requestFocus();
        }
    }

    /**
     *
     * @param primaryCode
     * @return true if handle the key
     *         false no handle and dispatch
     */
    public abstract boolean handleSpecialKey(int primaryCode);

    public interface KeyStyle {

        public Drawable getKeyBackound(Key key);

        public Float getKeyTextSize(Key key);

        public Integer getKeyTextColor(Key key);

        public CharSequence getKeyLabel(Key key);
    }

    public Padding getPadding() {
        return new Padding(0,0,0,0);
    }

    public static class DefaultKeyStyle implements KeyStyle {

        @Override
        public Drawable getKeyBackound(Key key) {
            return key.iconPreview;
        }

        @Override
        public Float getKeyTextSize(Key key) {
            return null;
        }

        @Override
        public Integer getKeyTextColor(Key key) {
            return null;
        }

        @Override
        public CharSequence getKeyLabel(Key key) {
            return key.label;
        }
    }

    public static class Padding {
        int top;
        int left;
        int bottom;
        int right;

        /**
         * px
         * @param top
         * @param left
         * @param bottom
         * @param right
         */
        public Padding(int top, int left, int bottom, int right) {
            this.top = top;
            this.left = left;
            this.bottom = bottom;
            this.right = right;
        }
    }

    public float convertSpToPixels(Context context, float sp) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
        return px;
    }
}
