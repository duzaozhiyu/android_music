package com.example.sql;

import com.example.activity.IConstant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MusicDB extends SQLiteOpenHelper implements IConstant{

	
	
	
	public MusicDB(Context context) {
		super(context,"MyandroidMC",null,1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table "
				+ TABLE_SING
				+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " TP_ID_S integer, S_NAME TEXT, ALBUM TEXT, S_PATH TEXT, "
				+ "ARTIST TEXT, TP_ID_W integer, FAVORITE integer,TIME integer)");
		db.execSQL("create table "
				+ TABLE_SING_WORD
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "TP_ID_W integer, W_PATH TEXT, W_NAME TEXT)");
		db.execSQL("create table "
				+ TABLE_LIST
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, TP_ID_L integer, L_NAME nchar,TP_ID_S  integer)");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldarg1, int newarg2) {
		// TODO Auto-generated method stub
		if(oldarg1<newarg2)
		{
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SING);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SING_WORD);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST);
		onCreate(db);
		}
	}

}
