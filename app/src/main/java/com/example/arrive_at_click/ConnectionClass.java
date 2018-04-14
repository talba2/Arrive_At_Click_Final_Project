package com.example.arrive_at_click;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import java.sql.Statement;
import java.util.NoSuchElementException;

import javax.sql.RowSetReader;
import javax.sql.RowSetWriter;

public class ConnectionClass {

    String ip,db,un,password;
    static String connString;
    //private Connection con;

    @SuppressLint("NewApi")
    public ConnectionClass(){
        /*StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);*/
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
        //init();
    }

    public  void init()
    {
        //connString = talConn();
        connString=sapirConn();
    }

    @SuppressLint("NewApi")
    public Connection openConn()
    {
        Connection conn=null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            String driver = "net.sourceforge.jtds.jdbc.Driver";
            Class.forName(driver).newInstance();
            //connString=talConn();
            connString=sapirConn();
            conn = DriverManager.getConnection(connString);
            Log.w("Connection","open");
        }
        catch (SQLException se) {
            Log.e("ERROR", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERROR", e.getMessage());
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
        return conn;
    }

    private String sapirConn()
    {
        //ip="10.0.0.7";
        //db="Final_Project";
        //un="sapirbu";
        //password="";
        return "jdbc:sqlserver://localhost:1433;user=sapirSql;password=sapir123;";
    }

    private String talConn()
    {
        ip="localhost";
        db="Arrive_At_Click";
        //un="talba2";
        //db="DESKTOP-7AT3KOA\\TAL";
        //password="talmusai147";
        //return "jdbc:jtds:sqlserver://"+ip +";"+"databaseName="+ db +";"+"integratedSecurity=true;";
        //return "jdbc:jtds:sqlserver://"+ip +"/"+ db +";user="+un+";password="+password+";instance="+"TAL";
        return "jdbc:jtds:sqlserver://localhost;user=talSql;password=tal1234;";
    }

    /*
    public void closeConn()
    {
        try {
            con.close(); }

        catch (Exception e) {
            Log.w("Error connection", "" + e.getMessage());
        }
    }*/

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

            Connection conn=openConn();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select " + select + " from " + table + " where" + where );

            conn.close();
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
            Connection conn=openConn();
            Statement stmt = conn.createStatement();
            ResultSet reset = stmt.executeQuery("select * from " + table + " where" + term );

            while (reset.next()) {
                count++;
            }

           conn.close();

        } catch (Exception e) {
            Log.w("Error connection", "" + e.getMessage());
        }

        return count;
    }
}

