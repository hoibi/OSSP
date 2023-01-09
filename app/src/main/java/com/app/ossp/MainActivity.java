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

//액티비티 컴포넌트는 이름과 같이 사용자가 어떠한 활동을 할 때 실행되는 컴포넌트이다.
//UI를 제공하여 사용자와 상호작용을 하며, class에서 activity를 상속받아 사용할 수 있다
//
//AppCompatActivity
//액티비티는 여러 종류가 존재. 상속의 구조를 가진다
//최종적으로 AppCompatActivity까지 자식을 가지고 있다.
//AppCompatActivity는 크게 다음과 같은 3가지 특징을 가지고 있습니다.
//
//setSupportActionBar(Toolbar) API를 사용하여 action item, navigation mode 등을 포함하는 action bar를 지원합니다.
//Theme.AppCompat.DayNight 테마를 사용하며 AppCompatDelegate.setDefaultNightMode(int) API를 사용하여 다크 모드를 지원합니다.
//getDrawerToggleDelegate() API를 사용하여 DrawerLayout과 통합합니다.
//⚠️ 이 클래스를 확장하는 모든 액티비티는 AppCompat 또는 해당 테마를 확장하는 테마를 사용해야 합니다.
public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {
    private String TAG = "##HH";
    private TextView monthYearText;                 //TextView는 안드로이드 UI를 구성함에 있어 화면에 텍스트를 표시하는 기능을 담당
    //텍스트와 연관된 기능을 포함하는 Button 또는 EditText의 부모 클래스이기도 합니다.

    private RecyclerView calendarRecyclerView;      //리사이클러뷰(RecyclerView)는 "사용자가 관리하는 많은 수의 데이터 집합(Data Set)을 개별 아이템 단위로 구성하여
    // 화면에 출력하는 뷰그룹(ViewGroup)이며,
    // 한 화면에 표시되기 힘든 많은 수의 데이터를 스크롤 가능한 리스트로 표시해주는 위젯"입니다.

    private LocalDate selectedDate;                 // 날짜만 표시합니다. 형식은 YYYY-MM-DD 입니다!


    //onCreate()는 콜백 메소드이며 Activity의 생명주기에서 생성 단계에 한번 실행되는 메소드이다.
    //'savedInstanceState'라는 파라메터를 수신하며 이 파라메터는 Activity의 이전 상태를 저장한 'Bundle'객체입니다.
    //Activity가 처음 생성된 경우에 savedInstancdState는 null 상태이다.
    //생성단계에서 onCreate()안에서 setContentView()에 레이아웃을 전달하여 화면을 정의한다.
    //ex) setContentView(R.layout.main_activity)
    @Override
    protected void onCreate(Bundle savedInstanceState) {    //Activity가 생성될 때 화면 정의하는 용도로 많이 사용
        // onCreate()가 완료되면 onStart()가 호출됨
        super.onCreate(savedInstanceState);                 // super class 호출(Activity를 구현하는데 필요한 과정)
        setContentView(R.layout.activity_main);

        initWidgets();                                      //calendar에 사용되는 view들을 findViewById 통해서 초기화 진행하는 메서드

        //안드로이드 내에서 날짜/시간 관련한 메서드
        //LocalDate, LocalTime, LocalDateTime 이 세 가지가 있습니다.
        // 이들은 자바 언어를 쓰는 환경에서는 다 통용됩니다! 왜냐면 java.time 이라는 패키지에 들어있기 때문이죠.
        //LocalTime
        // 시간만 표시합니다. 형식은 HH:mm:ss 입니다!
        //LocalDate
        // 날짜만 표시합니다. 형식은 YYYY-MM-DD 입니다!
        //LocalDateTime
        // 위 두개의 짬뽕입니다. 형식은 YYYY-MM-DDTHH:mm:ss 입니다! (공백을 T로 표기하는군요!)
        selectedDate = LocalDate.now();                     // 로컬 컴퓨터의 현재 날짜 정보를 저장한 LocalDate 객체를 리턴
        setMonthView();
    }

    /**
     * calendar에 사용되는 view들을 findViewById 통해서 초기화 진행하는 메서드
     * 기본적으로 findViewById 메소드는 id값을 이용해 특정 뷰를 받아와주는 메소드로, 액티비티, 프래그먼트, 뷰홀더 등에서 다양하게 사용이 되곤 했다.
     */
    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView); //
        monthYearText = findViewById(R.id.monthYearTV);
    }

    /**
     * 현재 view에 보이는 month에 따라서 그 month의 일 수를 화면에 뿌려주는 메서드
     *
     * 뷰(View)
     * 뷰는 안드로이드 기본 화면을 구성하는 모든 기본 화면의 구성요소이다.
     */
    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));                 // month 부분에 현재 month를 반환후 TextView에 적용
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);         // daysInMonth에 daysInMonthArray(selectedDate)를 통해서 현재 month의 일 수를 리턴받아 저장

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);        // 화면에 보일 calendar(=recyclerview)의 Adapter(현재 month의 일 수들, 클릭리스너)를 설정.
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);       // calendar(=recyclerview)의 규격을 정함. spanCount = 일주일 이므로 7을 설정.
        calendarRecyclerView.setLayoutManager(layoutManager);                   // recyclerview 의 layoutManager 연결
        calendarRecyclerView.setAdapter(calendarAdapter);                       // recyclerview 의 adapter 연결
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
        YearMonth yearMonth = YearMonth.from(date);                 // 해당 월의 일수, 해당 월의 마지막 날을 구할 때 사용

        int daysInMonth = yearMonth.lengthOfMonth();                // 해당 월의 총 일수를 저장

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);     // 날짜만 표시 -> 해당 월의 첫날
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();        // 한 주에서 날짜 뽑기 getDayofWeek() 는 요일을 가져온다 getValue()는 요일에서 날자를 가져온다

        // i는 일 수
        for (int i = 1; i <= 42; i++) {                                 // 요일이 1: 월 ~ 7: 일 이런식으로 숫자화 되어 있음
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {        // 월의 시작요일 보다 i의 값이 작으면 값을 넣지 않는다. 시작 요일이 1일이여야 하니까
                // 그날의 일수를 넘어가면 넣지 않는다
                daysInMonthArray.add("");
            } else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    /**
     * @param date
     * @return
     *
     * 지정된 패턴을 사용하여 날짜, 시간 개체를 처리하도록 도와주는 포맷터(Formatter)
     *
     */
    private String monthYearFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");         // 날짜 형식을 yyyy-mm-dd에서 MMMM yyyy로 변경
        return date.format(formatter);
    }

    /**
     * 이전 달을 보기 위한 메서드
     * @param view
     */
    public void previousMonthAction(View view) {
        selectedDate = selectedDate.minusMonths(1);         // 이전 달의 시작 일
        setMonthView();
    }

    /**
     * 다음 달을 보기 위한 메서드
     * @param view
     */
    public void nextMonthAction(View view) {
        selectedDate = selectedDate.plusMonths(1);          // 다음달의 첫 날
        setMonthView();
    }

    /**
     *
     * 아이템을 클릭시 클릭한 날짜를 보여주는 onClick
     * @param position
     * @param dayText
     */
    @Override
    public void onItemClick(int position, String dayText) {         // 클릭한 위치와 텍스트 내용
        if (!dayText.equals("")) {
            String message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate);            // message 형식의 텍스트 출력
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();        //토스트(Toast)는 사용자에게 짧은 메시지 형식으로 정보를 전달하는 팝업을 의미합니다.
            // 메시지를 표시할 공간만 차지하고 시간이 지나면 자동으로 사라지는 메시지입니다.
            //makeText() 함수를 통하여 Toast 객체를 생성
            //첫 번째 인자는 현재 프로세스의 Context 정보
            //두 번째 인자는 Toast 메시지로 사용자에게 보여줄 문자열을 넘겨줍니다
            //세 번째 길게 Toast 메시지를 표시합니다.
        }
    }
}
