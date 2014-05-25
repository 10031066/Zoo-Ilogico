package com.example.c_beto;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConvetidorActivity extends Activity {
	double var=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_convetidor);
		
		Button celsius = (Button) findViewById(R.id.C);
		Button Fahrenheit = (Button) findViewById(R.id.F);
		final EditText et = (EditText) findViewById(R.id.ET_grados);
		final TextView tv = (TextView) findViewById(R.id.TV_tag);
		
		celsius.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//(°F - 32) x 5/9 = °C
				var=Double.parseDouble(et.getText().toString());
				et.setText(""+((var-32)*5/9));
				tv.setText("°C");
				
				
			}
		});

Fahrenheit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//(°C × 9/5) + 32 = °F
				var=Double.parseDouble(et.getText().toString());
				et.setText(""+((var*9/5)+32));
				tv.setText("°F");
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.convetidor, menu);
		return true;
	}

}
