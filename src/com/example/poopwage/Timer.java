package com.example.poopwage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Timer extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timer);
		
		//get hourly wage from StartScreen activity
		Intent intent = getIntent();
		int hourlyWage = intent.getIntExtra("hourly wage", 0);
		
		Log.d("Wage", "Your hourly wage is "+hourlyWage+ " kr. per hour");
	}
	
}
