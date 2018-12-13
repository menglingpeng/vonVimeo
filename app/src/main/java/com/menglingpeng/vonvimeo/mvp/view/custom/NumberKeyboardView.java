package com.menglingpeng.vonvimeo.mvp.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import java.lang.reflect.Method;
import java.util.List;

public class NumberKeyboardView extends KeyboardView implements KeyboardView.OnKeyboardActionListener{

    //用于区分左下角空白按键,(要与xml里设置的数值相同)
    private int KEYCODE_EMPTY=-10;
    //删除按键背景图片
    private Drawable deleteDrawable;
    //最下面两个灰色的按键（空白按键跟删除按键）
    private int bgColor;
    protected Context context;
    protected ViewGroup rootView;
    private Keyboard.Key rInvalidatedKey;

    public NumberKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NumberKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    public void initView(Context context, AttributeSet attrs, int defStyleAttr) {


        //获取xml中的按键布局
        Keyboard keyboard=new Keyboard(context,R.xml.numkeyview);
        setKeyboard(keyboard);
        setEnabled(true);
        setPreviewEnabled(false);
        setOnKeyboardActionListener(this);

    }

    Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        List<Keyboard.Key> keys = getKeyboard().getKeys();
        for (Keyboard.Key key : keys) {
            //绘制空白键
            if (key.codes[0] == KEYCODE_EMPTY) {
                drawKeyBackGround(key, canvas);
            } else if (key.codes[0] == Keyboard.KEYCODE_DELETE) {
                //绘制删除键背景
                drawKeyBackGround(key, canvas);
                //绘制按键图片
                drawkeyDelete(key, canvas);
            }
        }
    }

    private void drawKeyBackGround(Keyboard.Key key, Canvas canvas) {
        ColorDrawable colordrawable=new ColorDrawable(bgColor);
        colordrawable.setBounds(key.x,key.y,key.x+key.width,key.y+key.height);
        colordrawable.draw(canvas);
    }
    private void drawkeyDelete(Keyboard.Key key, Canvas canvas) {
        int intrinsicWidth=deleteDrawable.getIntrinsicWidth();
        int intrinsicHeight=deleteDrawable.getIntrinsicHeight();
        int drawWidth=key.width;
        int drawHeight=key.height;
        if(drawWidth<intrinsicWidth){
            drawHeight=drawWidth*intrinsicHeight/intrinsicWidth;
        }
        drawWidth=drawWidth/6;
        drawHeight=drawHeight/6;
        int widthInterval=(key.width-drawWidth)/2;
        int heightInterval=(key.height-drawHeight)/2;

        deleteDrawable.setBounds(key.x+widthInterval,key.y+heightInterval,key.x+widthInterval+drawWidth,
                key.y+heightInterval+drawHeight);
        deleteDrawable.draw(canvas);
    }

    public void bindToEditor(EditText editText, BaseKeyboard keyboard) {
        hideSystemSoftKeyboard(editText);
        editText.setTag(R.id.bind_keyboard_2_editor, keyboard);
        editText.setOnFocusChangeListener(editorFocusChangeListener);
    }

    private BaseKeyboard getBindKeyboard(EditText editText) {
        if (editText != null) {
            return (BaseKeyboard) editText.getTag(R.id.bind_keyboard_2_editor);
        }
        return null;
    }


    //回调接口
    public interface OnKeyPressListener{
        //添加数据回调
        void onInertKey(String text);
        //删除数据回调
        void onDeleteKey();
    }
    private OnKeyPressListener onkeyPressListener;
    public void setOnKeyPressListener(OnKeyPressListener li){
        onkeyPressListener=li;
    }



    @Override
    public void onPress(int i) {

    }

    @Override
    public void onRelease(int i) {

    }

    @Override
    public void onKey(int i, int[] ints) {

        if (i== Keyboard.KEYCODE_DELETE&&onkeyPressListener!=null){
            //添加数据回调
            onkeyPressListener.onDeleteKey();
        }else if (i!=KEYCODE_EMPTY){
            //删除数据回调
            onkeyPressListener.onInertKey(Character.toString((char) i));
        }


    }

    public void showSoftKeyboard(EditText editText) {

        rootView.addOnLayoutChangeListener(mOnLayoutChangeListener);
        BaseKeyboard keyboard = getBindKeyboard(editText);
        if (keyboard == null) {
            return;
        }
        keyboard.setEditText(editText);
        keyboard.setNextFocusView(mKeyboardWithSearchView.getEditText());
        initKeyboard(keyboard);
    }

    private void hideSoftKeyboard() {
        //mRootView.removeView(mKeyboardWithSearchView);
        mKeyboardWithSearchView.setVisibility(View.GONE);
        mKeyboardWithSearchView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.up_to_hide));
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

    @Override
    public void onText(CharSequence charSequence) {

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

    private void onRefreshKey(Canvas canvas) {
        final Paint paint = (Paint) ReflectionUtils.getFieldValue(this, "mPaint");
        final Rect padding = (Rect) ReflectionUtils.getFieldValue(this, "mPadding");
        final int kbdPaddingLeft = getPaddingLeft();
        final int kbdPaddingTop = getPaddingTop();
        Drawable keyBackground = null;
        final Keyboard.Key invalidKey = rInvalidatedKey;
        boolean drawSingleKey = false;

        //拿到当前键盘被弹起的输入源 和 键盘为每个key的定制实现customKeyStyle
        EditText etCur = ((BaseKeyboard) getKeyboard()).getEditText();
        BaseKeyboard.KeyStyle customKeyStyle = ((BaseKeyboard) getKeyboard()).getKeyStyle();

        List<Keyboard.Key> keys = getKeyboard().getKeys();
        final int keyCount = keys.size();
        //canvas.drawColor(0x00000000, PorterDuff.Mode.CLEAR);
        for (int i = 0; i < keyCount; i++) {
            final Keyboard.Key key = keys.get(i);
            if (drawSingleKey && invalidKey != key) {
                continue;
            }

            //获取为Key自定义的背景, 若没有定制, 使用KeyboardView的默认属性keyBackground设置
            keyBackground = customKeyStyle.getKeyBackound(key);
            if (null == keyBackground) {
                keyBackground = rKeyBackground;
            }

            int[] drawableState = key.getCurrentDrawableState();
            keyBackground.setState(drawableState);

            //获取为Key自定义的Label, 若没有定制, 使用xml布局中指定的
            CharSequence keyLabel = customKeyStyle.getKeyLabel(key);
            if (null == keyLabel) {
                keyLabel = key.label;
            }
            // Switch the character to uppercase if shift is pressed
            String label = keyLabel == null ? null : adjustCase(keyLabel).toString();

            final Rect bounds = keyBackground.getBounds();
            if (key.width != bounds.right ||
                    key.height != bounds.bottom) {
                keyBackground.setBounds(0, 0, key.width, key.height);
            }
            canvas.translate(key.x + kbdPaddingLeft, key.y + kbdPaddingTop);
            keyBackground.draw(canvas);

            if (label != null) {
                //获取为Key的Label的字体大小, 若没有定制, 使用KeyboardView的默认属性keyTextSize设置
                Float customKeyTextSize = customKeyStyle.getKeyTextSize(key);
                // For characters, use large font. For labels like "Done", use small font.
                if (null != customKeyTextSize) {
                    paint.setTextSize(customKeyTextSize);
                    paint.setTypeface(Typeface.DEFAULT);
                } else {
                    if (label.length() > 1 && key.codes.length < 2) {
                        paint.setTextSize(rLabelTextSize);
                        paint.setTypeface(Typeface.DEFAULT);
                    } else {
                        paint.setTextSize(rKeyTextSize);
                        paint.setTypeface(Typeface.DEFAULT);
                        //paint.setTypeface(Typeface.DEFAULT);
                    }
                }
            }
        }


    }

    }
