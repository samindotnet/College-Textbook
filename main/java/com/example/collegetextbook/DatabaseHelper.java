package com.example.collegetextbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DatabaseName ="ProjectDb";
    public static final String TableName ="Books";
    public static final String Column1 ="BookID";
    public static final String Column2 ="Title";
    public static final String Column3 ="Author";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DatabaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table "+TableName+"(BookID Integer Primary Key AutoIncrement,Title Text, Author Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TableName);
        onCreate(db);
    }
    public boolean InsertData(String Title, String Author){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column2,Title);
        contentValues.put(Column3,Author);
        long result = db.insert(TableName,null,contentValues);
        if(result ==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean UpdateData(String BookID,String Title,String Author){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column1,BookID);
        contentValues.put(Column2,Title);
        contentValues.put(Column3,Author);
        db.update(TableName,contentValues,"BookID=?",new String[]{BookID});
        return true;
    }
    public Integer DeleteData(String BookID){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TableName,"BookID=?",new String[] {BookID});
    }
    public Cursor ShowData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor acura = db.rawQuery(" select * from "+TableName,null);
        return acura;
    }
}
