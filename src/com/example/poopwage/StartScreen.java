package com.example.poopwage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class StartScreen extends Activity implements OnClickListener {
	
	//Declares button and editText
	Button acceptButton;
	EditText wageEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_screen);
		
		acceptButton = (Button)findViewById(R.id.stopButton);
		wageEditText = (EditText)findViewById(R.id.editText1);
		
		acceptButton.setOnClickListener(this);
		}

	@Override
		public void onClick(View v) {
		int hourlyWage = Integer.parseInt(wageEditText.getText().toString());
		Intent timerIntent = new Intent(this, Timer.class);
		timerIntent.putExtra("hourly wage", hourlyWage);
		startActivity(timerIntent);
	}
}
