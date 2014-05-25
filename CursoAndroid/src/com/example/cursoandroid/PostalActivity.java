package com.example.cursoandroid;

import android.os.Bundle;

import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Button;

public class PostalActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_postal);
		
		final ProgressBar bar = (ProgressBar) this.findViewById(R.id.progressBarPostal);
		bar.setVisibility(View.GONE);
		
		final EditText editpostalCode = (EditText) this.findViewById(R.id.PostalText);
		
		Button botonSend = (Button) this.findViewById(R.id.Gobutton);
		botonSend.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Log.e("curso", "se dio click en el boton send");
				bar.setVisibility(View.VISIBLE);
				bar.setIndeterminate(true);
				
				String postalcode= editpostalCode.getEditableText().toString();
				Log.e("curso", "se creo el string");
				PostalesRequestHelper request = new PostalesRequestHelper(postalcode, PostalActivity.this);
				Log.e("curso", "request helper creado");
				request.execute();
				Log.e("curso", "ejecutado");
				
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.postal, menu);
		return true;
	}

}
