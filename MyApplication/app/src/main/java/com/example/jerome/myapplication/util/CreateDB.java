package com.example.jerome.myapplication.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alan on 2016/8/26.
 */
public class CreateDB extends SQLiteOpenHelper{

    public static final String CREATE_CONTRACT="create table Contract ("
            +"id integer primary key autoincrement, "
            +"user_name text, "
            +"img BLOB, "
            +"content text)";

    public CreateDB(Context context, String name, SQLiteDatabase.CursorFactory factory,int version)
    {
        super(context,name,factory,version);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_CONTRACT);
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
    {
        db.execSQL("drop table if exists Contract");
        onCreate(db);
    }

}
