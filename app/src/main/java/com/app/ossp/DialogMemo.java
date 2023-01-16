package com.app.ossp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import androidx.annotation.NonNull;

import java.util.ArrayList;

//
public class DialogMemo extends Dialog {
    public DlgResult mCallback;

    public DialogMemo(@NonNull Context context,DlgResult callback) {
        super(context);

        Dialog dlg = new Dialog(context);
        this.mCallback = callback;

        dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.setCanceledOnTouchOutside(false);
        dlg.setCancelable(false);
    }

    interface DlgResult {
        void onClickBtCreate(String day, String contents);
    }
}





























