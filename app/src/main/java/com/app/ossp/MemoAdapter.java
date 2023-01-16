package com.app.ossp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//
public class MemoAdapter extends RecyclerView.Adapter<MemoViewHolder> {
    private ArrayList<String> memoList;

    public MemoAdapter(ArrayList<String> mList) {
        this.memoList = mList;
    }

    @NonNull
    @Override
    public MemoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater i = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = i.inflate(R.layout.item_memo, parent, false);
        return new MemoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoViewHolder holder, int position) {
        holder.tvMemo.setText(memoList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return memoList.size();
    }

    public void clearMemo() {
        memoList = new ArrayList<>();
        notifyDataSetChanged();
    }
}

