package com.example.tanguy.testimportdatabasesqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.example.tanguy.testimportdatabasesqlite.database.DataBaseHelper;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    private DataBaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create database handler
        mDbHelper = new DataBaseHelper(this.getBaseContext());
        try {
            mDbHelper.createDataBase();
        } catch (IOException mIOException) {
            Log.e("error", mIOException.toString() + "  UnableToCreateDatabase");
        }

        //open database
        mDbHelper.openDataBase();
        mDbHelper.close();
        mDb = mDbHelper.getReadableDatabase();

        //try to get data
        String sql ="SELECT * FROM Client";

        Cursor mCur = mDb.rawQuery(sql, null);
        if (mCur!=null)
        {
            Log.d("tables columns name", mCur.getColumnNames().toString());
            int tupleNbr = 1;
            while(mCur.moveToNext()) {
                Log.d("Tuple number", Integer.toString(tupleNbr));
                for(int i = 0; i < mCur.getColumnCount()-1; i++){
                    Log.d("Column", mCur.getString(i));
                }
            }
        }

        mDbHelper.close();
    }

}
