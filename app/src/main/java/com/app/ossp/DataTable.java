package com.app.ossp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class DataTable {
    //자동으로 ID를 지정해주는 어노테이션
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String contents;

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }
}