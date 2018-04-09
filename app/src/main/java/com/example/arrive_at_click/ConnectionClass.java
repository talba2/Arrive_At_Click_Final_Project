package com.example.arrive_at_click;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.lang.Object;
import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import java.lang.Object.*;
import java.sql.Statement;
import java.util.NoSuchElementException;

import  com.example.arrive_at_click.*;
import javax.sql.RowSetReader;
import javax.sql.RowSetWriter;

public class ConnectionClass {

    String ip,db,un,password;
    String classs = "ConnectionClass";
    static String connString;
    //private static ConnectionClass obj;
    private Connection con;

    @SuppressLint("NewApi")
    public ConnectionClass(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //Connection conn = null;
        //String ConnURL = null;
        /*try {

            Class.forName(classs);
            ConnURL = talConn();
            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            Log.e("ERROR", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERROR", e.getMessage());
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }

        return conn;
        */
        init();
    }

    public  void init()
    {
        connString = talConn();
        //connString=sapirConn();
    }

    /*
    public  ConnectionClass Check()
    {
        if (obj == null)
            obj = new ConnectionClass();
        return obj;
    }*/

    public  void openConn()
    {
        con=null;
        try {
            String driver = "net.sourceforge.jtds.jdbc.Driver";
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(connString);
        }

     catch (Exception e)
        {
        Log.w("Error connection", "" + e.getMessage());
        }

    }

    public void closeConn()
    {
        try {
            con.close();
        }

        catch (Exception e)
        {
            Log.w("Error connection", "" + e.getMessage());
        }

    }

    public String[] read(SQLInput stream, String typeName,int numOfRows)
    {

        String[] arr = new String[numOfRows];

        //*****need to continue here******//

        return arr;
    }

    public ResultSet getTable(String select, String table, String where)
    {
        ResultSet rs=null;
        try {

            openConn();
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("select " + select + " from " + table + " where" + where );

            closeConn();
        }
        catch (Exception e) {
            Log.w("Error connection", "" + e.getMessage());
        }

        return rs;
    }

    public int CountRecords(String table, String term) throws NoSuchElementException
    {
        int count = 0;
        try {

            openConn();
           // String driver = "net.sourceforge.jtds.jdbc.Driver";
            //Class.forName(driver).newInstance();
            //String connString =talConn();

           // conn = DriverManager.getConnection(connString);
            Statement stmt = con.createStatement();
            ResultSet reset = stmt.executeQuery("select * from " + table + " where" + term );

            while (reset.next()) {
                count++;
            }

           closeConn();


        } catch (Exception e) {
            Log.w("Error connection", "" + e.getMessage());
        }

        return count;
    }

    private String sapirConn()
    {
        ip="10.0.0.7";
        db="Final_Project";
        un="sapirbu";
        password="";
        return "jdbc:jtds:sqlserver://"+ip+";"+"databaseName="+db+";user="+un+";password="+password+";";
    }

    private String talConn()
    {
        ip="192.168.1.2";
        db="Arrive_At_Click";
        un="talba2";
        password="talmusai147";
        String serverName="DESKTOP-7AT3KOA\\TAL";
        return "jdbc:jtds:sqlserver://"+ip+";"+"databaseName="+db+";user="+un+";password="+password+";";
    }

}
