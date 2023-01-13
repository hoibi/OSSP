package com.app.ossp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * 데이터 베이스는 여러곳에서 사용되므로 메모리 낭비를 막기위해서 싱글톤 객체로 생성함.
 * 데이터 베이스 사용은 되도록 비동기에서 실행을 권장.
 */

@Database(entities = {DataTable.class}, version = 1)
public abstract class DataBase extends RoomDatabase {
    private static DataBase INSTANCE = null;        // 데이터베이스변수

    public abstract DataDao dataDao();              // interface로 선언된 Dao를 사용해 데이터베이스에 접근하기 위해 사용

    public synchronized static DataBase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            //  데이터 베이스 삽입
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DataBase.class, "calendar_db")
                    .fallbackToDestructiveMigration()       // 스키마(Database) 버전 변경 가능
                    .build();
        }
        return INSTANCE;
    }
}




























