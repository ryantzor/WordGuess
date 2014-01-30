package com.suppakng.wordguess;
//Written By Nuthapol Suppakitjarak & Ryan Tuller
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class HangMan extends Activity {
    //Declared variables
	int guessesLefts;
    boolean correctLetter;
    String usedChar;
    String userWordInput;
    String wordDisplay;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hang_man);
		
		//Declared UI Elements
		final EditText guessCharInput = (EditText)findViewById(R.id.guessCharInput);
		final TextView guessedCharList = (TextView)findViewById(R.id.guessedCharList);
		//final TextView guessesLeft = (TextView)findViewById(R.id.guessesLeft);
		final TextView hint = (TextView)findViewById(R.id.tvHint);
		final TextView hangManText = (TextView)findViewById(R.id.hangManText);
        hangManText.setText("");
		//Initialize string variables
	    guessesLefts = -0;
	    usedChar = "";
		wordDisplay = "";
			
		//Bundle Declaration to get the data from the last activity
		Bundle bundle = getIntent().getExtras();
		userWordInput = bundle.getString("user_word");
		hint.setText(bundle.getString("user_hint"));
		
		final TextView textView1 = (TextView)findViewById(R.id.hiddenWord);
		final String hangMan = "HANGMAN";
		guessCharInput.setOnKeyListener(new OnKeyListener() {
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
    	            for(int i = 0; i< userWordInput.length();i++)
    	            {
    	            	if((userWordInput.charAt(i)+"").equals(guessCharInput.getText().toString()))
    	            	{
    	            		StringBuilder wordCopy = new StringBuilder(wordDisplay);
    	            		wordCopy.setCharAt(i, guessCharInput.getText().charAt(0));
    	            		wordDisplay = wordCopy +"";
    	            		correctLetter = true;
    	            	}
    	            }

    	            //Winning condition that will take the user tot he winning activity
    	            if(!wordDisplay.contains("*"))
	            	{
    	               Intent intent = new Intent(HangMan.this, Result.class);
    			        Boolean value = true;
    					intent.putExtra("user_win", value);
    					HangMan.this.startActivity(intent);
    			        HangMan.this.finish();
	            	}
    	            
    	            //Check if we had any matches or if any char equals ""
    	            if(!correctLetter && !guessCharInput.getText().toString().equals(""))
    	            {
    	              hangManText.append(hangMan.charAt(guessesLefts)+"");
    	             ++guessesLefts;
    	             
    	            }
    	            //Losing condition that will take the user to the result activity
    	            if(guessesLefts == 7)
    	            {
    	            	Intent intent = new Intent(HangMan.this, Result.class);
    			        Boolean value = false;
    					intent.putExtra("user_win", value);
    					HangMan.this.startActivity(intent);
    			        HangMan.this.finish();
    	            }
    	            textView1.setText(wordDisplay);
    	            guessCharInput.setText(""); 	    
    	           
    	            //sguessesLeft.setText(guessesLefts+"");
    	            return true;
    	        }
    	        return false;
    	        }
    	    });

		//Sets how many '*" we need 
		for(int i = 0;i<userWordInput.length();i++)
		{
			wordDisplay += "*";
		}
		textView1.setText(wordDisplay);
		
	}
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hang_man, menu);
		return true;
	}

}
