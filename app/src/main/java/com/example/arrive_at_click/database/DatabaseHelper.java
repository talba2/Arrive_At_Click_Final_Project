package com.example.arrive_at_click.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import com.example.arrive_at_click.model.Facilities;
import com.example.arrive_at_click.model.Opinion;
import com.example.arrive_at_click.model.Site;
import com.example.arrive_at_click.model.Users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME="sqLite14.0.db";
    public static final String DBLOCATION="/data/data/com.example.arrive_at_click/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private int version;

    public DatabaseHelper(Context context){
        super(context,DBNAME,null,3);
        this.mContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    private void openDatabase() {
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

    public ArrayList<Site> getListSites(String select, String from, String where) {
        Site site = null;
        ArrayList<Site> siteList = new ArrayList<>();
        openDatabase();
        Cursor cursor;
        if (where == null)
            cursor = mDatabase.rawQuery("SELECT " + select + " FROM "+ from, null);
        else
            cursor = mDatabase.rawQuery("SELECT " + select + " FROM "+from+" WHERE " + where, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            site = new Site(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(6), cursor.getInt(7), cursor.getInt(8), cursor.getFloat(9), cursor.getFloat(10));
            siteList.add(site);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return siteList;
    }

    public ArrayList<Opinion> getListOpinions(String select, String where) {
        if(numOfRows("Opinion",where)==0)
            return null;
        else
        {
            Opinion opinion = null;
            ArrayList<Opinion> opinionList = new ArrayList<>();
            openDatabase();
            Cursor cursor;
            if (where==null)
                cursor = mDatabase.rawQuery("SELECT " + select + " FROM Opinion", null);
            else
                cursor = mDatabase.rawQuery("SELECT " + select + " FROM Opinion WHERE " + where, null);

            cursor.moveToFirst();
            if(select.equals("*"))
            {
                while (!cursor.isAfterLast()) {
                    opinion = new Opinion(cursor.getString(5), cursor.getInt(1), cursor.getString(2), cursor.getInt(3),cursor.getString(4));
                    opinionList.add(opinion);
                    cursor.moveToNext();
                }
            }
            else
            {
                while (!cursor.isAfterLast()) {
                    opinion = new Opinion(cursor.getString(0), cursor.getString(1), cursor.getInt(2),cursor.getString(3));
                    opinionList.add(opinion);
                    cursor.moveToNext();
                }
            }

            cursor.close();
            closeDatabase();
            return opinionList;
        }
    }

    public ArrayList<Facilities> getListFacilities(String select, String where) {
        Facilities facilities = null;
        ArrayList<Facilities> facilitiesList = new ArrayList<>();
        openDatabase();
        Cursor cursor;
        if (where == null)
            cursor = mDatabase.rawQuery("SELECT " + select + " FROM Facilities", null);
        else
            cursor = mDatabase.rawQuery("SELECT " + select + " FROM Facilities WHERE " + where, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            facilities = new Facilities(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4),cursor.getInt(5),cursor.getInt(6),cursor.getInt(7));
            facilitiesList.add(facilities);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return facilitiesList;
    }

    public ArrayList<Users> getListUsers(String select, String where) {
        Users user = null;
        ArrayList<Users> usersList = new ArrayList<>();
        openDatabase();
        Cursor cursor;
        if (where == null)
            cursor = mDatabase.rawQuery("SELECT " + select + " FROM Users", null);
        else
            cursor = mDatabase.rawQuery("SELECT " + select + " FROM Users WHERE " + where, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            user = new Users(cursor.getString(0),cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getInt(4),cursor.getInt(5),cursor.getInt(6));
            usersList.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return usersList;
    }

    public int numOfRows(String table, String where)
    {
        openDatabase();
        Cursor cursor=null;
        int count=0;
        if(where==null)
            cursor= mDatabase.rawQuery("SELECT Count(*) FROM " + table, null);
        else
            cursor= mDatabase.rawQuery("SELECT Count(*) FROM " + table + " WHERE " + where, null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            count=cursor.getInt(0);
            cursor.close();
        }
        closeDatabase();
        return count;
    }

    public long insertValues(String table,ContentValues cv)
    {
        openDatabase();
        long added=mDatabase.insert(table,null,cv);
        closeDatabase();
        return added;
    }

    public int sumColumn(String query)
    {
        openDatabase();
        Cursor cursor = mDatabase.rawQuery(query, null);
        int total=0;
        if (cursor.moveToFirst())
            total = cursor.getInt(cursor.getColumnIndex("Total"));// get final total
        closeDatabase();
        return total;
    }

    public boolean update(ContentValues cv,String table,String where)
    {
        openDatabase();
        int affectedRows=mDatabase.update(table, cv, where, null);
        closeDatabase();
        return affectedRows>0;
    }

    public boolean delete(String table,String where)
    {
        openDatabase();
        int affectedRows=mDatabase.delete(table,where, null);
        closeDatabase();
        return affectedRows>0;
    }
}
