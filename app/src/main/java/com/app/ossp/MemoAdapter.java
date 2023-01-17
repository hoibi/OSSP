package com.app.ossp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


//memo데이터를 리스트화 하여 화면에 보여줄 수 있게 해주는 클래스.
public class MemoAdapter extends RecyclerView.Adapter<MemoViewHolder> {
    private ArrayList<String> memoList;

    // 현재까지의 메모가 담김 list를 생성자변수로 받는다.
    public MemoAdapter(ArrayList<String> mList) {
        this.memoList = mList;
    }


    // view 초기화 진행.
    @NonNull
    @Override
    public MemoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater i = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = i.inflate(R.layout.item_memo, parent, false);
        return new MemoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoViewHolder holder, int position) {

        // 실질적으로 화면에 보일 textview에 데이터를 하나씩 꺼내 화면에 뿌려준다.
        holder.tvMemo.setText(memoList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return memoList.size();         // roomdb에 있던 모든 memo를 담은 list의 크기만큼 화면에 칸수가 결정된다.
    }


    // roomdb의 데이터를 지울시 화면에 이미 있던 데이터들도 지워줘야 하기때문에 (새로고침이라고 이해하면 편함) 진행하는 메서드.
    public void clearMemo() {
        memoList = new ArrayList<>();
        notifyDataSetChanged();
    }
}

