package com.suppakng.wordguess;

import java.lang.Object;
import android.view.InputEvent;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
//Written By Nuthapol Suppakitjarak & Ryan Tuller
/*
* Tested the startup to make sure the UI tool appear and respond accordingly 
* Tested to make sure the enterWord_Activity closes and starts up the next activity
* 
*/
public class EnterWordActivity extends Activity {
	PopupWindow popUp;
	Button startB;
	EditText editText1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterword);
        startB = (Button)findViewById(R.id.replayButton);
        editText1 = (EditText)findViewById(R.id.guessCharInput);
        popUp = new PopupWindow(this);
        
        final TextView errorMessage = (TextView)findViewById(R.id.result);     
        final TextView hint = (TextView)findViewById(R.id.tvHintArea);
        
        //Key listener for the hint to properly close the keyboard when enter is pressed
        hint.setOnKeyListener(new OnKeyListener() {
   	    public boolean onKey(View v, int keyCode, KeyEvent event) {
   	        if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_DPAD_CENTER)) {
   	            //sendMessage(v);
   	        	InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
   	            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
   	            return true;
   	        }
   	        return false;
   	        }
   	    });
        
      //Key listener for the word to properly close the keyboard when enter is pressed
       editText1.setOnKeyListener(new OnKeyListener() {
    	    public boolean onKey(View v, int keyCode, KeyEvent event) {
    	        if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_DPAD_CENTER)) {
    	            //sendMessage(v);
    	        	InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
    	            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    	            return true;
    	        }
    	        return false;
    	        }
    	    });
        startB.setOnClickListener(new View.OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
			  if(editText1.length()> 0)
			  {
				
				Intent intent = new Intent(EnterWordActivity.this, HangMan.class);
		        String value = editText1.getText().toString();
		        String hinter = hint.getText().toString();
		        
				intent.putExtra("user_word", value);
				intent.putExtra("user_hint", hinter );
				
				EnterWordActivity.this.startActivity(intent);
		        EnterWordActivity.this.finish();
			  }
			  else
			  {
				  errorMessage.setVisibility(1);
			  }
		     
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
