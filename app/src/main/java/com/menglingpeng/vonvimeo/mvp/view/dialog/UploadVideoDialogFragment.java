package com.menglingpeng.vonvimeo.mvp.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.menglingpeng.vonvimeo.utils.Constants;

public class UploadVideoDialogFragment extends AppCompatDialogFragment {

    private Dialog dialog;
    private ImageView closeIv;;
    private ImageView pauseIv;
    private ImageView settingsIv;
    private ProgressBar uploadPb;
    private UploadDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getContext(), R.style.ThemeLoginDialog);
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_upload_a_video, null);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.LoginDialog);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
        dialog.setContentView(dialogView);
        settingsIv = (ImageView) dialogView.findViewById(R.id.upload_video_settings_iv);
        closeIv = (ImageView) dialogView.findViewById(R.id.upload_video_close_iv);
        pauseIv = (ImageView) dialogView.findViewById(R.id.upload_video_pause_iv);
        uploadPb = (ProgressBar) dialogView.findViewById(R.id.upload_video_pb);
        dialog.show();
        closeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        settingsIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        pauseIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return dialog;
    }

    public interface UploadDialogListener{
        void onUploadDialogLoginListener(Button button, ProgressBar progressBar, Dialog dialog);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(!(getActivity() instanceof UploadDialogListener)){
            throw new IllegalStateException(context.getString(R.string.login_dialog_fragment_exception));
        }
        listener = (UploadDialogListener)getActivity();
    }
}
