package com.app.ossp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * recyclerview의 calednar data를 화면에 뿌려주기 위한 viewholder
 */
public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public final TextView dayOfMonth;
    private final CalendarAdapter.OnItemListener onItemListener;

    //CalendarViewHolder의 생성자로 view와 아이템 클릭리스너를 받는다.
    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener)
    {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);   //TextView를 받아서 dayOfMonth에 초기화한다.
        this.onItemListener = onItemListener;           // 생성자로 받은 클릭리스너를 클래스의 onItemListener로 초기화.
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        //  item 클릭시 callback을 받기 위해서 viewholder에 itemView.setOnClickListener(this); 생성
        //  아이탬의 위치를 알기 위해서 어댑터의포지션과 아이탬의 날짜를 클릭리스너의 매개변수로 넣어준다.
        onItemListener.onItemClick(getAdapterPosition(), (String) dayOfMonth.getText());
    }
}
