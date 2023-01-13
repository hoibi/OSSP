package com.app.ossp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// 데이터 베이스 모델 만들기
@Entity
public class DataTable {
    //  자동으로 ID를 지정해주는 어노테이션
    // ID가 1씩 카운트 되면서 올라간다
    @PrimaryKey(autoGenerate = true)
    public int id;      // 고유 ID 값

    public String contents;

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }
}