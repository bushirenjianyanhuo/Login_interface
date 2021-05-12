package com.example.logininterface.database.tool;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyLibrary extends SQLiteOpenHelper {

    public MyLibrary(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //数据库初始化
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE table userInformation(\n" +
                "    accounts Text not null ,\n" +
                "    password Text not null \n" +
                ")";

        db.execSQL(sql);
    }

    //数据库升级
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
