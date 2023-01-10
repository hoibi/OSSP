package com.app.ossp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Calendar RecyclerView를 화면에 뿌려주기 위한 Adapter
 *
 * 주요 기능
 * -> 아이탬 클릭시 화면에 문구를 띄우기 위한 리스너, 뷰의 초기화
 */
class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>
{
    private final ArrayList<String> daysOfMonth;        // 각 달에 들어가는 날짜들을 담기 위한 ArrayList
    private final OnItemListener onItemListener;        // 클릭에 대한 리스너

    /**
     * recyclerview를 담고있는 뷰의 클래스에서 넘어오는 그 달의 데이터를 매개변수로 받고 daysOfMonth로 초기화
     * 또한 그 해당 클래스의 리스너를 onItemListener로 초기화 시킴.
     *
     * @param daysOfMonth
     * @param onItemListener
     */
    public CalendarAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener)
    {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
    }

    /**
     * 뷰혿더 한칸의 크기와 각 셀의 정의를 진행하는 메서드
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        return new CalendarViewHolder(view, onItemListener);
    }

    /**
     * 직접적으로 해당 달의 일들을 화면에 보여주는 메서드
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position)
    {
        //viewholder의 dayOfMaonth의 텍스트를 해당 클래스에서 넘어오는 데이터로 넘겨준다.
        holder.dayOfMonth.setText(daysOfMonth.get(position));
    }


    /**
     * 해당 달의 일수를 itemcount로 받는다.
     * 결국에는 daysOfMonth의 size가 해당 월의 일수가 된다( = 칸 생성 수)
     * @return
     */
    @Override
    public int getItemCount()
    {
        return daysOfMonth.size();
    }

    /**
     * 아이템의 클릭이벤트를 발생시키기위한 인터페이스
     */
    public interface  OnItemListener
    {
        // 그 아이템의 position(자리)와 text(선택 날짜) 를 넘겨준다.
        void onItemClick(int position, String dayText);
    }
}
