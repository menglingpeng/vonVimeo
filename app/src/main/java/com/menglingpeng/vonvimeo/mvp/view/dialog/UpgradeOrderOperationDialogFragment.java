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
        void onUploadDialogLoginListener(Button button, ProgressBar progressBar, Dialog dialog);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(!(getActivity() instanceof OperationDialogListener)){
            throw new IllegalStateException(context.getString(R.string.login_dialog_fragment_exception));
        }
        listener = (OperationDialogListener)getActivity();
    }

}
