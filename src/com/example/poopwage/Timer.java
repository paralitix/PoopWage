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


	public void stop(View view){
		stopped = true;
	}
	
	public void nextPage(View view){
		if(stopped = true){

			Intent endPageIntent = new Intent(this, EndPage.class);
			Log.d("intent","intent has been created");
			endPageIntent.putExtra("time", number);
			endPageIntent.putExtra("moneyEarned", moneyEarned);
			Log.d("intent","intent variables have been added");
			startActivity(endPageIntent);
		}
	}
	
	public void tweetThatShit(View view){
		String statusText = editStatus.getText().toString();
		
		String tweetUrl = "https://twitter.com/intent/tweet?text="+statusText;
		Uri uri = Uri.parse(tweetUrl);
		startActivity(new Intent(Intent.ACTION_VIEW, uri));
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
								textfield.setText("Time: " + String.valueOf(number)+" Seconds");
								textfield2.setText("Money: " + String.valueOf(moneyEarned)+"Kr.");
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
