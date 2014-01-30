package com.suppakng.wordguess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//Written By Nuthapol Suppakitjarak & Ryan Tuller & Kathryn Dew & Kyle Berry
/*
* Tested the startup to make sure the splash screen + sound clip appear
* Tested to make sure the slash screen closes and startsup the next activity
* 
*/
public class Result extends Activity {

	private int testss;

	@SuppressWarnings("null")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		String data = "";
		 ArrayList<Player> players = new ArrayList<Player>();
		 ArrayList<Player> copy = new ArrayList<Player>();
		 
		//Getting the boolean condition from the last activity
		Bundle bundle = getIntent().getExtras();
		String name = bundle.getString("user_name");
	    int points = bundle.getInt("user_points");
		TextView guessCharInput = (TextView)findViewById(R.id.result);
	 	TextView player1 = (TextView)findViewById(R.id.player1);
		TextView player2 = (TextView)findViewById(R.id.player2);
		TextView player3 = (TextView)findViewById(R.id.player3);
		Button replayButton = (Button)findViewById(R.id.replayButton);
		String filename = "highscore4";
		Player currentPlayer = new Player(name, points);
		int score = 0;
		int playerCount = 0;
		
	 /*This method will first try to open the file highscore
	  * If this fails, it will create a new file name highscore
	  * If this doesn't fail, it will find all the user and score of
	  * each players and add it to an array list.
	  * 
	 */
		
		//*Read the high scores
		Context context = getApplicationContext();
		int ch;
		StringBuffer fileContent = new StringBuffer("");
		FileInputStream fis;
		try {
		    fis = context.openFileInput( filename );
		    try {
		        while( (ch = fis.read()) != -1)
		            fileContent.append((char)ch);
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		}
		//data recieved
		 data = new String(fileContent);
		 @SuppressWarnings("unused")
		int tester = 0;
		 String info = "";
		 info = (currentPlayer.name+" "+currentPlayer.score);
		  if (data.equals("")|| data == null) {
		 
		  
		  player1.setText(info);
		  info = "/"+info;
		  FileOutputStream fos;
		  try
		  {
		      fos = context.openFileOutput( filename, Context.MODE_PRIVATE );
		      try
		      {
		          fos.write( info.getBytes() );
		          fos.close();

		      }
		      catch (IOException e)
		      {
		          e.printStackTrace();
		      }

		  }
		  catch (FileNotFoundException e)
		  {
		      e.printStackTrace();
		  }
		} else {
			// Split path into segments
			  String splitPlayers[] = data.split("/");
			for (int size = 0; size < 3; size++) {
		
				
				//Add all the players from the text file
				if(!data.equals("")&& size< splitPlayers.length-1)
				{
				  
					// Split path into segments
				  
				  String splitData[] = splitPlayers[size+1].split(" ");
					
				  String names = splitData[0];
				//  String testScore =data.substring((i2+1), (i3-1));
				  int scores = Integer.parseInt(splitData[1]);
				  Player player = new Player(names, scores);
				  players.add(player);
				  
		    
				}
				else
				{
					size = 3;
				}
			}
			
		}
		
		//This will compare to see which player has the highest score
		int i = 0;
		int sizeCounter = 0;
		while(i < players.size()&& i <3 )
		{
		  if(currentPlayer.score >= players.get(i).score)
		  {
			 for(int ii=0;ii<i;ii++)//adding all the players before the current score
			 {
				 copy.add(players.get(ii));
				 sizeCounter++;
			 }
			  copy.add(currentPlayer);
			  sizeCounter++;
			 for(int ii=i;ii<players.size();ii++)//adding all the players after the current score
			 {
				 copy.add(players.get(ii));
				 sizeCounter++;
			 }
			 players = copy;
			 /*players.clear();
			 int ii = 0;
			 while(ii<copy.size() && ii <10)
			 {
				 players.add(copy.get(ii));
				 ii++;
			 }*/
			
			  break;
		  }
		 i++;	
		}
		if(players.size()== 0)
		{
			players.add(currentPlayer);
		}
		player1.setText(players.get(0).name +" "+players.get(0).score);
		if(players.size() >=2)
		player2.setText(players.get(1).name +" "+players.get(1).score);
		if(players.size() >=3)
		player3.setText(players.get(2).name +" "+players.get(2).score);
		
		if (points>=30) // winning condition
		{
			guessCharInput.setText("You Won!");
		}
		else //losing condition
		{
			guessCharInput.setText("You Suck!");
		}
		
		
		String str = "";
		
	
	
			for(int ii = 0;ii<players.size();ii++)
			{
				str += "/"+players.get(ii).name+" "+players.get(ii).score;
			}
			
		FileOutputStream fos;
		  try
		  {
		      fos = context.openFileOutput( filename, Context.MODE_PRIVATE );
		      try
		      {
		          fos.write( str.getBytes() );
		          fos.close();

		      }
		      catch (IOException e)
		      {
		          e.printStackTrace();
		      }

		  }
		  catch (FileNotFoundException e)
		  {
		      e.printStackTrace();
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
