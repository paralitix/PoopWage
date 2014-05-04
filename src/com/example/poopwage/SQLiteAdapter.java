package com.example.poopwage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class SQLiteAdapter {

	 public static final String MYDATABASE_NAME = "MY_DATABASE3";
	 public static final String MYDATABASE_TABLE = "MY_TABLE";
	 public static final int MYDATABASE_VERSION = 1;
	 public static final String KEY_TIME = "Time";
	 public static final String KEY_MONEY = "Money";
	 public static final String KEY_DATE = "Date";
	
	 //create table MY_DATABASE (ID integer primary key, Content text not null);
	 private static final String SCRIPT_CREATE_DATABASE = 
	 "create table " + MYDATABASE_TABLE + " (Date varchar not null, Time varchar not null, Money varchar not null);";
	 
	 // create table MY_TABLE (Content text not null, )
	 
	 
	 private SQLiteHelper sqLiteHelper;
	 private SQLiteDatabase sqLiteDatabase;
	
	 private Context context;
	 
	 public SQLiteAdapter(Context c){
		 context = c;
	 }
	 
	 public SQLiteAdapter openToRead() throws android.database.SQLException {
		 sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
		 sqLiteDatabase = sqLiteHelper.getReadableDatabase();
		 return this; 
	 }
	 
	 public SQLiteAdapter openToWrite() throws android.database.SQLException {
		 sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
		 sqLiteDatabase = sqLiteHelper.getWritableDatabase();
		 return this; 
	 }
	 
	 public void close(){
		 sqLiteHelper.close();
	 }
	 
	 public long insert(String date, String seconds, String money){
		 ContentValues contentValues = new ContentValues();
		 contentValues.put(KEY_TIME, seconds);
		 contentValues.put(KEY_MONEY, money);
		 contentValues.put(KEY_DATE, date);
		 return sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);
	 }
	 
	 public int deleteAll(){
		 return sqLiteDatabase.delete(MYDATABASE_TABLE, null, null);
	 }
	 
	 public String queueAll(){
		 String[] columns = new String[]{KEY_DATE, KEY_TIME, KEY_MONEY};
		 Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns, 
		 null, null, null, null, null);
		 String result = "";
	  
		 int index_DATE = cursor.getColumnIndex(KEY_DATE);
		 int index_TIME = cursor.getColumnIndex(KEY_TIME);
		 int index_MONEY = cursor.getColumnIndex(KEY_MONEY);
		 for(cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()){
			 result = result + "Date: " + cursor.getString(index_DATE) + "  Time: " + cursor.getString(index_TIME) + " Seconds    Paid: " + cursor.getString(index_MONEY) + "Kr. " + "\n";
		 }
	 
		 return result;
	 }
	 
	 public class SQLiteHelper extends SQLiteOpenHelper {
	
	  public SQLiteHelper(Context context, String name, CursorFactory factory, int version) {
		  super(context, name, factory, version);
	  }
	
	  @Override
	  public void onCreate(SQLiteDatabase db) {
		  // TODO Auto-generated method stub
		  Log.d("sql","Creating DB");
		  db.execSQL(SCRIPT_CREATE_DATABASE);
	  }
	
	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	   // TODO Auto-generated method stub
	
	  }
	
	 }
	 
}