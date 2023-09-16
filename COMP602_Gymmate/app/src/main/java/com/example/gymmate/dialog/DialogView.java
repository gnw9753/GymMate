package com.example.gymmate.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import androidx.annotation.NonNull;

public class DialogView extends Dialog {

    public DialogView(@NonNull Context context, int layout, int style, int gravity) {
        super(context, style);
        setContentView(layout);
        //Window mWindow = getWindow();
//        WindowManager.LayoutParams params = mWindow.getAttributes();
//        params.width = WindowManager.LayoutParams.MATCH_PARENT;
//        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        params.gravity = gravity;
//        mWindow.setAttributes(params);
    }
}