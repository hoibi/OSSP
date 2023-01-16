package com.app.ossp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;
// 
public class MemoViewHolder extends RecyclerView.ViewHolder {
    public TextView tvMemo;

    public MemoViewHolder(@NonNull View itemView) {
        super(itemView);

        tvMemo = itemView.findViewById(R.id.tv_memo_item);
    }
}
