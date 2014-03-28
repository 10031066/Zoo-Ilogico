package com.example.lolmachine;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;

public class WinDialogActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_win_dialog);
		Bundle bundle = getIntent().getExtras();
		TextView DisplayPremio = (TextView) findViewById(R.id.textView1);
		Log.i("LolMachine", "el premio es de "+ bundle.getInt("premio"));
		DisplayPremio.setText("$ "+bundle.getInt("premio"));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.win_dialog, menu);
		return true;
	}

}
