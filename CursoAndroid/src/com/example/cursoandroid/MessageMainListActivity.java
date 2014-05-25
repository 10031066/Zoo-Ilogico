package com.example.cursoandroid;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.content.Intent;
import android.view.View.OnClickListener;
public class MessageMainListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message_main_list);
		
		ListView lvmsgs= (ListView) findViewById(R.id.lvmsg);
		
		//String[] datasource = new String[] {"elemento1","elemento2","elemento3","elemento4","elemento5"};
		
		MessageDataSource datasource = new MessageDataSource(this);
		datasource.oper();
		final ArrayList<Message> allMessages = datasource.getAllMessage();
		
		//ArrayAdapter<Message> adapter = new ArrayAdapter<Message> (this,android.R.layout.simple_list_item_1,allMessages);
	
		SMSThreadListAdapter adapter = new SMSThreadListAdapter(this, R.layout.item_list_sms,allMessages);
		lvmsgs.setAdapter(adapter);
		
		lvmsgs.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				Log.i("curso","item clickeado: "+ String.valueOf(position));
				Intent i = new Intent(MessageMainListActivity.this,Smscomp.class);
				i.putExtra("number", allMessages.get(position).getNumber_source());
				startActivity(i);
				// TODO Auto-generated method stub
				
			}
		});
		Button buttonnewMessage = (Button) this.findViewById(R.id.bnew);
		buttonnewMessage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MessageMainListActivity.this,Smscomp.class);
				
				startActivity(i);
			}
			
		});
 	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.message_main_list, menu);
		return true;
	}

}
