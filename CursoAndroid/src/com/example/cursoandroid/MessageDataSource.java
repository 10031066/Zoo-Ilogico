package com.example.cursoandroid;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MessageDataSource {
  
	private SMSDbHelper helper;
	private SQLiteDatabase db;
	private Context ctx;
	public MessageDataSource(Context context){
		
		helper= new SMSDbHelper(context);
		ctx=context;
	}
	
	public void oper(){
		db=helper.getWritableDatabase();
	}
	
	public void close(){
		db.close();
	}
	
	public Message createMessage(String number_source, String text, String date){
		db.execSQL("INSERT INTO "+SMSDbHelper.TABLE_NAME+" ("+SMSDbHelper.COLUMN_NUMBER_SOURCE+","+SMSDbHelper.COLUMN_TEXT+", "+SMSDbHelper.COLUMN_DATE+") VALUES ('4611892364','OLA K DICE',datetime())");
		Message messageInserted = new Message (number_source,text);   
		return messageInserted;
	}
	
	public ArrayList<Message> getAllMessage(){
		Cursor cursor =db.rawQuery("SELECT * from "+SMSDbHelper.TABLE_NAME,null);
		ArrayList<Message> Lista = new ArrayList<Message>();
		Message temp = null;
		while(cursor.moveToNext()){
			temp= new Message(cursor.getString(1), cursor.getString(2));
			temp.setId(cursor.getLong(0));
			temp.setDate(cursor.getString(3));
			
			Lista.add(temp);
		}
		
		return Lista;
		
	}
}
