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

//Written By Nuthapol Suppakitjarak & Ryan Tuller & Kathryn Dew & Kyle Berry
/*
 * Tested the startup to make sure the UI tool appear and respond accordingly 
 * Tested to make sure the enterWord_Activity closes and starts up the next activity
 * 
 */
public class EnterWordActivity extends Activity {
	PopupWindow popUp;
	Button startB;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_enterword);
		// Find and initialize all of the controls
		startB = (Button) findViewById(R.id.replayButton);
		final EditText etWord1 = (EditText) findViewById(R.id.first_word);
		final EditText etWord2 = (EditText) findViewById(R.id.seond_word);
		final EditText etWord3 = (EditText) findViewById(R.id.third_word);
		final EditText etUserName = (EditText) findViewById(R.id.etUserName);
		

		// final TextView errorMessage = (TextView)findViewById(R.id.result);
		final EditText hint = (EditText) findViewById(R.id.tvHintArea);
		final TextView test = (TextView) findViewById(R.id.hangManText);
		// Key listener for the hint to properly close the keyboard when enter
		// is pressed
		hint.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
						&& (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_DPAD_CENTER)) {
					// sendMessage(v);
					InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
					imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,
							0);
					return true;
				}
				return false;
			}
		});

		// Key listener for the word to properly close the keyboard when enter
		// is pressed
		/*etWord1.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
						&& (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_DPAD_CENTER)) {
					// sendMessage(v);
					InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
					imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,
							0);
					return true;
				}
				return false;
			}
		});*/
		startB.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				

					Intent intent = new Intent(EnterWordActivity.this,
							HangMan.class);

					String word1 = etWord1.getText().toString();
					String word2 = etWord2.getText().toString();
					String word3 = etWord3.getText().toString();
					String hinter = hint.getText().toString();
					String userName = etUserName.getText().toString();

					// all of the information in the text fields are passed in
					// here to the next intent
					intent.putExtra("user_hint", hinter);
					intent.putExtra("word1", word1);
					intent.putExtra("word2", word2);
					intent.putExtra("word3", word3);
					intent.putExtra("user_name", userName);
					test.setText("WORKS");
					EnterWordActivity.this.startActivity(intent);
					EnterWordActivity.this.finish();
				

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