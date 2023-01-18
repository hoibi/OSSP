package com.app.ossp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * recyclerview의 calednar data를 화면에 뿌려주기 위한 viewholder
 *
 *  ViewHolder
 *  "ListView / RecyclerView 는 inflate를 최소화 하기 위해서 뷰를 재활용 하는데,
 *  이 때 각 뷰의 내용을 업데이트 하기 위해 findViewById 를 매번 호출 해야합니다.
 *  이로 인해 성능저하가 일어남에 따라 ItemView의 각 요소를 바로 엑세스 할 수 있도록 저장해두고 사용하기 위한 객체입니다."
 *
 *  안드로이드에서 Inflate의 정의는 xml 에 표기된 레이아웃들을 메모리에 객체화시키는 행동이다.
 * 쉽게 말해서, XML 코드들을 객체화 해서 코드에서 사용하기 위함이다.
 */
public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public final TextView dayOfMonth;
    private final CalendarAdapter.OnItemListener onItemListener;

    //CalendarViewHolder의 생성자로 view와 아이템 클릭리스너를 받는다.
    //뷰는 안드로이드 기본 화면을 구성하는 모든 기본 화면의 구성요소이다.
    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener)
    {
        super(itemView);
        //findViewById 메소드는 id값을 이용해 특정 뷰를 받아와주는 메소드로, 액티비티, 프래그먼트, 뷰홀더 등에서 다양하게 사용이 되곤 했다.
        dayOfMonth = itemView.findViewById(R.id.cellDayText);   //TextView를 받아서 dayOfMonth에 초기화한다.
        this.onItemListener = onItemListener;           // 생성자로 받은 클릭리스너를 클래스의 onItemListener로 초기화.
        itemView.setOnClickListener(this);              // setOnClickListener 버튼 클릭 시 수행할 동작 지정하기
    }

    @Override
    public void onClick(View view)
    {
        // CallBack 이란 내가 ListView의 한 Row(행)을 클릭했을때,
        //시스템에서 내가 클릭한 것에 대한 반응을 다시 나에게 알려주는 것을 말한다.
        //쉽게 말해 "내가 클릭하면, 시스템이 그에 대한 반응을 해준다" 라고 말할 수 있다.

        //onItemClick() 이라는 메소드는 ListView(리스트뷰)의 한 항목이 클릭 되었을때, 콜백되어 호출 된다.

        //  item 클릭시 callback을 받기 위해서 viewholder에 itemView.setOnClickListener(this); 생성
        //  아이탬의 위치를 알기 위해서 어댑터의포지션과 아이탬의 날짜를 클릭리스너의 매개변수로 넣어준다.
        onItemListener.onItemClick(getAdapterPosition(), (String) dayOfMonth.getText());
    }
}
