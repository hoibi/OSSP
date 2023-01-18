package com.app.ossp;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//지금까지 기록하였던 메모들을 관리할 수 있는 클래스.
public class MemoActivity extends AppCompatActivity {
    private List<DataTable> dList;      // roomdB에 저장되어있는 메모들을 담을 변수.
    private ArrayList<String> aList;        //dList를 필터링하여 가공한 데이터를 담을 변수.
    private DataBase db;
    private MemoAdapter mAdapter;
    private RecyclerView memoRecyclerview;
    private ImageButton ibtClear;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        db = DataBase.getAppDatabase(this);
        memoRecyclerview = findViewById(R.id.re_memo_activity);
        ibtClear = findViewById(R.id.imb_clear);

        // 초기화버튼을 누를 시 현재까지 담겨있단 roomdb의 데이터가 전부 초기화 된다.
        // 마찬가지로 데이터 가공에 대한 작업은 별도의 쓰레드를 만들어 진행한다.
        ibtClear.setOnClickListener(v -> {
            DeleteItems clearData = new DeleteItems();
            Thread c = new Thread(clearData);
            c.start();
        });

        fetch();        // 현재 화면에 들어왔을때 데이터를 불러오는 메서드.
    }

    // 현재 화면에 들어왔을때 데이터를 불러오는 메서드.
    private void fetch() {
        Fetch getData = new Fetch();
        Thread f = new Thread(getData);
        f.start();
    }

    class Fetch implements Runnable {
        @Override
        public void run() {
            dList = db.dataDao().getAll();
            aList = new ArrayList<>();
            for (int i = 0; i < dList.size(); i++) {        // roomdb에 담겨있던 데이터들을 하나하나 aList에 담는다
                aList.add(dList.get(i).contents);
            }

            //( 중요 ) 여기는 데이터를 가공하는 쓰레드였지만 화면변경(ui)에 대한 작업은 진행할 수 없다. 그렇기 때문에 다시 쓰레드를 만들어 ui작업에 관여한다.
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAdapter = new MemoAdapter(aList);      //어댑터의 초기화를 진행한다 변수로는 Roomdb의 담겨있던 memo의 list를 넘긴다.
                    memoRecyclerview.setAdapter(mAdapter);      //리사이클러뷰의 어댑터를 직접 연결한다.
                    memoRecyclerview.setLayoutManager(new LinearLayoutManager(MemoActivity.this));          // 화면에 리사이클러뷰의 속성을 정의한다 기본적으로 리니어로 할경우 수직으로 데이터가 그려진다.
                }
            });
        }
    }

    //rooMdb의 데이터를 지울 때 사용하는 클래스이자 쓰레드이다.
    class DeleteItems implements Runnable {
        @Override
        public void run() {
            db.dataDao().deleteAll();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAdapter.clearMemo();
                }
            });
        }
    }
}
