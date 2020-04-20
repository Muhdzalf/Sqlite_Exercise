package com.example.sqlite_exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper  extends  SQLiteOpenHelper{

    public static final String DATABASE_NAME = "mykontak.db";
    public static final String KONTAK_TABLE_NAME = "kontak";
    public static final String KONTAK_TABLE_ID = "id";
    public static final String KONTAK_COLUMN_NAMA = "nama";
    public static final String KONTAK_COLUMN_TELEPON = "telepon";
    public static final String KONTAK_COLUMN_EMAIL = "email";
    public static final String KONTAK_COLUMN_ALAMAT = "alamat";
    private HashMap hp;


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table kontak" +
                    "(id interger primary key, nama text, telepon text, email text, alamat,text)"
        );
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS kontak");
        onCreate(db);
    }

    public boolean insertContact (String nama, String telepon, String email, String alamat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nama", nama);
        contentValues.put("telepon", telepon);
        contentValues.put("email", email);
        contentValues.put("alamat", alamat);

        db.insert("kontak", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from kontak where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, KONTAK_TABLE_NAME);
        return numRows;
    }

    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from kontak", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(KONTAK_COLUMN_NAMA)));
            res.moveToNext();
        }
        return array_list;
    }
}
