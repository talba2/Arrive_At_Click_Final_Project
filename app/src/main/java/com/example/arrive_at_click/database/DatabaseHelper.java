package com.example.arrive_at_click.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import com.example.arrive_at_click.model.Site;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME="sqLite.db";
    public static final String DBLOCATION="/data/data/com.example.arrive_at_click/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context){
        super(context,DBNAME,null,2);
        this.mContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void openDatabase(){
        String dbPath=mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase!=null && mDatabase.isOpen()) {
            return;
        }
        mDatabase=SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase(){
        if(mDatabase!=null)
            mDatabase.close();
    }

    public ArrayList<Site> getListSites(){
        Site site=null;
        ArrayList<Site> siteList=new ArrayList<>();
        openDatabase();
        Cursor cursor= mDatabase.rawQuery("SELECT * FROM Sites WHERE name LIKE '%בנק לאומי%'",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            site=new Site(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(6),cursor.getInt(7),cursor.getInt(8),cursor.getFloat(9),cursor.getFloat(10));
            siteList.add(site);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return siteList;
    }
}