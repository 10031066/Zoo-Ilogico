package com.example.c_beto;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ImageButton botoncalc = (ImageButton) findViewById(R.id.imagecalc);
		ImageButton botonconv= (ImageButton)findViewById(R.id.imageconv);
		
     botoncalc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Log.i("C_beto","entrando al activity calculadora");
				Intent i=new Intent(MainActivity.this,Calculadora.class); 
				startActivity(i);
			}
		});
	
	botonconv.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			Log.i("C_beto","entrando al activity convertidor");
			Intent i=new Intent(MainActivity.this,ConvetidorActivity.class); 
			startActivity(i);
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
