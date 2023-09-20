package com.example.gymmate.dialog

import android.app.Dialog
import android.content.Context
import android.view.Window

class DialogView(@NonNull context: Context?, layout: Int, style: Int, gravity: Int) :
    Dialog(context, style) {
    init {
        setContentView(layout)
        //Window mWindow = getWindow();
//        WindowManager.LayoutParams params = mWindow.getAttributes();
//        params.width = WindowManager.LayoutParams.MATCH_PARENT;
//        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        params.gravity = gravity;
//        mWindow.setAttributes(params);
    }
}