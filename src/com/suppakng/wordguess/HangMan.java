package com.suppakng.wordguess;
//Written By Nuthapol Suppakitjarak & Ryan Tuller & Kathryn Dew & Kyle Berry
/*
* Tested to make sure the program account correctly for incorrect/correct char inputs
* Tested to make sure the result screen is displayed according to win/lost condition
* 
*/


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class HangMan extends Activity {
	
	int points = 0;
    //Declared variables
	int guessesLefts;
    boolean correctLetter;
    
    //Initialize variables
    Bundle b = new Bundle();
    String usedChar = "";  
    String word1= "";
    String word2= "";
    String word3= "";
    String word1C = "";
    String word2C= "";
    String word3C= "";
    String name= "";
    String hint = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hang_man);
		
		//Declared UI Elements
		final EditText guessCharInput = (EditText)findViewById(R.id.guessCharInput);
		final Button guessEnter = (Button)findViewById(R.id.guessEnter);
		final TextView guessedCharList = (TextView)findViewById(R.id.guessedCharList);
		final TextView hint = (TextView)findViewById(R.id.tvHint);
		final TextView hangManText = (TextView)findViewById(R.id.hangManText);
        hangManText.setText("");
		//Initialize string variables
	    guessesLefts = -0;
	    usedChar = "";

		
		//Bundle Declaration to get the data from the last activity
		Bundle bundle = getIntent().getExtras();
		try
		{
		word1 = bundle.getString("word1");
		word2 = bundle.getString("word2");
		word3 = bundle.getString("word3");
		name = bundle.getString("user_name");
		hint.setText(bundle.getString("user_hint"));
		}catch(Exception e)
		{
			System.out.print(e);
		}
		
		final TextView textView1 = (TextView)findViewById(R.id.hiddenWord);
		final TextView textView2 = (TextView)findViewById(R.id.hiddenWord1);
		final TextView textView3 = (TextView)findViewById(R.id.hiddenWord2);
		final String hangMan = "HANGMAN";
		//Sets how many '*" we need 
				for(int i = 0;i<word1.length();i++)
				{
					word1C += "*";
				}
				for(int i = 0;i<word2.length();i++)
				{
					word2C += "*";
				}
				for(int i = 0;i<word3.length();i++)
				{
					word3C += "*";
				}
			
				textView1.setText(word1C);
				textView2.setText(word2C);
				textView3.setText(word3C);
				guessEnter.setOnClickListener(new View.OnClickListener() 
		        {
					@Override
					public void onClick(View v) 
					{
						 
	    	        	correctLetter = false;
	    	        	InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
	    	            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
	    	            
	    	              for(int i = 0;i<usedChar.length();i++)
	    	            {
	    	            	if(usedChar.contains(guessCharInput.getText().toString().toUpperCase()))
	    	            	{
	    	            		//return true;
	    	            	}
	    	            }
	    	            
	    	            usedChar += guessCharInput.getText().toString().toUpperCase();
	    	            guessedCharList.setText(usedChar);
	    	           
	    	            //Check for correct keys and reveal them
	    	            for(int i = 0; i< word1.length();i++)
	    	            {
	    	            	if((word1.charAt(i)+"").equals(guessCharInput.getText().toString()))
	    	            	{
	    	            		StringBuilder wordCopy = new StringBuilder(word1C);
	    	            		wordCopy.setCharAt(i, guessCharInput.getText().charAt(0));
	    	            		word1C = wordCopy +"";
	    	            		correctLetter = true;
	    	            		textView1.setText(word1C);
	    	            	}
	    	            }
	    	            for(int i = 0; i< word2.length();i++)
	    	            {
	    	            	if((word2.charAt(i)+"").equals(guessCharInput.getText().toString()))
	    	            	{
	    	            		StringBuilder wordCopy = new StringBuilder(word2C);
	    	            		wordCopy.setCharAt(i, guessCharInput.getText().charAt(0));
	    	            		word2C = wordCopy +"";
	    	            		correctLetter = true;
	    	            		textView2.setText(word2C);
	    	            	}
	    	            }
	    	            for(int i = 0; i< word3.length();i++)
	    	            {
	    	            	if((word3.charAt(i)+"").equals(guessCharInput.getText().toString()))
	    	            	{
	    	            		StringBuilder wordCopy = new StringBuilder(word3C);
	    	            		wordCopy.setCharAt(i, guessCharInput.getText().charAt(0));
	    	            		word3C = wordCopy +"";
	    	            		correctLetter = true;
	    	            		textView3.setText(word3C);
	    	            	}
	    	            }
	    	          
	    				
	    			   
	    	            //Winning condition that will take the user to the winning activity
	    	            if(!word1C.contains("*") &&!word2C.contains("*")&&!word3C.contains("*"))
		            	{
	    	               points += 30;
	    	               Intent intent = new Intent(HangMan.this, Result.class);
	    	               intent.putExtra("user_name", name);
	   					   intent.putExtra("user_points", points);
	    				   HangMan.this.startActivity(intent);
	    			       HangMan.this.finish();
		            	}
	    	            
	    	            //Check if we had any matches or if any char equals ""
	    	            if(!correctLetter)
	    	            {
	    	              hangManText.append(hangMan.charAt(guessesLefts)+"");
	    	             ++guessesLefts;
	    	             
	    	            }
	    	            //Losing condition that will take the user to the result activity
	    	            if(guessesLefts == 7)
	    	            {
	    	            	Intent intent = new Intent(HangMan.this, Result.class);
	    			        if(!word1.contains("*"))
	    			        {
	    			        	points+=10;
	    			        }
	    			        else
	    			        {
	    			        	if(!word1.equals(word1C))
	    			        	{
	    			        		points+=1;
	    			        	}
	    			        }
	    			        if(!word2.contains("*"))
	    			        {
	    			        	points+=10;
	    			        }
	    			        else
	    			        {
	    			        	if(!word2.equals(word2C))
	    			        	{
	    			        		points+=1;
	    			        	}
	    			        }
	    			        if(!word3.contains("*"))
	    			        {
	    			        	points+=10;
	    			        }
	    			        else
	    			        {
	    			        	if(!word3.equals(word3C))
	    			        	{
	    			        		points+=1;
	    			        	}
	    			        }
	    					intent.putExtra("user_name", name);
	    					intent.putExtra("user_points", points);
	    					HangMan.this.startActivity(intent);
	    			        HangMan.this.finish();
	    	            }
	    	          
	    	            guessCharInput.setText(""); 	    
	    	           
	    	            //return true;
	    	        }
	    	        //return false;
	    	        
				     
					
				});
		/*guessCharInput.setOnKeyListener(new OnKeyListener() {
    	    public boolean onKey(View v, int keyCode, KeyEvent event) {
    	        if (((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_DPAD_CENTER))||keyCode == EditorInfo.IME_ACTION_DONE) {
    	            
    	        	correctLetter = false;
    	        	InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
    	            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    	            
    	              for(int i = 0;i<usedChar.length();i++)
    	            {
    	            	if(usedChar.contains(guessCharInput.getText().toString().toUpperCase()))
    	            	{
    	            		return true;
    	            	}
    	            }
    	            
    	            usedChar += guessCharInput.getText().toString().toUpperCase();
    	            guessedCharList.setText(usedChar);
    	           
    	            //Check for correct keys and reveal them
    	            for(int i = 0; i< word1.length();i++)
    	            {
    	            	if((word1.charAt(i)+"").equals(guessCharInput.getText().toString()))
    	            	{
    	            		StringBuilder wordCopy = new StringBuilder(word1C);
    	            		wordCopy.setCharAt(i, guessCharInput.getText().charAt(0));
    	            		word1C = wordCopy +"";
    	            		correctLetter = true;
    	            		textView1.setText(word1C);
    	            	}
    	            }
    	            for(int i = 0; i< word2.length();i++)
    	            {
    	            	if((word2.charAt(i)+"").equals(guessCharInput.getText().toString()))
    	            	{
    	            		StringBuilder wordCopy = new StringBuilder(word2C);
    	            		wordCopy.setCharAt(i, guessCharInput.getText().charAt(0));
    	            		word2C = wordCopy +"";
    	            		correctLetter = true;
    	            		textView2.setText(word2C);
    	            	}
    	            }
    	            for(int i = 0; i< word3.length();i++)
    	            {
    	            	if((word3.charAt(i)+"").equals(guessCharInput.getText().toString()))
    	            	{
    	            		StringBuilder wordCopy = new StringBuilder(word3C);
    	            		wordCopy.setCharAt(i, guessCharInput.getText().charAt(0));
    	            		word3C = wordCopy +"";
    	            		correctLetter = true;
    	            		textView3.setText(word3C);
    	            	}
    	            }
    	          
    				
    			   
    	            //Winning condition that will take the user to the winning activity
    	            if(!word1C.contains("*") &&!word2C.contains("*")&&!word3C.contains("*"))
	            	{
    	               points += 30;
    	               Intent intent = new Intent(HangMan.this, Result.class);
    	               intent.putExtra("user_name", name);
   					   intent.putExtra("user_points", points);
    				   HangMan.this.startActivity(intent);
    			       HangMan.this.finish();
	            	}
    	            
    	            //Check if we had any matches or if any char equals ""
    	            if(!correctLetter)
    	            {
    	              hangManText.append(hangMan.charAt(guessesLefts)+"");
    	             ++guessesLefts;
    	             
    	            }
    	            //Losing condition that will take the user to the result activity
    	            if(guessesLefts == 7)
    	            {
    	            	Intent intent = new Intent(HangMan.this, Result.class);
    			        if(!word1.contains("*"))
    			        {
    			        	points+=10;
    			        }
    			        else
    			        {
    			        	if(!word1.equals(word1C))
    			        	{
    			        		points+=1;
    			        	}
    			        }
    			        if(!word2.contains("*"))
    			        {
    			        	points+=10;
    			        }
    			        else
    			        {
    			        	if(!word2.equals(word2C))
    			        	{
    			        		points+=1;
    			        	}
    			        }
    			        if(!word3.contains("*"))
    			        {
    			        	points+=10;
    			        }
    			        else
    			        {
    			        	if(!word3.equals(word3C))
    			        	{
    			        		points+=1;
    			        	}
    			        }
    					intent.putExtra("user_name", name);
    					intent.putExtra("user_points", points);
    					HangMan.this.startActivity(intent);
    			        HangMan.this.finish();
    	            }
    	          
    	            guessCharInput.setText(""); 	    
    	           
    	            return true;
    	        }
    	        return false;
    	        }
    	    });*/

		
	}
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hang_man, menu);
		return true;
	}

}
