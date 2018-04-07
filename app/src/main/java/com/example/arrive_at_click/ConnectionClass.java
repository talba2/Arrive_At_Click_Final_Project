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
import  com.example.arrive_at_click.*;
import javax.sql.RowSetReader;
import javax.sql.RowSetWriter;

public class ConnectionClass {

    String ip,db,un,password;
    String classs = "ConnectionClass";

    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {

            Class.forName(classs);
            ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + db + ";user=" + un + ";password="
                    + password + ";";
            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            Log.e("ERROR", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERROR", e.getMessage());
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }

        return conn;
    }


    public String[]  read(SQLInput stream, String typeName,int numOfRows)
    {

        String[] arr = new String[numOfRows];
    }

    public int CountRecords(String table, String term)
    {
        int count = 0;
        Connection conn = null;
        try {

            String myIp= "10.0.0.7";
            String myDB = "Final_Project";
            String username = "sapirbu";
            String pass= "";
            String driver = "net.sourceforge.jtds.jdbc.Driver";
            Class.forName(driver).newInstance();
            String connString ="jdbc:jtds:sqlserver://"+myIp+";"+"databaseName="+myDB+";user="+username+";password="+pass+";";

            conn = DriverManager.getConnection(connString);
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
