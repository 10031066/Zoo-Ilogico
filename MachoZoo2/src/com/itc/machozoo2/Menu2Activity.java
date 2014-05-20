package com.itc.machozoo2;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Menu2Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu2);
		ImageButton Esc1 = (ImageButton) findViewById(R.id.escenario1);
		ImageButton Esc2 = (ImageButton) findViewById(R.id.escenario2);
		ImageButton Esc3 = (ImageButton) findViewById(R.id.escenario3);
		ImageButton Esc4 = (ImageButton) findViewById(R.id.escenario4);
		
		
		Esc1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("Zoo","entrando al Escenario 1");				
				Intent i=new Intent(Menu2Activity.this,MainActivity.class); 
				i.putExtra("esc", 1);
				startActivity(i);
				}
		});
		
		Esc2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("Zoo","entrando al Escenario 2");				
				Intent i=new Intent(Menu2Activity.this,WinActivity.class); 
				i.putExtra("esc", 2);
				startActivity(i);
				}
		});
		
		Esc3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("Zoo","entrando al Escenario 3");				
				Intent i=new Intent(Menu2Activity.this,MainActivity.class); 
				i.putExtra("esc", 3);
				startActivity(i);
				}
		});
		
		Esc4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("Zoo","entrando al Escenario 3");				
				Intent i=new Intent(Menu2Activity.this,MainActivity.class); 
				i.putExtra("esc", 4);
				startActivity(i);
				}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu2, menu);
		return true;
	}
	
	public void lanzar(View view) {
        Intent i = new Intent(this, MainActivity.class );
        startActivity(i);
  } 

}
