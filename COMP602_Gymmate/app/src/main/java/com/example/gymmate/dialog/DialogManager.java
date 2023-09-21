package com.example.gymmate.dialog;

import android.content.Context;
import android.view.Gravity;

import com.example.gymmate.R;

public class DialogManager {
    private static volatile DialogManager mInstance = null;

    private DialogManager() {

    }

    public static DialogManager getInstance() {
        if (mInstance == null) {
            synchronized (DialogManager.class) {
                if (mInstance == null) {
                    mInstance = new DialogManager();
                }
            }
        }
        return mInstance;
    }

    public DialogView initView(Context context, int layout) {
        return new DialogView(context,layout, R.style.CustomDialog, Gravity.CENTER);
    }

    public DialogView initView(Context context,int layout,int gravity) {
        return new DialogView(context,layout, R.style.mydialog, gravity);
    }

    // 显示弹框
    public void show(DialogView view) {
        if (view != null) {
            if (!view.isShowing()) {
                view.show();
            }
        }
    }

    // 隐藏弹框
    public void hide(DialogView view) {
        if (view != null) {
            if (view.isShowing()) {
                view.dismiss();
            }
        }
    }
}

