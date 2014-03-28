package com.example.cursoandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SMSDbHelper extends SQLiteOpenHelper{
	//Nombres de las columnas como constantes 
	public static final String TABLE_NAME="messages";
	public static final String COLUMN_ID="id";
	public static final String COLUMN_NUMBER_SOURCE="number_source";
	public static final String COLUMN_TEXT="text";
	public static final String COLUMN_DATE="date";
	
	public static final String DATABASE_NAME="messages.db";
	public static final int DATABASE_VERSION=1;
	
	public SMSDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("curso","se creo la tabla messages");
	    db.execSQL("CREATE TABLE "+ this.TABLE_NAME+"("+COLUMN_ID+" integer primary key autoincrement, "
	    		   + COLUMN_NUMBER_SOURCE+" text not null,"+COLUMN_TEXT+" text not null, "+COLUMN_DATE+" text not null);"
	    		);
	    
	    db.execSQL("INSERT INTO "+TABLE_NAME+" ("+COLUMN_NUMBER_SOURCE+" , "+COLUMN_TEXT+" , "+COLUMN_DATE+" ) VALUES('4611892364','OLA K DICE',datetime())");
	    db.execSQL("INSERT INTO "+TABLE_NAME+" ("+COLUMN_NUMBER_SOURCE+" ,"+COLUMN_TEXT+", "+COLUMN_DATE+") VALUES('1234','Sup bro!',datetime())");
	    db.execSQL("INSERT INTO "+TABLE_NAME+" ("+COLUMN_NUMBER_SOURCE+" ,"+COLUMN_TEXT+", "+COLUMN_DATE+") VALUES('5678','No!',datetime())");
	    db.execSQL("INSERT INTO "+TABLE_NAME+" ("+COLUMN_NUMBER_SOURCE+" ,"+COLUMN_TEXT+", "+COLUMN_DATE+") VALUES('2334','OMFG',datetime())");
	    db.execSQL("INSERT INTO "+TABLE_NAME+" ("+COLUMN_NUMBER_SOURCE+" ,"+COLUMN_TEXT+", "+COLUMN_DATE+") VALUES('5556','OMW',datetime())");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	db.execSQL("drop table if exist"+TABLE_NAME);
	onCreate(db);
		
	}

	
}
