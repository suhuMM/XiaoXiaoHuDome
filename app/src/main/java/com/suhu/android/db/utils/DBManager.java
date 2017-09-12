package com.suhu.android.db.utils;

import android.database.sqlite.SQLiteDatabase;

import com.suhu.android.application.SoftwareApp;

/**
 * @author suhu
 * @data 2017/9/12.
 * @description
 */

public class DBManager {
    private static DBManager manager;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    private DBManager(){
        dbHelper = DBHelper.getInstance(SoftwareApp.getInstance());
        if (db == null){
            db = dbHelper.getWritableDatabase();
        }
    }

    public static synchronized DBManager getInstance(){
        if (manager == null){
            manager = new DBManager();
        }
        return manager;
    }

    public SQLiteDatabase getDataBase(){
        return db;
    }


}
