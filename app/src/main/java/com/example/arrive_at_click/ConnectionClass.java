package com.example.arrive_at_click;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.Context;
import android.util.Log;

import com.example.arrive_at_click.database.DatabaseHelper;

public class ConnectionClass {

    public static DatabaseHelper DBHelper;

    public boolean copyDatabase(Context context)
    {
        try{
            InputStream inputStream=context.getAssets().open(DatabaseHelper.DBNAME);
            String OutFileName=DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream=new FileOutputStream(OutFileName);
            byte[]buff=new byte[1024];
            int length=0;
            while((length=inputStream.read(buff))>0)
                outputStream.write(buff,0,length);
            outputStream.flush();
            outputStream.close();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

