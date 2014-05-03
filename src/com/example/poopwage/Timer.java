package com.example.poopwage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class Timer extends Activity {
	private int number;
	private TextView textfield;
	private TextView textfield2;
	private int moneyEarned;
	private Handler handler;
	private boolean Running = true;
	private int hourlyWage;
	private boolean stopped  = false;
	Button stopButton;


	public void stop(View view){
		stopped = true;
		Log.d("button","stopping counter");
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		//get hourly wage from StartScreen activity
		Intent intent = getIntent();
		hourlyWage = intent.getIntExtra("hourly wage", 0);
		
		Log.d("Wage", "Your hourly wage is "+hourlyWage+ " kr. per hour");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timer);
	
		textfield=(TextView)findViewById(R.id.TVTimer);
		textfield2=(TextView)findViewById(R.id.textfield);
		handler = new Handler();
		
		Runnable runnable = new Runnable(){
			@Override
			
			
			public void run(){
				while(Running){
					try{
						Thread.sleep(1000);
					}
					catch(InterruptedException e){
						e.printStackTrace();
					}
					handler.post(new Runnable(){
						@Override
						public void run(){
							if(stopped == false){
								number+=1;
								moneyEarned = Math.round(number*hourlyWage/3600);
								textfield.setText(String.valueOf(number));
								textfield2.setText(String.valueOf(moneyEarned));
								Log.d("wage", String.valueOf(hourlyWage));
								Log.d("payEarned", String.valueOf(number*hourlyWage/3600));
							}
							
						}
					});
				}
			}
		};
	
	new Thread(runnable).start();


	}
	
}
