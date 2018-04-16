package com.example.arrive_at_click;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.icu.util.Calendar;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class SqlClass {

    public final class SampleDBContract {
        private SampleDBContract() {
        }

        public  class Employer implements BaseColumns {
            public static final String TABLE_NAME = "employer";
            public static final String COLUMN_NAME = "name";
            public static final String COLUMN_DESCRIPTION = "description";
            public static final String COLUMN_FOUNDED_DATE = "date";

            public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                    TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_FOUNDED_DATE + " INTEGER" + ")";
        }
    }


    public class SampleDBSQLiteHelper extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "sample_database";

        public SampleDBSQLiteHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(SampleDBContract.Employer.CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SampleDBContract.Employer.TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }
/*
    private void saveToDB() {
        SQLiteDatabase database = new SampleDBSQLiteHelper(this).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SampleDBContract.Employer.COLUMN_NAME, binding.nameEditText.getText().toString());
        values.put(SampleDBContract.Employer.COLUMN_DESCRIPTION, binding.descEditText.getText().toString());

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime((new SimpleDateFormat("dd/MM/yyyy")).parse(
                    binding.foundedEditText.getText().toString()));
            long date = calendar.getTimeInMillis();
            values.put(SampleDBContract.Employer.COLUMN_FOUNDED_DATE, date);
        }
        catch (Exception e) {
            Log.e(TAG, "Error", e);
            Toast.makeText(this, "Date is in the wrong format", Toast.LENGTH_LONG).show();
            return;
        }
        long newRowId = database.insert(SampleDBContract.Employer.TABLE_NAME, null, values);

        Toast.makeText(this, "The new Row Id is " + newRowId, Toast.LENGTH_LONG).show();
    }
*/

}
