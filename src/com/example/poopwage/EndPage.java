package com.example.poopwage;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndPage extends Activity{
	
	private int time;
	private int moneyEarned;
	private SQLiteAdapter mySQLiteAdapter;
	private TextView textfield;
	private Calendar c = Calendar.getInstance();
	private int Day = c.get(Calendar.DAY_OF_MONTH);
	private int Month = c.get(Calendar.MONTH);
	private String Date = (String.valueOf(Day)+"."+String.valueOf(Month+1));
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		Log.d("sql", String.valueOf(Date));
		// Setting up all the primary stuff
		Intent intent = getIntent();
		time = intent.getIntExtra("time", 0);
		moneyEarned = intent.getIntExtra("moneyEarned", 0);
		
		Log.d("Intent Received", "Time: " + time + "   Money: " + moneyEarned);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.end_page);
		

		textfield=(TextView)findViewById(R.id.textView1);
		
		//Database Connection
		mySQLiteAdapter = new SQLiteAdapter(this);
        mySQLiteAdapter.openToWrite();
        mySQLiteAdapter.insert(Date, String.valueOf(time), String.valueOf(moneyEarned));
        mySQLiteAdapter.close();

        /*
         *  Open the same SQLite database
         *  and read all it's content.
         */
        mySQLiteAdapter = new SQLiteAdapter(this);
        mySQLiteAdapter.openToRead();
        String contentRead = mySQLiteAdapter.queueAll();
        mySQLiteAdapter.close();
        
        textfield.setText(contentRead);
        
	}
}
