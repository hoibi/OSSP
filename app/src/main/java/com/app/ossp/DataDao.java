package com.app.ossp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataDao {

    //데이터를 roomdb에 삽입하기위한 메서드
    @Insert
    void insert(DataTable dataTable);

    //roomdb에서 데이터를 삭제하기 위한 메서드
    @Query("DELETE FROM DataTable")
    void deleteAll();

    //roomdb에서 데이터를 전체다 불러올 수 있는 쿼리문사용
    @Query("SELECT * FROM DataTable")
    List<DataTable> getAll();

}




















