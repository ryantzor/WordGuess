package com.suppakng.wordguess;


import java.io.FileOutputStream;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
//Written By Nuthapol Suppakitjarak & Ryan Tuller
/*
 * Tested the startup to make sure the splash screen + sound clip appear
 * Tested to make sure the slash screen closes and startsup the next activity
 * 
 */
public class SplashScreen extends Activity {
	MediaPlayer ourSong;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen);
	//****TIP****Soundpool used for small clips || mediaplayer uses background and longer
		
		//If sound clip 20 sec long we don't want to carryit outside next class
		ourSong = MediaPlayer.create(SplashScreen.this, R.raw.electrics);
		ourSong.start();
		
		// A timer thread looking for "run" method
		Thread timer = new Thread() {
			public void run() {
				try {
					//this is how many mil sec
					sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();

				} finally {
					Intent intent = new Intent(SplashScreen.this, EnterWordActivity.class);
			        SplashScreen.this.startActivity(intent);
			        SplashScreen.this.finish();
				}

			}
		};
		timer.start();
		
		String filename = "myfile";
		String string = "Hello world!";
		FileOutputStream outputStream;

		try {
		  outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
		  outputStream.write(string.getBytes());
		  outputStream.close();
		} catch (Exception e) {
		  e.printStackTrace();
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		ourSong.release();
		
		//destroys it
		finish();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}

}
