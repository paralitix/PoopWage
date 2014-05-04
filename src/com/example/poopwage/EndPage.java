package com.example.poopwage;

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
	
	@Override
	protected void onCreate(Bundle savedInstanceState){

		//get hourly wage from StartScreen activity
		Intent intent = getIntent();
		time = intent.getIntExtra("time", 0);
		moneyEarned = intent.getIntExtra("moneyEarned", 0);
		
		Log.d("Intent Received", "Time: " + time + "   Money: " + moneyEarned);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_end_page);
	}
}
