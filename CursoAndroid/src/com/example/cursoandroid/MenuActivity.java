package com.example.cursoandroid;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MenuActivity extends Activity implements LocationListener {
	private LocationManager locManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
	    NetUtils netutils;
	    
		ImageButton botoncalc = (ImageButton) findViewById(R.id.bcalc);
		ImageButton botonsms= (ImageButton)findViewById(R.id.bmsg);
		botoncalc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Log.i("curso","entrando al activity calculadora");
				Intent i=new Intent(MenuActivity.this,MainActivity.class); 
				startActivity(i);
			}
		});
		botonsms.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Log.i("curso","entrando a msg");
				Intent i=new Intent(MenuActivity.this,MessageMainListActivity.class); 
				startActivity(i);
			}
		});
		
		Button botonpostales = (Button) this.findViewById(R.id.Blistas);
		botonpostales.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(MenuActivity.this,PostalActivity.class);
				startActivity(i);
				
			}
		});
		
		this.locManager=(LocationManager)this.getSystemService(LOCATION_SERVICE);
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, this);
		
		
		Location loc= locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		
	
		
		TextView tvgeo = (TextView) this.findViewById(R.id.tvGeo);
		
		
		if(loc != null){
			
			tvgeo.setText(loc.getLatitude()+","+loc.getLongitude());
			RequestHelper request = new RequestHelper("http://api.geonames.org/findNearbyPlaceNameJSON?username=cursoandroiddicis&lat="+loc.getLatitude()+"&lng="+loc.getLongitude(),this);
		    request.execute();
		    ProgressBar bar = (ProgressBar)this.findViewById(R.id.progressBar);
		    bar.setVisibility(View.VISIBLE);
		    bar.setIndeterminate(true);
		    
		    
		}else{
			tvgeo.setText("no location");
		}
		
		
		
		
	}
	
	
   

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
	
		return true;
	}

	@Override
	public void onResume(){
		super.onResume();
		
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 10, this);
	}
	@Override
	public void onRestart(){
		super.onRestart();
		
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 10, this);
		
	}
	@Override
	public void onPause(){
		super.onPause();
		this.locManager.removeUpdates(this);
		
	}
	@Override
	public void onDestroy(){
		super.onDestroy();
		this.locManager.removeUpdates(this);
	}
	@Override
	public void onLocationChanged(Location loc) {
		Log.i("Curso","cambio de location");
		TextView tvgeo = (TextView) this.findViewById(R.id.tvGeo);
		tvgeo.setText(loc.getLatitude()+","+loc.getLongitude());
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

}
