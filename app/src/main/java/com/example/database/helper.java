package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class helper extends SQLiteOpenHelper {
    public static final String name= "myTable";

    public helper(@Nullable Context context) {
        super(context, name,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table myTable(id INTEGER PRIMARY KEY AUTOINCREMENT ,NAME TEXT , SUPNAME TEXT,MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS myTable");
        onCreate(db);
    }
    public  boolean insertData(String name,String supname , String Marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("NAME",name);
        contentValues.put("SUPNAME",supname);
        contentValues.put("MARKS",Marks);
        long result = db.insert("myTable",null,contentValues);

        if(result == -1) {
            return false;
        }else {
            return true;
        }

    }

        public ArrayList getAllrecord(){
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
    Cursor res = db.rawQuery("select * from myTable",null);
    res.moveToFirst();
    while (res.isAfterLast()==false){
        String T1 = res.getString(0);
        String T2 = res.getString(1);
        String T3 = res.getString(2);
        String T4 = res.getString(3);

    arrayList.add(T1+" - "+ T2 +" : "+ T3 +"his marks is :"+T4 );
    res.moveToNext();
    }
return arrayList;

    }
    public boolean UPdateData(String id ,String name,String supname , String Marks){
SQLiteDatabase db = this.getWritableDatabase();
ContentValues contentValues = new ContentValues();

        contentValues.put("NAME",name);
        contentValues.put("SUPNAME",supname);
        contentValues.put("MARKS",Marks);
db.update("myTable",contentValues,"id= ?",new String[]{id});
    return true;
    }
public Integer delet(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("myTable","id= ?",new String[]{id});
}
}
