package com.app.ossp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;



// room db(앱 내부 데이터 베이스)
// Room은 스마트폰 내장 DB에 데이터를 저장하기 위해 사용하는 라이브러리이다.
// 기존의 sqLite라는 android에 기본 내장 방식의 sqLite의 업그레드 격
// 기존의 sqLite에 비해 좀 더 간편하고 쉽게 만들어서 안드로이드 앱상에 내부 db를 구축해서 여러가지 데이터를 관리할 수  있도록

// 실질적으로 삽입이나 또는 삭제, 조회, 업데이트, 수정 이런것들(행위)를 할 인터페이스를 만들어야 합니다.
// 그것을 가능하게 하는 것이 Dao(data access object) 객체

// room dao 인더페이스 명시하기 위해 작성
@Dao
public interface DataDao {

    //데이터를 roomdb에 삽입하기위한 메서드
    @Insert
    void insert(DataTable dataTable);


    // DataBase에다가 요청하는 명령
    // roomdb에서 데이터를 삭제하기 위한 메서드
    @Query("DELETE FROM DataTable")
    void deleteAll();

    // roomdb에서 데이터를 전체다 불러올 수 있는 쿼리문사용
    // DataTable로부터 SELECT(조회) *:전체 컬럼을
    @Query("SELECT * FROM DataTable")
    List<DataTable> getAll();

}




















