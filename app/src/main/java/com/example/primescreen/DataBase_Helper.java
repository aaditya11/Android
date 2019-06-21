package com.example.primescreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

public class DataBase_Helper extends SQLiteOpenHelper {
    public String TAG="hello";
    public DataBase_Helper(Context context){
        super(context,"test_table3",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate: here1");
        String createTable="CREATE TABLE "+"test_table3"+" (IMAGE TEXT, ITEM_ID VARCHAR PRIMARY KEY, DESCR VARCHAR, LINK VARCHAR)";
        sqLiteDatabase.execSQL(createTable);
        Log.d(TAG, "onCreate: here2");
}

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS test_table3");
        onCreate(sqLiteDatabase);

    }
    public boolean add_data(String a,String b,String c,String d){
        Log.d(TAG, "onCreate: here3");
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("ITEM_ID",a);
        contentValues.put("DESCR",b);
        contentValues.put("IMAGE",c);
        contentValues.put("LINK",d);

        long result=db.insert("test_table3",null,contentValues);
        if(result==-1) {
            Log.d("DBCHECK", "fail");
            return false;
        }
        else{
            Log.d("DBCHECK", "pass");
            return true;
            }
        }
        public Cursor getData(){
            SQLiteDatabase db=this.getWritableDatabase();
            String query="SELECT * FROM test_table3";
            Cursor data=db.rawQuery(query,null);
            return  data;
        }
    public Cursor getData_t(){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="SELECT * FROM test_table3 WHERE ITEM_ID LIKE 'T%' ";
        Cursor data=db.rawQuery(query,null);
        return  data;
    }
    public Cursor getData_w(){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="SELECT * FROM test_table3 WHERE ITEM_ID LIKE 'W%' ";
        Cursor data=db.rawQuery(query,null);
        return  data;
    }
    }

