package com.suppakng.wordguess;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//Written By Nuthapol Suppakitjarak & Ryan Tuller
/*
* Tested the startup to make sure the splash screen + sound clip appear
* Tested to make sure the slash screen closes and startsup the next activity
* 
*/
public class Result extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		//Getting the boolean condition from the last activity
		Bundle bundle = getIntent().getExtras();
		Boolean win = bundle.getBoolean("user_win");
		TextView guessCharInput = (TextView)findViewById(R.id.result);
		Button replayButton = (Button)findViewById(R.id.replayButton);
		
		if(win) //winning condition
		{
			guessCharInput.setText("You Won!");
		}
		else //losing condition
		{
			guessCharInput.setText("You Suck!");
		}
		replayButton.setOnClickListener(new View.OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent(Result.this, SplashScreen.class);
				Result.this.startActivity(intent);
		        Result.this.finish();
		     
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

}
