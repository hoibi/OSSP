package com.app.ossp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        selectedDate = LocalDate.now();     // 로컬 컴퓨터의 현재 날짜 정보를 저장한 LocalDate 객체를 리턴
        setMonthView();
    }

    /**
     * calendar에 사용되는 view들을 findViewById 통해서 초기화 진행하는 메서드
     */
    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    /**
     * 현재 view에 보이는 month에 따라서 그 month의 일 수를 화면에 뿌려주는 메서드
     */
    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));         // month 부분에 현재 month를 반환후 TextView에 적용
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);         // daysInMonth에 daysInMonthArray(selectedDate)를 통해서 현재 month의 일 수를 리턴받아 저장

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);        // 화면에 보일 calendar(=recyclerview)의 Adapter(현재 month의 일 수들, 클릭리스너)를 설정.
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);       // calendar(=recyclerview)의 규격을 정함. spanCount = 일주일 이므로 7을 설정.
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    /**
     * @param date 는 현재 날짜를 받는다.
     * @return
     *
     * 각 Month의 일 수를 리턴 해주는 메서드
     *
     * ex) 2022년 12월의 일 수
     * -> [, , , , 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, , , , , , , ]
     */
    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i <= 42; i++) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("");
            } else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    private String monthYearFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    public void previousMonthAction(View view) {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view) {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText) {
        if (!dayText.equals("")) {
            String message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }
}
