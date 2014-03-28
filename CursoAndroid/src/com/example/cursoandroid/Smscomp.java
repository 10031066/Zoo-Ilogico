package com.example.cursoandroid;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Button;

public class Smscomp extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smscomp);
		Intent i = this.getIntent();
		
		if (i.hasExtra("number")){
			Log.i("curso", "Contiene el extra 'number' ");
			Bundle extras = i.getExtras();
			String number = extras.getString("number");
			
			EditText editnum = (EditText) this.findViewById(R.id.tvphone);
			editnum.setText(number);
		}
		
		Button enviar = (Button) this.findViewById(R.id.bsend);
		enviar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				EditText editnumberC = (EditText)findViewById(R.id.tvphone);
				EditText editTextC = (EditText)findViewById(R.id.tvmessage);
				
				String number = editnumberC.getEditableText().toString();
				String text = editTextC.getEditableText().toString();
				
				if(number==null || text==null){
					Log.w("curso","campos vacios");
					return;
				}
				
				
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(number, null, text, null, null);
				editTextC.setText("");
				
				//insertr en la base de datos y actualizar la lista
				
				
				
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.smscomp, menu);
		return true;
	}

}
