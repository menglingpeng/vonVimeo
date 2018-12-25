package com.menglingpeng.vonvimeo.mvp.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.mvp.view.activity.AddCardActivity;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import static android.app.Activity.RESULT_OK;

public class UpgradeOrderOperationDialogFragment extends DialogFragment {

    private Dialog dialog;
    private ImageView wechatIv;
    private ImageView wechatCircleIv;
    private ImageView homeIv;
    private ImageView refreshIv;
    private ImageView textsizeTv;
    private TextView cancleTv;
    private Dialog textsizeDialog;
    private OperationDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
        dialog = new Dialog(getContext(), R.style.ThemeLoginDialog);
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_supported_bank_card_operation, null);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.LoginDialog);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
        dialog.setContentView(dialogView);
        wechatIv = (ImageView) dialogView.findViewById(R.id.wechat_iv);
        wechatCircleIv = (ImageView) dialogView.findViewById(R.id.wechat_circle_iv);
        homeIv = (ImageView) dialogView.findViewById(R.id.back_home_iv);
        refreshIv = (ImageView) dialogView.findViewById(R.id.refresh_iv);
        textsizeTv = (ImageView) dialogView.findViewById(R.id.textsize_iv);
        cancleTv = (TextView) dialogView.findViewById(R.id.cancel_tv);
        dialog.show();
        wechatIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        wechatCircleIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        homeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backHome();
            }
        });
        refreshIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });
        textsizeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTextSizeDialog();
            }
        });
        cancleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        return dialog;
    }

    private void backHome(){
        Intent intent = new Intent(getActivity(), AddCardActivity.class);
        getContext().setResult(RESULT_OK, intent);
    }

    private void refresh(){

    }

    private void showTextSizeDialog(){
        SeekBar seekBar;
        int min = 12;
        int recommend = 20;
        int max = 25;
        textsizeDialog = new Dialog(getContext(), R.style.ThemeLoginDialog);
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_supported_bank_card_textsize_settings, null);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.LoginDialog);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
        textsizeDialog.setContentView(dialogView);
        seekBar = (SeekBar) dialogView.findViewById(R.id.textsize_sb);
        textsizeDialog.create();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public interface OperationDialogListener{
        void onOperationDialogLoginListener(Button button, ProgressBar progressBar, Dialog dialog);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(!(getActivity() instanceof OperationDialogListener)){
            throw new IllegalStateException(context.getString(R.string.login_dialog_fragment_exception));
        }
        listener = (OperationDialogListener)getActivity();
    }

    public class WechatShareManager {

        private static WechatShareManager mInstance;
        private ShareContent mShareContentText, mShareContentPicture, mShareContentWebpag, mShareContentVideo;
        private IWXAPI mWXApi;
        private Context mContext;

        public static final int WECHAT_SHARE_WAY_TEXT = 1;   //文字
        public static final int WECHAT_SHARE_WAY_PICTURE = 2; //图片
        public static final int WECHAT_SHARE_WAY_WEBPAGE = 3;  //链接
        public static final int WECHAT_SHARE_WAY_VIDEO = 4; //视频
        public static final int WECHAT_SHARE_TYPE_TALK = SendMessageToWX.Req.WXSceneSession;  //会话
        public static final int WECHAT_SHARE_TYPE_FRENDS = SendMessageToWX.Req.WXSceneTimeline; //朋友圈

        private WechatShareManager(Context context){
            this.mContext = context;
            //初始化数据
            //初始化微信分享代码
            initWechatShare(context);
        }

        /**
         * 获取WeixinShareManager实例
         * 非线程安全，请在UI线程中操作
         * @return
         */


        public static WechatShareManager getInstance(Context context){
            if(mInstance == null){
                mInstance = new WechatShareManager(context);
            }
            return mInstance;
        }

        private void initWechatShare(Context context){
            if (mWXApi == null) {
                mWXApi = WXAPIFactory.createWXAPI(context, WechatShareUtil.WECHAT_APP_ID, true);
            }
            mWXApi.registerApp(WechatShareUtil.WECHAT_APP_ID);
        }
        /**
         * 通过微信分享
         * @param shareContent 分享的方式（文本、图片、链接）
         * @param shareType 分享的类型（朋友圈，会话）
         */
        public void shareByWebchat(ShareContent shareContent, int shareType){
            switch (shareContent.getShareWay()) {
                case WECHAT_SHARE_WAY_TEXT:
                    shareText(shareContent, shareType);
                    break;
                case WECHAT_SHARE_WAY_PICTURE:
                    break;
                case WECHAT_SHARE_WAY_WEBPAGE:
                    break;
                case WECHAT_SHARE_WAY_VIDEO:
                    break;
            }
        }

        private abstract class ShareContent {
            protected abstract int getShareWay();
            protected abstract String getContent();
            protected abstract String getTitle();
            protected abstract String getURL();
            protected abstract int getPictureResource();
        }

        /**
         * 设置分享文字的内容
         * @author chengcj1
         *
         */
        public class ShareContentText extends ShareContent {
            private String content;

            /**
             * 构造分享文字类
             * @param text 分享的文字内容
             */
            public ShareContentText(String content){
                this.content = content;
            }

            @Override
            protected int getShareWay() {
                return WECHAT_SHARE_WAY_TEXT;
            }

            @Override
            protected String getContent() {
                return content;
            }

            @Override
            protected String getTitle() {
                return null;
            }

            @Override
            protected String getURL() {
                return null;
            }

            @Override
            protected int getPictureResource() {
                return -1;
            }
        }

        /*
         * 获取文本分享对象
         */
        public ShareContent getShareContentText(String content) {
            if (mShareContentText == null) {
                mShareContentText = new ShareContentText(content);
            }
            return (ShareContentText) mShareContentText;
        }

        /*
         * 分享文字
         */
        private void shareText(ShareContent shareContent, int shareType) {
            String text = shareContent.getContent();
            //初始化一个WXTextObject对象
            WXTextObject textObj = new WXTextObject();
            textObj.text = text;
            //用WXTextObject对象初始化一个WXMediaMessage对象
            WXMediaMessage msg = new WXMediaMessage();
            msg.mediaObject = textObj;
            msg.description = text;
            //构造一个Req
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            //transaction字段用于唯一标识一个请求
            req.transaction = buildTransaction("textshare");
            req.message = msg;
            //发送的目标场景， 可以选择发送到会话 WXSceneSession 或者朋友圈 WXSceneTimeline。 默认发送到会话。
            req.scene = shareType;
            mWXApi.sendReq(req);
        }

    }

}
