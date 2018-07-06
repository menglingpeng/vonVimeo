package com.menglingpeng.vonvimeo.mvp.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

public class LoginDialogFragment extends AppCompatDialogFragment {

    private Dialog dialog;
    private ImageView loginDialogCloseIv;
    private Button loginDialogLoginBt;
    private ProgressBar loginDialogPb;
    private LoginDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getContext(), R.style.ThemeLoginDialog);
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_fragment_login, null);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.LoginDialog);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
        dialog.setContentView(dialogView);
        loginDialogCloseIv = (ImageView) dialogView.findViewById(R.id.dialog_login_close_iv);
        loginDialogLoginBt = (Button) dialogView.findViewById(R.id.dialog_login_bt);
        loginDialogPb = (ProgressBar) dialogView.findViewById(R.id.dialog_login_pb);
        dialog.show();
        loginDialogCloseIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        loginDialogLoginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLoginDialogLoginListener(loginDialogLoginBt, loginDialogPb, dialog);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(Constants.REDIRECT_USERS_TO_REQUEST_DRIBBBLE_ACCESS_URL);
                intent.setData(uri);
                startActivity(intent);
            }
        });

        return dialog;
    }

    public interface LoginDialogListener{
        void onLoginDialogLoginListener(Button button, ProgressBar progressBar, Dialog dialog);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(!(getActivity() instanceof LoginDialogListener)){
            throw new IllegalStateException(context.getString(R.string.login_dialog_fragment_exception));
        }
        listener = (LoginDialogListener)getActivity();
    }
}
