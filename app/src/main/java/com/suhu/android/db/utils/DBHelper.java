package com.suhu.android.db.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author suhu
 * @data 2017/9/12.
 * @description
 */

public class DBHelper extends SQLiteOpenHelper{

    private static DBHelper dbHelper;

    public DBHelper(Context context) {
        super(context, TabConfig.DB_NAME, null, 1);
    }

    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public static synchronized DBHelper getInstance(Context context){
        if (dbHelper==null){
            dbHelper = new DBHelper(context);
        }
        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createSportTab(sqLiteDatabase);
        createFriendTab(sqLiteDatabase);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }



    /**
     *@method 创建一个gps点的记录
     *@author suhu
     *@time 2017/9/12 14:01
     *@param sqLiteDatabase
     *
    */
    private void createSportTab(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table if not exists "+TabConfig.Sport.TAB_NAME
                        +"("
                +"_id integer primary key autoincrement,"
                +TabConfig.Sport.TIME+" verchar(20),"
                +TabConfig.Sport.LONGITUDE_LATITUDE+" verchar(20)"
                       +")"
        );
    }

    /**
     *@method 好友列表
     *@author suhu
     *@time 2017/9/15 0015 21:03
     *@param sqLiteDatabase
     *
    */
    private void createFriendTab(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(
                "create table if not exists "+TabConfig.Friend.TAB_NAME
                        +"("
                        +"_id integer primary key autoincrement,"
                        +TabConfig.Friend.USER_ID+" text,"
                        +TabConfig.Friend.NAME+" text,"
                        +TabConfig.Friend.TOKEN+" text,"
                        +TabConfig.Friend.URL+" text"
                        +")"
        );
    }

}
