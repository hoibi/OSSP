package com.app.ossp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

//화면에 보여질 memo의 textview를 초기화 하기 위한 클래스 어댑터와 연결되어 사용된다.
public class MemoViewHolder extends RecyclerView.ViewHolder {
    public TextView tvMemo;

    public MemoViewHolder(@NonNull View itemView) {
        super(itemView);

        tvMemo = itemView.findViewById(R.id.tv_memo_item);
    }
}
