package com.app.ossp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import androidx.annotation.NonNull;

import java.util.ArrayList;


/**
 * Dialog는 대화상자
 */
public class DialogMemo extends Dialog {
    public DlgResult mCallback;     // mCallback???

    // @NonNull 널을 허용하지 않겠다
    //시스템이 관리하고 있는 액티비티, 어플리케이션의 정보를 얻기 위해 사용
    public DialogMemo(@NonNull Context context,DlgResult callback) {
        super(context);

        Dialog dlg = new Dialog(context);
        this.mCallback = callback;

        dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.setCanceledOnTouchOutside(false);
        dlg.setCancelable(false);
    }

    //
    interface DlgResult {
        void onClickBtCreate(String day, String contents);
    }
}





























