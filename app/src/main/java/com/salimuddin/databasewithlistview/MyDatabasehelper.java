package com.salimuddin.databasewithlistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabasehelper extends SQLiteOpenHelper {

    //Declare Valuables

    //DATA BASE Name
    private static final String DATABASE_NAME = "Details.db";
    //Table name
    private static final String TABLE_NAME = "detail_table";
    //Version Name
    private static final int VERSION_NUMBER = 1;
    //Table Column id
    private static final String ID = "_Id";
    //Table column name
    private static final String Name = "Name";
    //Create Table SQl command
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Name+" VARCHAR(25));";
    //Context
    private Context context;
    //Drop Table SQL Command
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    public MyDatabasehelper(@Nullable Context context) {
        //passing contex, DATABASE NAME, Data base Version
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creating Database

        try {
            Toast.makeText(context, "Database Created", Toast.LENGTH_SHORT).show();

            db.execSQL(CREATE_TABLE);
        }catch (Exception e){
            Toast.makeText(context, "Database not Created"+e, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Database update

        try {
            Toast.makeText(context, "Database Updated", Toast.LENGTH_SHORT).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);

        }
        catch (Exception e){
            Toast.makeText(context, "Database not Updated "+e, Toast.LENGTH_SHORT).show();
        }

    }
    public long saveData(String id, String name){

        //Creating saveData

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(Name, name);
        long rowid =  db.insert(TABLE_NAME, null, contentValues);
        return rowid;

    }

    public Cursor showAllData(){

        //creating showAllData
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return cursor;
    }

    public Boolean updateData(String id, String name){

        //creating Update Data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(Name, name);
        db.update(TABLE_NAME, contentValues, ID+" = ?", new String[]{id});

        return true;
    }

    public int deleteData(String id){

        //creating deleteData

        SQLiteDatabase db = this.getWritableDatabase();
        int value = db.delete(TABLE_NAME, ID+" = ?", new String[]{id});

        return value;
    }
}
