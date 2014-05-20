package com.itc.machozoo2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class WinActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_win);
ImageButton menu = (ImageButton) findViewById(R.id.menub);
		
		
		menu.setOnClickListener(new OnClickListener() {
			//lol
			@Override
			public void onClick(View v) {
				Log.i("Zoo","entrando al Escenario 1");				
				Intent i=new Intent(WinActivity.this,Menu2Activity.class); 
				i.putExtra("esc", 1);
				startActivity(i);
				}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.win, menu);
		return true;
	}

}
